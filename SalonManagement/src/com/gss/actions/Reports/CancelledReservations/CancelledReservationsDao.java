package com.gss.actions.Reports.CancelledReservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Reports.CancelledReservation;
import com.gss.model.Reports.SalesReportDetail;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ReportDate;

public class CancelledReservationsDao {
	
	static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<CancelledReservation> getCancelledReservations(List<ReportDate> dateList, String type){
	
		Connection con = jdbc.getConnection();
		
		try{
			List<CancelledReservation> list = new ArrayList<CancelledReservation>();
			PreparedStatement pre = con.prepareStatement("CALL getCancelledReservations_Total(?, ?);");
			
			for(int i = 0; i < dateList.size(); i++){
				ReportDate date = dateList.get(i);
				pre.setString(1, date.getDateFrom());
				pre.setString(2, date.getDateTo());
				
				ResultSet set = pre.executeQuery();
				
				while(set.next()){
					
					int event = set.getInt(1);
					int home = set.getInt(2);
					
					if(type.equalsIgnoreCase("monthly")){
						list.add(new CancelledReservation(event, home));
					}
					else if(type.equalsIgnoreCase("quarterly")){
						list.add(new CancelledReservation(event, home));
					}
					else{
						list.add(new CancelledReservation(event, home));
					}
				}
				
				set.close();
			}
			
			pre.close();
			
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
