package com.gss.dao.Reports;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.WalkIn;
import com.gss.model.Reports.ProductPurchases;

public class ProductSalesReportRepository {
	
	private static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<ProductPurchases> getProductOrderProductPurchases(String dateFrom, String dateTo){
		
		Connection con = jdbc.getConnection();
		
		String getOrder							= "CALL getProductOrderProductSales(?, ?)";
		
		List<ProductPurchases> sales			= new ArrayList<ProductPurchases>();
		try{
			PreparedStatement oderSt			= con.prepareStatement(getOrder);
			
			oderSt.setString(1, dateFrom);
			oderSt.setString(2, dateTo);
			
			ResultSet orderSet					= oderSt.executeQuery();
			
			while(orderSet.next()){
				int transactionID				= orderSet.getInt(1);
				Date transactionDate			= orderSet.getDate(2);
				String transactionName			= "Product Order";
				String transactionType			= ProductSales.toString(orderSet.getInt(3));
				String productName				= orderSet.getString(6);
				int quantity					= orderSet.getInt(7);
				
				sales.add(new ProductPurchases(transactionID, transactionDate, transactionName, transactionType, productName, quantity));
			}
			
			oderSt.close();
			orderSet.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ProductPurchases> getReservationProductPurchases(String dateFrom, String dateTo){
		
		Connection con = jdbc.getConnection();
		
		String getReservation					= "CALL getReservationProductSales(?, ?)";
		
		List<ProductPurchases> sales			= new ArrayList<ProductPurchases>();
		try{
			PreparedStatement reservationSt		= con.prepareStatement(getReservation);
			
			reservationSt.setString(1, dateFrom);
			reservationSt.setString(1, dateTo);
			
			ResultSet reservationSet			= reservationSt.executeQuery();
			
			while(reservationSet.next()){
				int transactionID				= reservationSet.getInt(1);
				Date transactionDate			= reservationSet.getDate(3);	
				String transactionName			= "Reservatation";
				String transactionType			= Reservation.toString(reservationSet.getInt(2));
				String productName				= reservationSet.getString(5);
				int quantity					= reservationSet.getInt(6);
				
				sales.add(new ProductPurchases(transactionID, transactionDate, transactionName, transactionType, productName, quantity));
			}
			
			reservationSt.close();
			reservationSet.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<ProductPurchases> getWalkinProductPurchases(String dateFrom, String dateTo){
		
		Connection con = jdbc.getConnection();
		
		String getWalkin						= "CALL getWalkInProductSales(?, ?)";
		
		List<ProductPurchases> sales			= new ArrayList<ProductPurchases>();
		try{

			PreparedStatement walkinSt			= con.prepareStatement(getWalkin);
			
			walkinSt.setString(1, dateFrom);
			walkinSt.setString(1, dateTo);
			
			ResultSet walkinSet					= walkinSt.executeQuery();
			
			while(walkinSet.next()){
				int transactionID				= walkinSet.getInt(1);
				Date transactionDate			= walkinSet.getDate(2);
				String transactionName			= "Walk In";
				String transactionType			= "";
				String productName				= walkinSet.getString(4);
				int quantity					= walkinSet.getInt(5);
				
				sales.add(new ProductPurchases(transactionID, transactionDate, transactionName, transactionType, productName, quantity));
			}
			
			walkinSt.close();
			walkinSet.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
