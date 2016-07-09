package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.WalkIn;

public class WalkInJDBCRepository implements WalkInRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	private Connection con = jdbc.getConnection();
	
	@Override
	public boolean createWalkIn(WalkIn walkin) throws SQLException {
		
		String createWalkIn 				= "CALL createWalkIn(?, ?)";
		String createProductWalkIn 			= "CALL createProductWalkIn(?, ?, ?)";
		String createServiceWalkIn 			= "CALL createServiceWalkIn(?, ?, ?)";
		String createPackageWalkIn 			= "CALL createPackageWalkIn(?, ?, ?)";
		String createEmpAssignment 			= "INSERT INTO tblEmployeeAssignment(intAssignmentStatus) VALUE(1)";
		String createDetail					= "CALL createAssignmentDetail(?, ?, ?)";
		String createPromoWalkIn 			= "CALL createPromoWalkIn(?, ?)";
		String createPackagePromo			= "CALL createPackagePromoWalkIn(?, ?)";
		String createPromoService			= "CALL createPackagePromoServiceWalkIn(?, ?, ?)";
		
		try{
			con.setAutoCommit(false);
			int intWalkInID = 0;
			int intEmpAssignmentID = 0;
			
			//PreparedStatements and ResultSets
			PreparedStatement insertWalkIn 			= con.prepareStatement(createWalkIn);
			PreparedStatement insertProduct 		= con.prepareStatement(createProductWalkIn);
			PreparedStatement insertService			= con.prepareStatement(createServiceWalkIn);
			PreparedStatement insertPackage 		= con.prepareStatement(createPackageWalkIn);
			PreparedStatement insertEmpAssignment 	= con.prepareStatement(createEmpAssignment);
			PreparedStatement insertDetail 			= con.prepareStatement(createDetail);
			PreparedStatement insertPromo			= con.prepareStatement(createPromoWalkIn);
			PreparedStatement insertPromoPackage	= con.prepareStatement(createPackagePromo);
			PreparedStatement insertServicePromo	= con.prepareStatement(createPromoService);
			ResultSet insertWalkInResult;
			ResultSet insertEmpAssignmentResult;
			ResultSet walkInID;
			
			//Inserting new Walk-In record
			insertWalkIn.setString(1, walkin.getStrName());
			insertWalkIn.setString(2, walkin.getStrContactNo());
			insertWalkInResult = insertWalkIn.executeQuery();
			
			//parsing inserted Walk-In ID
			while(insertWalkInResult.next()){
				intWalkInID = insertWalkInResult.getInt(1);
			}
			
			//Inserting product batch
			for(int intCtr = 0; intCtr < walkin.getProducts().size(); intCtr++){
				
				insertProduct.setInt(1, intWalkInID);
				insertProduct.setInt(2, walkin.getProducts().get(intCtr).getProduct().getIntProductID());
				insertProduct.setInt(3, walkin.getProducts().get(intCtr).getIntQuantity());
				insertProduct.addBatch();
			}
			
			//Executing product batch insert
			insertProduct.executeBatch();
			insertProduct.clearBatch();
			
			//Inserting service batch
			for(int intCtr = 0; intCtr < walkin.getServices().size(); intCtr++){
				insertService.setInt(1, intWalkInID);
				insertService.setInt(2, walkin.getServices().get(intCtr).getService().getIntServiceID());
				insertService.setInt(3, walkin.getServices().get(intCtr).getIntEmployeeAssigned());
				insertService.addBatch();
			}
			
			//Executing service batch insert
			insertService.executeBatch();
			insertService.clearBatch();
			
			
			for(int intCtr = 0; intCtr < walkin.getPackages().size(); intCtr++){
				
				insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
				
				//parsing inserted Employee Assignment ID
				while(insertEmpAssignmentResult.next()){
					intEmpAssignmentID = insertEmpAssignmentResult.getInt(1);
				}
				
				insertEmpAssignmentResult.close();
				
				insertPackage.setInt(1, intWalkInID);
				insertPackage.setInt(2, walkin.getPackages().get(intCtr).getPackages().getIntPackageID());
				insertPackage.setInt(3, intEmpAssignmentID);
				insertPackage.addBatch();
				
				//Size of services inside each package
				int intPackageServiceSize = walkin.getPackages().get(intCtr).getPackages().getServiceList().size();
				
				for(int intCtrInner = 0; intCtrInner < intPackageServiceSize; intCtr++){
					insertDetail.setInt(1, intEmpAssignmentID);
					insertDetail.setInt(2, walkin.getPackages().get(intCtr).getEmployees().get(intCtrInner).getIntEmployeeID());
					insertDetail.setInt(3, walkin.getPackages().get(intCtr).getEmployees().get(intCtrInner).getService().getIntServiceID());
					insertDetail.addBatch();
				}
			}
			
			insertPackage.executeBatch();
			insertPackage.clearBatch();
			insertDetail.executeBatch();
			insertDetail.clearBatch();
			
			for(int intCtr = 0; intCtr < walkin.getPromo().size(); intCtr++){
				
				insertPromo.setInt(1, intWalkInID);
				insertPromo.setInt(2, walkin.getPromo().get(intCtr).getPromo().getIntPromoID());
				walkInID = insertPromo.executeQuery();
				int walkID = 0;
				
				while(walkInID.next()){
					walkID = walkInID.getInt(1);
				}
				
				int intPromoPackageSize = walkin.getPromo().get(intCtr).getPackages().size();
				
				for(int intCtrInner = 0; intCtrInner < intPromoPackageSize; intCtr++){
					
					insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
					
					//parsing inserted Employee Assignment ID
					while(insertEmpAssignmentResult.next()){
						intEmpAssignmentID = insertEmpAssignmentResult.getInt(1);
					}
					
					insertEmpAssignmentResult.close();
					
					insertPromoPackage.setInt(1, walkID);
					insertPromoPackage.setInt(2, intEmpAssignmentID);
					insertPromoPackage.addBatch();
					
					for(int innerMostCtr = 0; innerMostCtr < walkin.getPromo().get(intCtr).getPackages().get(intCtrInner).getEmployees().size(); innerMostCtr++){
						insertDetail.setInt(1, intEmpAssignmentID);
						insertDetail.setInt(2, walkin.getPromo().get(intCtr).getPackages().get(intCtrInner).getEmployees().get(innerMostCtr).getIntEmployeeID());
						insertDetail.setInt(3, walkin.getPromo().get(intCtr).getPackages().get(intCtrInner).getEmployees().get(innerMostCtr).getService().getIntServiceID());
						insertDetail.addBatch();
					}
					
					for(int intCtr2 = 0; intCtr2 < walkin.getServices().size(); intCtr2++){
						insertServicePromo.setInt(1, walkID);
						insertServicePromo.setInt(2, walkin.getPromo().get(intCtr).getServices().get(intCtr2).getService().getIntServiceID());
						insertServicePromo.setInt(3, walkin.getPromo().get(intCtr).getServices().get(intCtr2).getIntEmployeeID());
						insertServicePromo.addBatch();
					}
				}
				
				insertPromoPackage.executeBatch();
				insertDetail.executeBatch();
				insertServicePromo.executeBatch();
				insertPromoPackage.close();
				insertDetail.close();
				insertServicePromo.close();
			}
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			con.rollback();
			con.close();
			
			return false;
		}
	}

	@Override
	public boolean updateWalkIn(WalkIn walkin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelWalkIn(int intWalkInID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean payWalkIn(int intWalkInID, double dblPaymentAmount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<WalkIn> getAllWalkIn() {
		// TODO Auto-generated method stub
		return null;
	}

}
