package com.gss.dao;

import java.util.List;

import com.gss.connection.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.gss.model.ReservationSalesReport;

public interface ReportsJDBCRepository {
	
	JDBCConnection jdbc = new JDBCConnection();
	
	
	public List<ReservationSalesReport> getReservationSales(){
		
		Connection con 							= jdbc.getConnection();
		
		String getreservation 					= "CALL getReservationSales()";	
		String getwalkin						= "CALL getWalkInSales()";
		String getorder							= "CALL getProductOrderSales()";
		
		try{
			PreparedStatement reservation 		= con.prepareStatement(getreservation);
			PreparedStatement walkin			= con.prepareStatement(getwalkin);
			PreparedStatement prepare			= con.prepareStatement(getorder);
			
		}
		catch(){
			
		}
	}

}
