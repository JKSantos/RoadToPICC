package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gss.connection.JDBCConnection;

public class CustomerTransactionHelper {
	
	public static boolean insertCustomerAppointment(int walkID, int custID, int type) throws SQLException{
		
		Connection con = new JDBCConnection().getConnection();
		try{
			
			con.setAutoCommit(false);
			
			PreparedStatement statement = con.prepareStatement("CALL insertCustomerAppointment(?, ?, ?);");
			statement.setInt(1, walkID);
			statement.setInt(2, custID);
			statement.setInt(3, type);
			
			statement.execute();
			statement.close();
			
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			con.rollback();
			return false;
		}catch(NullPointerException e){
			e.printStackTrace();
			con.rollback();
			return false;
		}finally{
			con.close();
		}
		
	}

}
