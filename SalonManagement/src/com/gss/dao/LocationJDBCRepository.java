package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Location;

public class LocationJDBCRepository implements LocationRepository{

	private static JDBCConnection jdbc = new JDBCConnection();
	
	public boolean createLocation(Location location) {

		Connection con = jdbc.getConnection();
		String query = "CALL createLocation(?, ?, ?)";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, location.getStrBarangay());
			pre.setString(2, location.getStrCity());
			pre.setDouble(3, location.getDblLocationPrice());
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateLocation(Location location) {
		Connection con = jdbc.getConnection();
		String query = "CALL updateLocation(?, ?, ?, ?)";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, location.getIntLocationID());
			pre.setString(2, location.getStrBarangay());
			pre.setString(3, location.getStrCity());
			pre.setDouble(4, location.getDblLocationPrice());
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean deactivateLocation(int locationID) {


		Connection con = jdbc.getConnection();
		String query = "UPDATE tblLocation SET intLocationStatus = 0 WHERE intLocationID = ?";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, locationID);
	
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Location> getAllLocation() {
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblLocation WHERE intLocationStatus = 1";
		
		List<Location> locationList = new ArrayList<Location>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet result = pre.executeQuery();
			
			while(result.next()){
				
				int intID = result.getInt(1);
				String strBrgy = result.getString(2);
				String strCity = result.getString(3);
				double price = result.getDouble(4);
				int status = result.getInt(5);
				
				Location location = new Location(intID, strBrgy, strCity, price, status);
				location.setStringPrice(String.format("%.2f", price));
				locationList.add(location);
			}
			
			pre.close();
			con.close();
			
			return locationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Location> queryAllLocation() {
		
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblLocation ORDER BY strBarangay, strCity ASC;";
		
		List<Location> locationList = new ArrayList<Location>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet result = pre.executeQuery();
			
			while(result.next()){
				
				int intID = result.getInt(1);
				String strBrgy = result.getString(2);
				String strCity = result.getString(3);
				double price = result.getDouble(4);
				int status = result.getInt(5);
				
				Location location = new Location(intID, strBrgy, strCity, price, status);
				location.setStringPrice(String.format("%.2f", price));
				locationList.add(location);
			}
			
			pre.close();
			con.close();
			
			return locationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String checkLocationName(String brgy, String city){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL checkLocation(?, ?);";
		
		String result = "";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, brgy);
			pre.setString(2, city);
			
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
			}

			pre.close();
			set.close();
			con.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}
	
	public static String checkLocationName(String brgy, String city, int intLocationID){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL checkLocationWithID(?, ?, ?);";
		
		String result = "";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, brgy);
			pre.setString(2, city);
			pre.setInt(3, intLocationID);
			
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
			}

			pre.close();
			set.close();
			con.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}
	
	public static Location getLocationByID(int id) {
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblLocation WHERE intLocationStatus = 1 AND intLocationID = ?;";
		
		Location location = null;
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, id);
			ResultSet result = pre.executeQuery();
			
			while(result.next()){
				
				int intID = result.getInt(1);
				String strBrgy = result.getString(2);
				String strCity = result.getString(3);
				double price = result.getDouble(4);
				int status = result.getInt(5);
				
				location = new Location(intID, strBrgy, strCity, price, status);
				location.setStringPrice(String.format("%.2f", price));
			}
			
			pre.close();
			con.close();
			
			return location;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
