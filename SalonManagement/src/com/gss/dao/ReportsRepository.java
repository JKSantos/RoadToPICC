package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Reports.ProductOrderSalesReport;
import com.gss.model.Reports.ProductOrderTotalSales;
import com.gss.model.Reports.ReservationSalesReport;
import com.gss.model.Reports.ReservationTotalSales;
import com.gss.model.Reports.WalkInSalesReport;
import com.gss.model.Reports.WalkInTotalSales;

public class ReportsRepository {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<ReservationSalesReport> getReservationSales(String dateFrom, String dateTo){
		Connection con = jdbc.getConnection();
		
		String getreservation 					= "CALL getReservationSales(?, ?)";	
		List<ReservationSalesReport> list		= new ArrayList<ReservationSalesReport>();
		
		try{
			PreparedStatement reservation 		= con.prepareStatement(getreservation);
			reservation.setString(1, dateFrom);
			reservation.setString(2, dateTo);
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
	
	public static List<WalkInSalesReport> getWalkInSales(String dateFrom, String dateTo){
		Connection con = jdbc.getConnection();
		
		
		String getwalkin						= "CALL getWalkInSales(?, ?)";
		List<WalkInSalesReport> list		= new ArrayList<WalkInSalesReport>();
		
		try{
			PreparedStatement walkin			= con.prepareStatement(getwalkin);
			walkin.setString(1, dateFrom);
			walkin.setString(2, dateTo);
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
	
	public static List<ProductOrderSalesReport> getProductOrderSales(String dateFrom, String dateTo){
		
		Connection con = jdbc.getConnection();
		
		
		String getorder							= "CALL getProductOrderSales(?, ?)";
		List<ProductOrderSalesReport> list 		= new ArrayList<ProductOrderSalesReport>();
		try{
			PreparedStatement prepare			= con.prepareStatement(getorder);
			prepare.setString(1, dateFrom);
			prepare.setString(2, dateTo);
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

	public static List<ReservationTotalSales> getReservationTotalSales(String dateFrom, String dateTo){
		Connection con = jdbc.getConnection();
		
		String getorder							= "CALL getSalesTotal(?, ?)";
		List<ReservationTotalSales> sales		= new ArrayList<ReservationTotalSales>();
		try{
			PreparedStatement prepare			= con.prepareStatement(getorder);
			prepare.setString(1, dateFrom);
			prepare.setString(2, dateTo);
			ResultSet set1 						= prepare.executeQuery();
			
			while(set1.next()){
				double homeService = set1.getDouble(1);
				double event = set1.getDouble(2);
				
				sales.add(new ReservationTotalSales(dateFrom, dateTo, "Home Service", homeService));
				sales.add(new ReservationTotalSales(dateFrom, dateTo, "Event Service", event));
			}
			
			prepare.close();
			set1.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ProductOrderTotalSales> getProductOrderTotalSales(String dateFrom, String dateTo){
		Connection con = jdbc.getConnection();
		
		String getorder							= "CALL getSalesTotal(?, ?)";
		List<ProductOrderTotalSales> sales		= new ArrayList<ProductOrderTotalSales>();
		try{
			PreparedStatement prepare			= con.prepareStatement(getorder);
			prepare.setString(1, dateFrom);
			prepare.setString(2, dateTo);
			ResultSet set1 						= prepare.executeQuery();
			
			while(set1.next()){
				double delivery = set1.getDouble(4);
				double pickup = set1.getDouble(5);
				
				sales.add(new ProductOrderTotalSales(dateFrom, dateTo, "Delivery", delivery));
				sales.add(new ProductOrderTotalSales(dateFrom, dateTo, "Pick Up", pickup));
			}
			
			prepare.close();
			set1.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<WalkInTotalSales> getWalkInTotalSales(String dateFrom, String dateTo){
		Connection con = jdbc.getConnection();
		
		String getorder							= "CALL getSalesTotal(?, ?)";
		List<WalkInTotalSales> sales		= new ArrayList<WalkInTotalSales>();
		try{
			PreparedStatement prepare			= con.prepareStatement(getorder);
			prepare.setString(1, dateFrom);
			prepare.setString(2, dateTo);
			ResultSet set1 						= prepare.executeQuery();
			
			while(set1.next()){
				double walkin = set1.getDouble(3);
				sales.add(new WalkInTotalSales(dateFrom, dateTo, walkin));
			}
			
			prepare.close();
			set1.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
