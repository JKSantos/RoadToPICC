package com.gss.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Service;
import com.gss.model.ServiceCategory;

public class ServiceJDBCRepository implements ServiceRepository{

	static JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public List<Service> getAllService() {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL fetchServices()";
		String strQuery2 = "CALL fetchPrice(?)";
		List<Service> serviceList = new ArrayList<Service>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
						
			while(set.next()){
				
				Service service;
				int intServiceID = set.getInt(1);
				String strServiceName = set.getString(2);
				String strServiceCate = set.getString(3);
				int intServiceStatus = set.getInt(4);
				String strServiceDesc = set.getString(5);
				byte[] actualPhoto = set.getBytes(6);
				String strPhotoPath = "localhost:8080/SalonManagement/getImage?ImageID="+ intServiceID + "&type=service";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intServiceID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					service = new Service(intServiceID, strServiceName, strServiceCate, intServiceStatus, strServiceDesc, price, actualPhoto, strPhotoPath);
					serviceList.add(service);
					
				}
				
				pre2.close();
				set2.close();
			}
			
			set.close();
			pre.close();
			con.close();
			
			return serviceList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean createService(Service service) {

		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL createService(?, ?, ?, ?, ?)";
		String strQuery2 = "CALL createServicePrice(?, ?)";
		int intServID = 0;
		
		try{
			
			File file = new File(service.getStrPhotoPath());
			FileInputStream fis = new FileInputStream(file);
			
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			
			pre1.setString(1, service.getStrServiceName());
			pre1.setString(2, service.getStrServiceCategory());
			pre1.setInt(3, service.getIntServiceStatus());
			pre1.setString(4, service.getStrServiceDesc());
			pre1.setBinaryStream(5, (InputStream)fis, (int)file.length());
			
			ResultSet set = pre1.executeQuery();
			while(set.next()){
				intServID = set.getInt(1);
			}
			pre1.close();
			
			PreparedStatement pre2 = con.prepareStatement(strQuery2);
			pre2.setInt(1, intServID);
			pre2.setDouble(2, service.getDblServicePrice());
			pre2.execute();
			pre2.close();
			con.close();
			
			return true;
		}
		catch(SQLException e){
			return false;
		}
		catch(NullPointerException a){
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateService(Service service) {
		
		String strQuery1 = "CALL updateService(?, ?, ?, ?, ?, ?)";
		String strQuery2 = "CALL updatePrice(?, ?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
	
		
		try{
			PreparedStatement pre1 = con.prepareStatement(strQuery1);
			double price = 0.00;
			
			if(!service.getStrPhotoPath().equalsIgnoreCase("image")){
				File file = new File(service.getStrPhotoPath());
				FileInputStream fis = new FileInputStream(file);
				
				pre1.setInt(1, service.getIntServiceID());
				pre1.setString(2, service.getStrServiceName());
				pre1.setString(3, service.getStrServiceCategory());
				pre1.setInt(4, service.getIntServiceStatus());
				pre1.setString(5, service.getStrServiceDesc());
				pre1.setBinaryStream(6, (InputStream)fis, (int)file.length());
			}
			else{
				pre1.setInt(1, service.getIntServiceID());
				pre1.setString(2, service.getStrServiceName());
				pre1.setString(3, service.getStrServiceCategory());
				pre1.setInt(4, service.getIntServiceStatus());
				pre1.setString(5, service.getStrServiceDesc());
				pre1.setInt(6, 101);
			}
			
			ResultSet set = pre1.executeQuery();
			
			while(set.next()){
				
				price = set.getDouble(1);
			}
			
			if(price != service.getDblServicePrice()){
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, service.getIntServiceID());
				pre2.setDouble(2, service.getDblServicePrice());
				pre2.execute();
				pre2.close();
				con.close();
				return true;
			}
			else{
				con.close();
				return true;
				
			}
		}
		catch(SQLException | FileNotFoundException e){
			
			System.out.println(e.getMessage() + " ...." + e.fillInStackTrace() );
			return false;
		}
	}

	@Override
	public List<String> getAllCategory() {
		
		List<String> categoryList = new ArrayList<String>();
		Connection con = new JDBCConnection().getConnection();
		String query = "SELECT strServiceCategory FROM tblServiceCategory;";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				categoryList.add(set.getString(1));
			}
			
			pre.close();
			set.close();
			con.createStatement();
			
			return categoryList;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean deactivateService(int intServiceID) {
		
		Connection con = new JDBCConnection().getConnection();
		String query = "UPDATE tblService SET intServiceStatus = 0 WHERE intServiceID = ?;";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intServiceID);
			
			pre.execute();
			pre.close();
			con.createStatement();
			
			return true;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<Service> getAllServiceNoImage() {
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL fetchServices()";
		String strQuery2 = "CALL fetchPrice(?)";
		List<Service> serviceList = new ArrayList<Service>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
						
			while(set.next()){
				
				Service service;
				int intServiceID = set.getInt(1);
				String strServiceName = set.getString(2);
				String strServiceCate = set.getString(3);
				int intServiceStatus = set.getInt(4);
				String strServiceDesc = set.getString(5);
				byte[] actualPhoto = null;
				String strPhotoPath = ":8080/SalonManagement/getImage?ImageID="+ intServiceID + "&type=service";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intServiceID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					service = new Service(intServiceID, strServiceName, strServiceCate, intServiceStatus, strServiceDesc, price, actualPhoto, strPhotoPath);
					serviceList.add(service);
					
				}
				
				pre2.close();
				set2.close();
			}
			
			set.close();
			pre.close();
			con.close();
			
			return serviceList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public List<Service> queryAllService() {
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String strQuery1 = "CALL queryAllServices()";
		String strQuery2 = "CALL fetchPrice(?)";
		List<Service> serviceList = new ArrayList<Service>();
		
		
		try{
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			ResultSet set = pre.executeQuery();
			ResultSet set2;
						
			while(set.next()){
				
				Service service;
				int intServiceID = set.getInt(1);
				String strServiceName = set.getString(2);
				String strServiceCate = set.getString(3);
				int intServiceStatus = set.getInt(4);
				String strServiceDesc = set.getString(5);
				byte[] actualPhoto = null;
				String strPhotoPath = ":8080/SalonManagement/getImage?ImageID="+ intServiceID + "&type=service";
				
				PreparedStatement pre2 = con.prepareStatement(strQuery2);
				pre2.setInt(1, intServiceID);
				
				set2 = pre2.executeQuery();
				
				while(set2.next()){
					double price = set2.getDouble(1);
					service = new Service(intServiceID, strServiceName, strServiceCate, intServiceStatus, strServiceDesc, price, actualPhoto, strPhotoPath);
					serviceList.add(service);
					
				}
				
				pre2.close();
				set2.close();
			}
			
			set.close();
			pre.close();
			con.close();
			
			return serviceList;
		}
		catch(Exception e){
			
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	public static List<ServiceCategory> getCategories(){
		Connection con = jdbc.getConnection();
				
		String query = "SELECT * FROM tblServiceCategory WHERE intStatus = 1;";
		try{
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
			
			List<ServiceCategory> categoryList = new ArrayList<ServiceCategory>();
			
			while(set.next()){
				int id = set.getInt(1);
				String category = set.getString(2);
				
				categoryList.add(new ServiceCategory(id, category));
			}
			
			statement.close();
			set.close();
			con.close();
			
			return categoryList;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean category(String categoryName, int type){
		
		Connection con = jdbc.getConnection();
		
		String query = "";
		
		if(type == 1)
			query = "CALL addServiceCategory(?);";
		else
			query = "CALL removeServiceCategory(?);";
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, categoryName.trim().toUpperCase());
			
			statement.execute();
			
			statement.close();
			con.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static String searchCategory(String categoryName){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT intServiceCateCode FROM tblServiceCategory WHERE strServiceCategory = ? AND intStatus = 1;";

		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, categoryName.trim().toUpperCase());
			
			ResultSet set = statement.executeQuery();
			int id = 0;
			
			while(set.next()){
				id = set.getInt(1);
			}
			
			statement.close();
			set.close();
			con.close();
			
			if(id == 0)
				return "OK";
			else
				return "EXISTING";
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<String> getServiceNames(){
		
		Connection con = jdbc.getConnection();
		List<String> names = new ArrayList<String>();
		
		String query = "SELECT strServiceName FROM tblService WHERE intServiceStatus = 1;";

		try{
			
			PreparedStatement statement = con.prepareStatement(query);	
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String name = set.getString(1);
				
				names.add(name);
			}
			
			statement.close();
			set.close();
			con.close();
			return names;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<String> getServiceNames(int id){
		
		Connection con = jdbc.getConnection();
		List<String> names = new ArrayList<String>();
		
		String query = "SELECT strServiceName FROM tblService WHERE intServiceStatus = 1 AND intServiceID <> ?;";

		try{
			
			PreparedStatement statement = con.prepareStatement(query);	
			statement.setInt(1, id);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String name = set.getString(1);
				
				names.add(name);
			}
			
			statement.close();
			set.close();
			con.close();
			return names;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
