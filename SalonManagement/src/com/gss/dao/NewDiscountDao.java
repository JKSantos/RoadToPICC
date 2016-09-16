package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class NewDiscountDao {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static String createDiscount(Discount discount) throws SQLException{
		Connection con 				= jdbc.getConnection();
		String strQuery 			= "CALL createDiscount(?, ? ,?, ?, ?, ?)";
		String createProducts		= "CALL createDiscProd(?, ?);";
		String createServices		= "CALL createDiscServ(?, ?);";
		String createPackage		= "CALL createDiscPack(?, ?);";
		String createPromo			= "CALL createDiscPromo(?, ?)";
		int intDiscountID			= 0;
		
		try{
			con.setAutoCommit(false);
			PreparedStatement pre 			= con.prepareStatement(strQuery);
			PreparedStatement preProduct	= con.prepareStatement(createProducts);
			PreparedStatement preService	= con.prepareStatement(createServices);
			PreparedStatement prePackage	= con.prepareStatement(createPackage);
			PreparedStatement prePromo		= con.prepareStatement(createPromo);
			
			ResultSet setDiscountID;
			
			pre.setString(1, discount.getApplicability());
			pre.setString(2, discount.getStrDiscountName());
			pre.setString(3, discount.getStrDiscountDesc());
			pre.setString(4, discount.getStrDiscountGuidelines());
			pre.setInt(5, discount.getIntDiscountType());
			pre.setDouble(6, discount.getDblDiscountAmount());
			
			setDiscountID = pre.executeQuery();
			
			while(setDiscountID.next()){
				intDiscountID = setDiscountID.getInt(1);
			}
			
			pre.close();
			setDiscountID.close();
			
			for(int i = 0; i < discount.getProductList().size(); i++){
				preProduct.setInt(1, intDiscountID);
				preProduct.setInt(2, discount.getProductList().get(i).getIntProductID());
				preProduct.addBatch();
			}
			
			preProduct.executeBatch();
			preProduct.close();
			
			for(int i = 0; i < discount.getServiceList().size(); i++){
				preService.setInt(1, intDiscountID);
				preService.setInt(2, discount.getServiceList().get(i).getIntServiceID());
				preService.addBatch();
			}
			
			preService.executeBatch();
			preService.close();
			
			for(int i = 0; i < discount.getPackageList().size(); i++){
				prePackage.setInt(1, intDiscountID);
				prePackage.setInt(2, discount.getPackageList().get(i).getIntPackageID());
				prePackage.addBatch();
			}
			
			prePackage.executeBatch();
			prePackage.close();
			
			for(int i = 0; i < discount.getPromoList().size(); i++){
				prePromo.setInt(1, intDiscountID);
				prePromo.setInt(2, discount.getPromoList().get(i).getIntPromoID());
				prePromo.addBatch();
			}
			
			prePromo.executeBatch();
			prePromo.close();
			
			con.commit();
			con.close();
			return "success";
			
		}catch(MySQLIntegrityConstraintViolationException m){
			return "existing";
		}
		catch(SQLException e){
			e.printStackTrace();
			con.rollback();
			return "failed";
		}
	}

}
