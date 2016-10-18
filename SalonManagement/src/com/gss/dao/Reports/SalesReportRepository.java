package com.gss.dao.Reports;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gss.model.Reports.Sales;
import com.gss.model.Reports.SalesReport;
import com.gss.connection.JDBCConnection;
import com.gss.model.Reports.SalesReportDetail;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ReportDate;

public class SalesReportRepository {
	
	public static JDBCConnection jdbc = new JDBCConnection();
	
	public static SalesReport getSalesReport(List<ReportDate> dateList, String type){
		
		Connection con = jdbc.getConnection();
		
		SalesReport report = null;
		List<SalesReportDetail> details = new ArrayList<SalesReportDetail>();
		
		String query = "CALL getSalesTotal(?, ?);";
		
		try{
			
			PreparedStatement get = con.prepareStatement(query);
			ResultSet set = null;
			
			for(int index = 0; index < dateList.size(); index++){

				ReportDate date = dateList.get(index);
				
				get.setString(1, date.getDateFrom());
				get.setString(2, date.getDateTo());
				
				set = get.executeQuery();
				
				while(set.next()){
					
					double homeService = (double) Math.round(set.getDouble(1) * 100) / 100;
					double eventService = (double) Math.round(set.getDouble(2) * 100) / 100;
					double walkin = (double) Math.round(set.getDouble(3) * 100) / 100;
					double delivery = (double) Math.round(set.getDouble(4) * 100) / 100;
					double pickup = (double) Math.round(set.getDouble(5) * 100) / 100;
					
					if(type.equalsIgnoreCase("monthly")){
						details.add(new SalesReportDetail(DateHelper.intMonthToString(index+1), homeService, eventService, walkin, delivery, pickup));
					}
					else if(type.equalsIgnoreCase("quarterly")){
						details.add(new SalesReportDetail("Quarter " + (index + 1), homeService, eventService, walkin, delivery, pickup));
					}
					else{
						String[] dateString = date.getDateFrom().split("-");
						details.add(new SalesReportDetail(dateString[0], homeService, eventService, walkin, delivery, pickup));
					}
				}
			}
			
			report = new SalesReport(type, details);
			
			set.close();
			get.close();
			con.close();
			
			return report;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static SalesReport getSalesReport(ReportDate date, String type){
		
		Connection con = jdbc.getConnection();
		
		SalesReport report = null;
		List<SalesReportDetail> details = new ArrayList<SalesReportDetail>();
		
		String query = "CALL getSalesTotal(?, ?);";
		
		try{
			
			PreparedStatement get = con.prepareStatement(query);
			ResultSet set = null;
			
				
				get.setString(1, date.getDateFrom());
				get.setString(2, date.getDateTo());
				
				set = get.executeQuery();
				
				while(set.next()){
					
					double homeService = set.getDouble(1);
					double eventService = set.getDouble(2);
					double walkin = set.getDouble(3);
					double delivery = set.getDouble(4);
					double pickup = set.getDouble(5);
					
					LocalDateTime now = LocalDateTime.now();
			    	int month = now.getMonthValue();
					
			    	if(type.equalsIgnoreCase("month"))
			    		details.add(new SalesReportDetail(DateHelper.intMonthToString(month+1), homeService, eventService, walkin, delivery, pickup));
			    	else
			    		details.add(new SalesReportDetail("This Week", homeService, eventService, walkin, delivery, pickup));
				}
			
			
			report = new SalesReport(type, details);
			
			set.close();
			get.close();
			con.close();
			
			return report;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public static List<Integer> getTotalClient(ReportDate date){
		
		List<Integer> clients = new ArrayList<Integer>();
		Connection con = new JDBCConnection().getConnection();
		try{
			
			PreparedStatement statement = con.prepareStatement("CALL getTotalClients(?, ?);");
			statement.setString(1, date.getDateFrom());
			statement.setString(2, date.getDateTo());
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				clients.add(set.getInt(1));
				clients.add(set.getInt(2));
				clients.add(set.getInt(3));
			}
			
			
			statement.close();
			set.close();
			con.close();
			return clients;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Sales> getSales(ReportDate date){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query1 = "CALL getSales_walkin(?, ?);";
		String query2 = "CALL getSales_reservation(?, ?);";
		String query3 = "CALL getSales_order(?, ?);";
		
		String[] queries = {query1, query2, query3};
		List<Sales> sales = new ArrayList<Sales>();
		
		try{
			
			for(int i = 0; i < queries.length; i++){
				PreparedStatement statement = con.prepareStatement(queries[i]);
				statement.setString(1, date.getDateFrom());
				statement.setString(2, date.getDateTo());
				
				ResultSet set = statement.executeQuery();
				
				while(set.next()){
					int transID 	= set.getInt(1);
					String type 	= set.getString(2);
					String name 	= set.getString(3);
					Date transDate	= set.getDate(4);
					double total	= set.getDouble(5);
					
					Sales sale = new Sales(transID, type, name, transDate, total);
					sales.add(sale);
				}
				
				statement.close();
				set.close();
			}
			
			con.close();
			
			return sales;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	

}
