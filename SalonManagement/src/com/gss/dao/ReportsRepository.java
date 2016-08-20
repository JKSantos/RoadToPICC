package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.ProductOrderSalesReport;
import com.gss.model.ReservationSalesReport;
import com.gss.model.WalkInSalesReport;

public class ReportsRepository {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<ReservationSalesReport> getReservationSales(){
		Connection con = jdbc.getConnection();
		
		String getreservation 					= "CALL getReservationSales()";	
		List<ReservationSalesReport> list		= new ArrayList<ReservationSalesReport>();
		
		try{
			PreparedStatement reservation 		= con.prepareStatement(getreservation);
			
			ResultSet set1 = reservation.executeQuery();
			
			while(set1.next()){
				int intID = set1.getInt(1);
				String type = ReservationSalesReport.toString(set1.getInt(2));
				Date dateTime = set1.getDate(3);
				String name = set1.getString(4);
				double amount = set1.getDouble(8);
				
				ReservationSalesReport report = new ReservationSalesReport(intID, type, (java.sql.Date) dateTime, name, amount);
				list.add(report);
			}
			
			reservation.close();
			set1.close();
			con.close();
			
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<WalkInSalesReport> getWalkInSales(){
		Connection con = jdbc.getConnection();
		
		
		String getwalkin						= "CALL getWalkInSales()";
		List<WalkInSalesReport> list		= new ArrayList<WalkInSalesReport>();
		
		try{
			PreparedStatement walkin			= con.prepareStatement(getwalkin);
			
			ResultSet set1 						= walkin.executeQuery();
			
			while(set1.next()){
				int intID = set1.getInt(1);
				Date dateTime = set1.getDate(2);
				String name = set1.getString(3);
				double amount = set1.getDouble(4);
				
				WalkInSalesReport report = new WalkInSalesReport(intID, (java.sql.Date) dateTime, name, amount);
				list.add(report);
			}
			
			walkin.close();
			set1.close();
			con.close();
			
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<ProductOrderSalesReport> getProductOrderSales(){
		
		Connection con = jdbc.getConnection();
		
		
		String getorder							= "CALL getProductOrderSales()";
		List<ProductOrderSalesReport> list 		= new ArrayList<ProductOrderSalesReport>();
		try{
			PreparedStatement prepare			= con.prepareStatement(getorder);
				
			ResultSet set1 						= prepare.executeQuery();
			
			while(set1.next()){
				int intID = set1.getInt(1);
				Date dateTime = set1.getDate(2);
				String type = ProductOrderSalesReport.toString(set1.getInt(3));
				String name = set1.getString(4);
				double amount = set1.getDouble(8);
				
				ProductOrderSalesReport report = new ProductOrderSalesReport(intID, (java.sql.Date) dateTime, type, name, amount);
				list.add(report);
			}
			
			prepare.close();
			set1.close();
			con.close();
			
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}

		
		
	}

}
