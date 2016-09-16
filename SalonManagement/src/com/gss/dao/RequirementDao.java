package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Requirement;

public class RequirementDao {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static int createRequirement(String requirementName){
		
		Connection con = jdbc.getConnection();
		
		String query = "CALL createRequirement(?);";
		String result = "";
		int id = 0;
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, requirementName.trim().toUpperCase());
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
				id = set.getInt(2);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return id;
			
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public static String removeRequirement(int requirementID){
		
		Connection con = jdbc.getConnection();
		
		String query = "UPDATE tblRequirement SET intStatus = 0 WHERE intRequirementID = ?;";
		String result = "success";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, requirementID);
			statement.execute();
			statement.close();
			con.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
		
	}
	
	public static List<Requirement> getAllRequirement(){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT * FROM tblRequirement WHERE intStatus = 1;";
		
		List<Requirement> requirements = new ArrayList<Requirement>();
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				Requirement requirement = new Requirement(set.getInt(1), set.getString(2), set.getInt(3));
				requirements.add(requirement);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return requirements;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
}
