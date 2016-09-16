package com.gss.dao.Specialization;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gss.connection.JDBCConnection;
import com.gss.model.ServiceCategory;

public class ServiceCategoryDao {
	
	static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<ServiceCategory> getAllServiceCategory(){
		
		List<ServiceCategory> categories = new ArrayList<ServiceCategory>();
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT * FROM tblServiceCategory WHERE intStatus = 1;";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				int id 			= set.getInt(1);
				String name 	= set.getString(2);
				categories.add(new ServiceCategory(id, name));
			}
			
			statement.close();
			set.close();
			con.close();
			
			return categories;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

}
