package com.gss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.CustomerAccount;
import com.gss.model.Verification;
import com.gss.utilities.RandomStringGenerator;
import com.gss.utilities.SMSSender;

import java.sql.Connection;

public class CustomerRegistration {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static Verification createCustomer(CustomerAccount customer) throws SQLException{
		
		Connection con = jdbc.getConnection();
		
		String query = "CALL createCustomerAccount(?, ?, ?, ?, ?, ?);";
		int accountID = 0;
		int codeID = 0;
		String code = RandomStringGenerator.generateRandomString();
		String message = "Good Day! Your verification code for Salon App is " + code;
		
		try{
			
			con.setAutoCommit(false);
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, customer.getStrCustName());
			statement.setString(2, customer.getStrContactNo());	
			statement.setString(3, customer.getStrEmail());
			statement.setString(4, customer.getStrUsername());
			statement.setString(5, customer.getStrPassword());
			statement.setString(6, code);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				accountID = set.getInt(1);
				codeID = set.getInt(2);
			}
			
			con.commit();
			con.close();
			
			SMSSender sender = new SMSSender();
			sender.sendSMS(message, customer.getStrContactNo());
			
			return new Verification(codeID, code, accountID); 
			
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return new Verification(0, "", 0);
		}
	}
	
	public static boolean updateCustomer(CustomerAccount customer) throws SQLException{
		Connection con = jdbc.getConnection();
		
		String query = "CALL updateCustomerAccount(?, ?, ?, ?, ?, ?);";
		try{
			
			con.setAutoCommit(false);
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, customer.getIntCustID());
			statement.setString(2, customer.getStrCustName());
			statement.setString(3, customer.getStrContactNo());
			statement.setString(4, customer.getStrEmail());
			statement.setString(5, customer.getStrUsername());
			statement.setString(6, customer.getStrPassword());
			
			statement.execute();
			
			con.commit();
			con.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}
	
	public static String verifyAccount(int id, int codeID) throws SQLException{
		Connection con = jdbc.getConnection();
		
		String query = "CALL verifyAccount(?, ?);";
		
		try{
			
			con.setAutoCommit(false);
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			statement.setInt(2, codeID);
			
			statement.executeQuery();
			
			con.commit();
			con.close();
			
			return "success"; 
			
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return "failed";
		}
	}
	
	public static String getVerificationCode(int codeID) throws SQLException{
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strVerificationCode FROM tblVerification WHERE intVerificationID = ?;";
		String code = "";
		try{
			
			con.setAutoCommit(false);
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, codeID);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				code = set.getString(1);
			}
			
			con.commit();
			con.close();
			
			return code; 
			
		}catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return "";
		}
	}
	
	public static String logInCustomer(String username, String password){
		
		Connection con = jdbc.getConnection();
		
		String query = "CALL loginCustomer(?, ?);";
		
		String result = "";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		
	}
	
	public static List<String> getAllUsername(){
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strUsername FROM tblCustomer;";
		List<String> usernameList = new ArrayList<String>();
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String username = set.getString(1);
				usernameList.add(username);
			}
			
			return usernameList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}