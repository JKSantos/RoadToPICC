package com.gss.dao.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.EmployeeJob;
import com.gss.model.Reservation;
import com.gss.utilities.DateHelper;

public class EmployeeJobRepository {
	
	static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<EmployeeJob> getWalkInJob(int intEmpID){
		
		Connection con = jdbc.getConnection();
		String walkinService = "CALL getWalkInService(?);";
		String packageService = "CALL getPackageService(?);";
		String promoService = "CALL getPromoService(?);";
		String packagePromoService = "CALL getPackagePromoService(?);";
		
		List<EmployeeJob> jobList = new ArrayList<EmployeeJob>();
		
		try{
			PreparedStatement walkinServiceStmt = con.prepareStatement(walkinService);
			PreparedStatement packageServiceStmt = con.prepareStatement(packageService);
			PreparedStatement promoServiceStmt = con.prepareStatement(promoService);
			PreparedStatement packagePromoServiceStmt = con.prepareStatement(packagePromoService);
			
			walkinServiceStmt.setInt(1, intEmpID);
			ResultSet walkinServiceRes = walkinServiceStmt.executeQuery();
			
			while(walkinServiceRes.next()){
				int jobID 				= walkinServiceRes.getInt(1);
				String transType 		= "WALKIN";
				String jobType			= "SERVICE";
				List<String> jobDetail 	= getCustomerName("walkin", walkinServiceRes.getInt(2));
				String strServiceName 	= getServiceName(walkinServiceRes.getInt(3));
				String strStatus 		= walkinServiceRes.getString(5);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDetail.get(1), jobDetail.get(0), strServiceName, strStatus);
				jobList.add(job);
			}
			
			walkinServiceStmt.close();
			walkinServiceRes.close();
			
			packageServiceStmt.setInt(1, intEmpID);
			ResultSet packageServiceRes = packageServiceStmt.executeQuery();
			
			while(packageServiceRes.next()){
				int jobID 				= packageServiceRes.getInt(1);
				String transType 		= "WALKIN";
				String jobType			= "PACKAGE";
				List<String> jobDetail 	= getCustomerName("packagewalkin", packageServiceRes.getInt(2));
				String strServiceName 	= getServiceName(packageServiceRes.getInt(4));
				String strStatus 		= packageServiceRes.getString(5);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDetail.get(1), jobDetail.get(0), strServiceName, strStatus);
				jobList.add(job);
			}
			
			packageServiceStmt.close();
			packageServiceRes.close();
			
			promoServiceStmt.setInt(1, intEmpID);
			ResultSet promoServiceRes = promoServiceStmt.executeQuery();
			
			while(promoServiceRes.next()){
				int jobID 				= promoServiceRes.getInt(1);
				String transType 		= "WALKIN";
				String jobType			= "PROMO";
				List<String> jobDetail 	= getCustomerName("promowalkinservice", promoServiceRes.getInt(2));
				String strServiceName 	= getServiceName(promoServiceRes.getInt(3));
				String strStatus 		= promoServiceRes.getString(5);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDetail.get(1), jobDetail.get(0), strServiceName, strStatus);
				jobList.add(job);
			}
			
			promoServiceStmt.close();
			promoServiceRes.close();
			
			packagePromoServiceStmt.setInt(1, intEmpID);
			ResultSet packagePromoServiceRes = packagePromoServiceStmt.executeQuery();
			
			while(packagePromoServiceRes.next()){
				int jobID 				= packagePromoServiceRes.getInt(1);
				String transType 		= "WALKIN";
				String jobType			= "PROMO PACKAGE";
				List<String> jobDetail 	= getCustomerName("promopackagewalkin", packagePromoServiceRes.getInt(2));
				String strServiceName 	= getServiceName(packagePromoServiceRes.getInt(4));
				String strStatus 		= packagePromoServiceRes.getString(5);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDetail.get(1), jobDetail.get(0), strServiceName, strStatus);
				jobList.add(job);
			}
			
			packagePromoServiceStmt.close();
			packagePromoServiceRes.close();
			
			return jobList;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static List<String> getCustomerName(String type, int transID){
		
		Connection con = jdbc.getConnection();
		
		String query = "";
		
		if(type.equals("walkin"))
			query = "SELECT strName, dtmDateTime FROM tblWalkIn WHERE intWalkInID = ?;";
		else if(type.equals("packagewalkin"))
			query = "CALL getWalkInDetails(?);";
		else if(type.equals("promowalkinservice"))
			query = "CALL getPromoWalkInID(?);";
		else if(type.equals("promopackagewalkin"))
			query = "CALL getPackagePromoService(?);";
		else if(type.equals("reservation"))
			query = "SELECT strName, datDateFrom FROM tblReservation WHERE intReservationID = ?;";
		else
			query = "SELECT strName, dateOrderDate FROM tblOrder WHERE intOrderID = ?;";

		String customerName = "";
		String jobDate = "";
		
		List<String> jobDetail = new ArrayList<String>();
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, transID);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				 customerName = set.getString(1);
				 jobDate = DateHelper.stringDate(set.getDate(2));
				 jobDetail.add(customerName);
				 jobDetail.add(jobDate);
			}
			
			statement.close();
			set.close();
			
			con.close();
			
			return jobDetail;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String getServiceName(int serviceID){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strServiceName FROM tblService WHERE intServiceID = ?;";
		String serviceName = "";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, serviceID);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				serviceName = set.getString(1);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return serviceName;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<EmployeeJob> getReservationJob(int intEmpID){
		
		Connection con = jdbc.getConnection();
		
		String query = "CALL getReservationJob(?);";
		
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, intEmpID);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				int jobID = set.getInt(1);
				int transID = set.getInt(2);
				List<String> details = getReservationType(transID);
				String transType = details.get(0);
				String jobDate = details.get(1);
				String jobType = "NOT SPECIFIED";
				
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static List<String> getReservationType(int id){
		Connection con = jdbc.getConnection();
		
		String query = "CALL getReservationType(?);";
		String type = "";
		String date = "";
		List<String> details = new ArrayList<String>();
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				type = Reservation.toString(set.getInt(1));
				date = DateHelper.stringDate(set.getDate(2));
				details.add(type);
				details.add(date);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return details;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
