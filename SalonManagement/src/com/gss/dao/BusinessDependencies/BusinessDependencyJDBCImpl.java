package com.gss.dao.BusinessDependencies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Dependency;

public class BusinessDependencyJDBCImpl implements BusinessDependencyRepository{

	private JDBCConnection jdbc =  new JDBCConnection();
	
	@Override
	public boolean updateDependencies(List<Dependency> dependency) throws SQLException {
		
		Connection con 					= jdbc.getConnection();
		String insertDep				= "CALL insertDependencies(?, ?);";
		String delete					= "DELETE FROM tblBusinessDependency;";
		
		con.setAutoCommit(false); 
		
		try{
			PreparedStatement statement = con.prepareStatement(insertDep);
			PreparedStatement deleteSt	= con.prepareStatement(delete);
			
			deleteSt.execute();
			
			for(int index = 0; index < dependency.size(); index++){
				Dependency dep = dependency.get(index);
				statement.setString(1, dep.getStrName());
				statement.setString(2, dep.getStrValue());
				statement.addBatch();
			}
			
			statement.executeBatch();
			statement.clearBatch();
			statement.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}

	@Override
	public List<Dependency> getAllDependencies() {
		
		Connection con 					= jdbc.getConnection();
		String getDep					= "SELECT * FROM tblBusinessDependency;";
		List<Dependency> dependencies	= new ArrayList<Dependency>();
		
		try{
			PreparedStatement statement = con.prepareStatement(getDep);
			ResultSet dependency		= statement.executeQuery();
			
			while(dependency.next()){
				int intID 				= dependency.getInt(1);
				String name 			= dependency.getString(2);
				String value 			= dependency.getString(3);
				
				dependencies.add(new Dependency(intID, name, value));
			}
			
			statement.executeBatch();
			statement.clearBatch();
			statement.close();
			
			con.close();
			return dependencies;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
