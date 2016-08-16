package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Invoice;
import com.gss.model.PackageWalkIn;
import com.gss.model.ProductWalkIn;
import com.gss.model.PromoWalkIn;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;

public class WalkInJDBCRepository implements WalkInRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	private Connection con = jdbc.getConnection();
	
	@Override
	public boolean createWalkIn(WalkIn walkin) throws SQLException {
		
		String createWalkIn 				= "CALL createWalkIn(?, ?, ?)";
		String createProductWalkIn 			= "CALL createProductWalkIn(?, ?, ?)";
		String createServiceWalkIn 			= "CALL createServiceWalkIn(?, ?, ?, ?)";
		String createPackageWalkIn 			= "CALL createPackageWalkIn(?, ?, ?)";
		String createEmpAssignment 			= "CALL insertAssignmentStatus();";
		String createDetail					= "CALL createAssignmentDetail(?, ?, ?, ?)";
		String createPromoWalkIn 			= "CALL createPromoWalkIn(?, ?)";
		String createPackagePromo			= "CALL createPackagePromoWalkIn(?, ?, ?)";
		String createPromoService			= "CALL createPackagePromoServiceWalkIn(?, ?, ?, ?)";
		String createDiscount					= "CALL createInvoiceDiscount(?, ?);";
		
		try{
			con.setAutoCommit(false);
			int intWalkInID = 0;
			int intInvoiceID = 0;
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
			PreparedStatement preDiscount			= con.prepareStatement(createDiscount);
			ResultSet insertWalkInResult 			= null;
			ResultSet insertEmpAssignmentResult 	= null;
			ResultSet walkInID 						= null;
			
			//Inserting new Walk-In record
			insertWalkIn.setString(1, walkin.getStrName());
			insertWalkIn.setString(2, walkin.getStrContactNo());
			insertWalkIn.setDouble(3, walkin.getInvoice().getDblTotalPrice());
			insertWalkInResult = insertWalkIn.executeQuery();
			
			//parsing inserted Walk-In ID
			while(insertWalkInResult.next()){
				intWalkInID = insertWalkInResult.getInt(1);
				intInvoiceID = insertWalkInResult.getInt(2);
			}
			
			//Inserting discount batch
			for(int i = 0; i < walkin.getInvoice().getDiscountList().size(); i++){
				preDiscount.setInt(1, intInvoiceID);
				preDiscount.setInt(2, walkin.getInvoice().getDiscountList().get(i).getIntDiscountID());
				preDiscount.addBatch();
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
			insertProduct.close();
			
			//Inserting service batch
			for(int intCtr = 0; intCtr < walkin.getServices().size(); intCtr++){
				insertService.setInt(1, intWalkInID);
				insertService.setInt(2, walkin.getServices().get(intCtr).getService().getIntServiceID());
				insertService.setInt(3, walkin.getServices().get(intCtr).getEmployeeAssigned().getIntEmpID());
				insertService.setString(4, walkin.getServices().get(intCtr).getStrServiceStatus());
				insertService.addBatch();
			}
			
			//Executing service batch insert
			insertService.executeBatch();
			insertService.clearBatch();
			insertService.close();
			
			//Batch insert for packages included
			for(int intCtr = 0; intCtr < walkin.getPackages().size(); intCtr++){
				
				insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
				PackageWalkIn packagee = walkin.getPackages().get(intCtr);
	
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
				int intPackageServiceSize = walkin.getPackages().get(intCtr).getServiceAssignment().size();
				
				for(int intCtrInner = 0; intCtrInner < intPackageServiceSize; intCtrInner++){
					insertDetail.setInt(1, intEmpAssignmentID);
					insertDetail.setInt(2, packagee.getServiceAssignment().get(intCtrInner).getEmployeeAssigned().getIntEmpID());
					insertDetail.setInt(3, packagee.getServiceAssignment().get(intCtrInner).getService().getIntServiceID());
					insertDetail.setString(4, packagee.getServiceAssignment().get(intCtrInner).getStrServiceStatus());
					insertDetail.addBatch();
				}
			}
			
			insertPackage.executeBatch();
			insertPackage.clearBatch();
			
			insertDetail.executeBatch();
			insertDetail.clearBatch();
			
			
			System.out.println(walkin.getPromo().size());
			
			//Batch insert for promo included
			for(int intCtr = 0; intCtr < walkin.getPromo().size(); intCtr++){
				
				PromoWalkIn promo = walkin.getPromo().get(intCtr);
				
				insertPromo.setInt(1, intWalkInID);
				insertPromo.setInt(2, walkin.getPromo().get(intCtr).getPromo().getIntPromoID());
				walkInID = insertPromo.executeQuery();
				int walkID = 0;
				
				while(walkInID.next()){
					walkID = walkInID.getInt(1);
				}
				
				int intPromoPackageSize = walkin.getPromo().get(intCtr).getPackages().size();
				

				for(int intCtrInner = 0; intCtrInner < intPromoPackageSize; intCtr++){
					
					PackageWalkIn packagee = promo.getPackages().get(intCtrInner);
					
					insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
					
					//parsing inserted Employee Assignment ID
					while(insertEmpAssignmentResult.next()){
						intEmpAssignmentID = insertEmpAssignmentResult.getInt(1);
					}
					
					insertPromoPackage.setInt(1, walkID);
					insertPromoPackage.setInt(2, intEmpAssignmentID);
					insertPromoPackage.setInt(3, packagee.getPackages().getIntPackageID());
					insertPromoPackage.addBatch();
					
					for(int innerMostCtr = 0; innerMostCtr < promo.getPackages().size(); innerMostCtr++){
						
						//size of services inside the current package in the loop
						int servicesIncludedSize = packagee.getServiceAssignment().size();
						
						for(int packageServiceCtr = 0; packageServiceCtr < servicesIncludedSize; packageServiceCtr++){
							
							ServiceWalkIn service = packagee.getServiceAssignment().get(packageServiceCtr);
							
							insertDetail.setInt(1, intEmpAssignmentID);
							insertDetail.setInt(2, service.getService().getIntServiceID());
							insertDetail.setInt(3, service.getEmployeeAssigned().getIntEmpID());
							insertDetail.setString(4, service.getStrServiceStatus());
							insertDetail.addBatch();
						}
					}
				}
				
				insertPromoPackage.executeBatch();
				insertDetail.executeBatch();
				insertPromoPackage.clearBatch();
				insertDetail.clearBatch();
				
				for(int intCtr2 = 0; intCtr2 < promo.getServices().size(); intCtr2++){
					
					//current service in the loop
					ServiceWalkIn service = promo.getServices().get(intCtr2);
					
					insertServicePromo.setInt(1, walkID);
					insertServicePromo.setInt(2, service.getService().getIntServiceID());
					insertServicePromo.setInt(3, service.getEmployeeAssigned().getIntEmpID());
					insertServicePromo.setString(4, service.getStrServiceStatus());
					insertServicePromo.addBatch();
				}
				
				insertServicePromo.executeBatch();
				insertServicePromo.clearBatch();
				
			}
			
			insertPromo.close();
			walkInID.close();
			insertPromoPackage.close();
			insertDetail.close();
			insertServicePromo.close();
			insertEmpAssignmentResult.close();
			insertPackage.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return false;
		}
	}

	@Override
	public boolean updateWalkIn(WalkIn walkin) throws SQLException {
		
		String createWalkIn 				= "CALL updateWalkIn(?, ?, ?, ?, ?)";
		String createProductWalkIn 			= "CALL createProductWalkIn(?, ?, ?)";
		String createServiceWalkIn 			= "CALL createServiceWalkIn(?, ?, ?, ?)";
		String createPackageWalkIn 			= "CALL createPackageWalkIn(?, ?, ?)";
		String createEmpAssignment 			= "INSERT INTO tblEmployeeAssignment(intAssignmentStatus) VALUE(1)";
		String createDetail					= "CALL createAssignmentDetail(?, ?, ?)";
		String createPromoWalkIn 			= "CALL createPromoWalkIn(?, ?)";
		String createPackagePromo			= "CALL createPackagePromoWalkIn(?, ?)";
		String createPromoService			= "CALL createPackagePromoServiceWalkIn(?, ?, ?)";
		String createDiscount					= "CALL createInvoiceDiscount(?, ?);";
		String deleteAll					= "CALL deleteWaklInComponents(?);";
		
		try{
			con.setAutoCommit(false);
			int intWalkInID = 0;
			int intInvoiceID = 0;
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
			PreparedStatement preDiscount			= con.prepareStatement(createDiscount);
			ResultSet insertWalkInResult;
			ResultSet insertEmpAssignmentResult = null;
			ResultSet walkInID = null;
			
			//Inserting new Walk-In record
			insertWalkIn.setInt(1, walkin.getIntWalkInID());
			insertWalkIn.setInt(2, walkin.getInvoice().getIntInvoiceID());
			insertWalkIn.setString(3, walkin.getStrName());
			insertWalkIn.setString(4, walkin.getStrContactNo());
			insertWalkIn.setDouble(5, walkin.getInvoice().getDblTotalPrice());
			insertWalkInResult = insertWalkIn.executeQuery();
			
			//parsing inserted Walk-In ID
			while(insertWalkInResult.next()){
				intWalkInID = insertWalkInResult.getInt(1);
				intInvoiceID = insertWalkInResult.getInt(2);
			}
			
			//Inserting discount batch
			for(int i = 0; i < walkin.getInvoice().getDiscountList().size(); i++){
				preDiscount.setInt(1, intInvoiceID);
				preDiscount.setInt(2, walkin.getInvoice().getDiscountList().get(i).getIntDiscountID());
				preDiscount.addBatch();
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
			insertProduct.close();
			
			//Inserting service batch
			for(int intCtr = 0; intCtr < walkin.getServices().size(); intCtr++){
				insertService.setInt(1, intWalkInID);
				insertService.setInt(2, walkin.getServices().get(intCtr).getService().getIntServiceID());
				insertService.setInt(3, walkin.getServices().get(intCtr).getEmployeeAssigned().getIntEmpID());
				insertService.setString(4, walkin.getServices().get(intCtr).getStrServiceStatus());
				insertService.addBatch();
			}
			
			//Executing service batch insert
			insertService.executeBatch();
			insertService.clearBatch();
			insertService.close();
			
			//Batch insert for packages included
			for(int intCtr = 0; intCtr < walkin.getPackages().size(); intCtr++){
				
				insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
				PackageWalkIn packagee = walkin.getPackages().get(intCtr);
	
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
				int intPackageServiceSize = walkin.getPackages().get(intCtr).getServiceAssignment().size();
				
				for(int intCtrInner = 0; intCtrInner < intPackageServiceSize; intCtrInner++){
					insertDetail.setInt(1, intEmpAssignmentID);
					insertDetail.setInt(2, packagee.getServiceAssignment().get(intCtrInner).getEmployeeAssigned().getIntEmpID());
					insertDetail.setInt(3, packagee.getServiceAssignment().get(intCtrInner).getService().getIntServiceID());
					insertDetail.setString(4, packagee.getServiceAssignment().get(intCtrInner).getStrServiceStatus());
					insertDetail.addBatch();
				}
			}
			
			insertPackage.executeBatch();
			insertPackage.clearBatch();
			
			insertDetail.executeBatch();
			insertDetail.clearBatch();
			
			
			System.out.println(walkin.getPromo().size());
			
			//Batch insert for promo included
			for(int intCtr = 0; intCtr < walkin.getPromo().size(); intCtr++){
				
				PromoWalkIn promo = walkin.getPromo().get(intCtr);
				
				insertPromo.setInt(1, intWalkInID);
				insertPromo.setInt(2, walkin.getPromo().get(intCtr).getPromo().getIntPromoID());
				walkInID = insertPromo.executeQuery();
				int walkID = 0;
				
				while(walkInID.next()){
					walkID = walkInID.getInt(1);
				}
				
				int intPromoPackageSize = walkin.getPromo().get(intCtr).getPackages().size();
				

				for(int intCtrInner = 0; intCtrInner < intPromoPackageSize; intCtr++){
					
					PackageWalkIn packagee = promo.getPackages().get(intCtrInner);
					
					insertEmpAssignmentResult = insertEmpAssignment.executeQuery();
					
					//parsing inserted Employee Assignment ID
					while(insertEmpAssignmentResult.next()){
						intEmpAssignmentID = insertEmpAssignmentResult.getInt(1);
					}
					
					insertPromoPackage.setInt(1, walkID);
					insertPromoPackage.setInt(2, intEmpAssignmentID);
					insertPromoPackage.setInt(3, packagee.getPackages().getIntPackageID());
					insertPromoPackage.addBatch();
					
					for(int innerMostCtr = 0; innerMostCtr < promo.getPackages().size(); innerMostCtr++){
						
						//size of services inside the current package in the loop
						int servicesIncludedSize = packagee.getServiceAssignment().size();
						
						for(int packageServiceCtr = 0; packageServiceCtr < servicesIncludedSize; packageServiceCtr++){
							
							ServiceWalkIn service = packagee.getServiceAssignment().get(packageServiceCtr);
							
							insertDetail.setInt(1, intEmpAssignmentID);
							insertDetail.setInt(2, service.getService().getIntServiceID());
							insertDetail.setInt(3, service.getEmployeeAssigned().getIntEmpID());
							insertDetail.setString(4, service.getStrServiceStatus());
							insertDetail.addBatch();
						}
					}
				}
				
				insertPromoPackage.executeBatch();
				insertDetail.executeBatch();
				insertPromoPackage.clearBatch();
				insertDetail.clearBatch();
				
				for(int intCtr2 = 0; intCtr2 < promo.getServices().size(); intCtr2++){
					
					//current service in the loop
					ServiceWalkIn service = promo.getServices().get(intCtr2);
					
					insertServicePromo.setInt(1, walkID);
					insertServicePromo.setInt(2, service.getService().getIntServiceID());
					insertServicePromo.setInt(3, service.getEmployeeAssigned().getIntEmpID());
					insertServicePromo.setString(4, service.getStrServiceStatus());
					insertServicePromo.addBatch();
				}
				
				insertServicePromo.executeBatch();
				insertServicePromo.clearBatch();
				
			}
			
			insertPromo.close();
			walkInID.close();
			insertPromoPackage.close();
			insertDetail.close();
			insertServicePromo.close();
			insertEmpAssignmentResult.close();
			insertPackage.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return false;
		}
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
	public List<WalkIn> getAllWalkInNoDetails() {

		List<WalkIn> walkin 			= new ArrayList<WalkIn>();
		List<ProductWalkIn> productList = new ArrayList<ProductWalkIn>();
		List<ServiceWalkIn> serviceList = new ArrayList<ServiceWalkIn>();
		List<PackageWalkIn> packageList = new ArrayList<PackageWalkIn>();
		List<PromoWalkIn> promoList 	= new ArrayList<PromoWalkIn>();
		Connection con 					= jdbc.getConnection();
		String getAllWalkIn				= "SELECT * FROM tblWalkIn WHERE strStatus <> 'CANCELLED';";
		
		try{
			
			PreparedStatement allWalkIn = con.prepareStatement(getAllWalkIn);
			ResultSet walkInResult 		= allWalkIn.executeQuery();
			
			while(walkInResult.next()){
				int intWalkInID = walkInResult.getInt(1);
				String strName = walkInResult.getString(2);
				String strContact = walkInResult.getString(3);
				Date dateCreated = walkInResult.getDate(4);
				int intInvoiceID = walkInResult.getInt(5);
				String strStatus = walkInResult.getString(6);
				String paymentStatus = "PAID";
				
/*				ReservationRepository repo = new ReservationJDBCRepository();
				Invoice invoice = repo.getInvoice(intInvoiceID);
				
				if(invoice.getPaymentList().size() == 0){
					paymentStatus = "UNPAID";
				}
			
				try{
					walkin.add(new WalkIn(intWalkInID, strName, "walkin", strContact, dateCreated, serviceList, productList, packageList, promoList, invoice, invoice.getPaymentList().get(0), paymentStatus, strStatus));
				}
				catch(IndexOutOfBoundsException ib){
					walkin.add(new WalkIn(intWalkInID, strName, "walkin", strContact, dateCreated, serviceList, productList, packageList, promoList, invoice, null, paymentStatus, strStatus));
				}
*/
				walkin.add(new WalkIn(intWalkInID, strName, "walkin", strContact, dateCreated, serviceList, productList, packageList, promoList, null, null, paymentStatus, strStatus));
			}
		
			return walkin;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<WalkIn> getAllWalkIn() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
