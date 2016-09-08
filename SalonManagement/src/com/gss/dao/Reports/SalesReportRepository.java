package com.gss.dao.Reports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
					
					double homeService = set.getDouble(1);
					double eventService = set.getDouble(2);
					double walkin = set.getDouble(3);
					double delivery = set.getDouble(4);
					double pickup = set.getDouble(5);
					
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

}
