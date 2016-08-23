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
import com.gss.model.Reports.ProductPurchaseTotal;
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

	public static List<ProductPurchaseTotal> getProductPurchasesTotal(String dateFrom, String dateTo){
		
		Connection con = jdbc.getConnection();
		
		String products						= "SELECT intProductID FROM tblProduct WHERE intProdStatus = 1;";
		String orderString					= "CALL getProductOrderProductSalesTotal(?, ?, ?)";
		String reservString					= "CALL getReservationProductSalesTotal(?, ?, ?)";
		String walkinString					= "CALL getWalkInProductSalesTotal(?, ?, ?)";
		
		
		List<ProductPurchaseTotal> sales			= new ArrayList<ProductPurchaseTotal>();
		
		try{

			PreparedStatement productSet			= con.prepareStatement(products);
			PreparedStatement orderTotal			= con.prepareStatement(orderString);
			PreparedStatement reservTotal			= con.prepareStatement(reservString);
			PreparedStatement walkinTotal			= con.prepareStatement(walkinString);
			
			ResultSet productIDs					= productSet.executeQuery();
			ResultSet orderSet						= null;
			ResultSet reservSet						= null;
			ResultSet walkinSet						= null;
			
			
			while(productIDs.next()){
				
				int intProductID = productIDs.getInt(1);
				String strProductName = null;
				int intorderTotal = 0;
				int intreservTotal = 0;
				int intwalkinTotal = 0;
				
				orderTotal.setString(1, dateFrom);
				orderTotal.setString(2, dateTo);
				orderTotal.setInt(3, intProductID);
				orderSet = orderTotal.executeQuery();
				
				while(orderSet.next()){
					strProductName = orderSet.getString(2);
					intorderTotal = orderSet.getInt(3);
				}
				
				reservTotal.setString(1, dateFrom);
				reservTotal.setString(2, dateTo);
				reservTotal.setInt(3, intProductID);
				reservSet = reservTotal.executeQuery();
				
				while(reservSet.next()){
					intreservTotal = reservSet.getInt(3);
				}
				
				walkinTotal.setString(1, dateFrom);
				walkinTotal.setString(2, dateTo);
				walkinTotal.setInt(3, intProductID);
				walkinSet = walkinTotal.executeQuery();
				
				while(walkinSet.next()){
					intwalkinTotal = walkinSet.getInt(3);
				}
				
				
				sales.add(new ProductPurchaseTotal(dateFrom, dateTo, intProductID, strProductName, intorderTotal, intreservTotal, intwalkinTotal));
			}
			
			orderTotal.close();
			reservTotal.close();
			walkinTotal.close();
			walkinSet.close();
			orderSet.close();
			reservSet.close();
			productIDs.close();
			productSet.close();
			con.close();
			
			return sales;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
