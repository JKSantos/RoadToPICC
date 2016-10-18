package com.gss.dao.Reports;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Reports.ServiceAvailment;
import com.gss.utilities.ReportDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceAvailmentRepository {
	
	public static List<ServiceAvailment> getServiceAvailed(ReportDate date){
		Connection con = new JDBCConnection().getConnection();
		
		String query1 	 = "CALL getServiceAvailments_reservation(?, ?);";
		String query2 	 = "CALL getServiceAvailments_walkin(?, ?);";
		
		String[] queries = {query1, query2};
		List<ServiceAvailment> services = new ArrayList<ServiceAvailment>();
		
		try{
			
			for(int i = 0; i < queries.length; i++){
				PreparedStatement statement = con.prepareStatement(queries[i]);
				statement.setString(1, date.getDateFrom());
				statement.setString(2, date.getDateTo());
				
				ResultSet set = statement.executeQuery();
				
				while(set.next()){
					int transID 	= set.getInt(1);
					String type 	= set.getString(2);
					Date datee  	= set.getDate(3);
					String name 	= set.getString(4);
					String service  = set.getString(5);
					
					ServiceAvailment avail = new ServiceAvailment(transID, type, datee, name, service);
					services.add(avail);
				}
				
				statement.close();
				set.close();
			}
			
			con.close();
			return services;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
