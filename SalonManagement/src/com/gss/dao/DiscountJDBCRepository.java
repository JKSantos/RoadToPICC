package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;

public class DiscountJDBCRepository implements DiscountRepository{

	JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public List<Discount> getAllDiscount() {
		
		Connection con = jdbc.getConnection();
		List<Discount> discountList = new ArrayList<Discount>();
		String strQuery = "SELECT * FROM tblDiscount WHERE intStatus = 1;";
		ResultSet set;
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery);
			set = pre.executeQuery();
			
			while(set.next()){
				
				int intID = set.getInt(1);
				String strName = set.getString(2);
				String strDesc = set.getString(3);
				String strGuide = set.getString(4);
				int intType = set.getInt(5);
				double dblAmount = set.getDouble(6);
				int status = set.getInt(7);
				
				Discount discount = new Discount(intID, strName, strDesc, strGuide, intType, dblAmount, status);
				discountList.add(discount);
			}
			
			return discountList;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean createDiscount(Discount discount) {
		
		Connection con = jdbc.getConnection();
		String strQuery = "CALL createDiscount(?, ? ,?, ?, ?)";
		
		try{
			PreparedStatement pre = con.prepareStatement(strQuery);
			pre.setString(1, discount.getStrDiscountName());
			pre.setString(2, discount.getStrDiscountDesc());
			pre.setString(3, discount.getStrDiscountGuidelines());
			pre.setInt(4, discount.getIntDiscountType());
			pre.setDouble(5, discount.getDblDiscountAmount());
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean updateDiscount(Discount discount) {
		
		Connection con = jdbc.getConnection();
		String query = "CALL updateDiscount(?, ?, ?, ?, ?)";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, discount.getIntDiscountID());
			pre.setString(2, discount.getStrDiscountName());
			pre.setString(3, discount.getStrDiscountDesc());
			pre.setString(4, discount.getStrDiscountGuidelines());
			pre.setInt(5, discount.getIntDiscountType());
			pre.setDouble(6, discount.getDblDiscountAmount());
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean deactivateDiscount(int intDiscountID) {

		Connection con = jdbc.getConnection();
		String query = "UPDATE tblDiscount SET intStatus = 0 WHERE intDiscountID = ?";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intDiscountID);

			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}
	
}
