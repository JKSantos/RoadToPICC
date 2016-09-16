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
		List<EmployeeJob> jobList = new ArrayList<EmployeeJob>();
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
				String customerName = details.get(2);
				String serviceName = "NOT SPECIFIED";
				String status = set.getString(4);
				String venue = details.get(3);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDate, customerName, serviceName, status);
				job.setIntTransID(transID);
				job.setStrAddress(venue);
				
				jobList.add(job);
			}

			statement.close();
			set.close();
			con.close();
			
			return jobList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<String> getReservationType(int id){
		Connection con = jdbc.getConnection();
		
		String query = "CALL getReservationType(?);";
	
		List<String> details = new ArrayList<String>();
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String type 		= Reservation.toString(set.getInt(1));
				String date 		= DateHelper.stringDate(set.getDate(2));
				String customer 	= set.getString(3);
				String address		= set.getString(4);
				String venue 		= set.getString(5);
				String brgy 		= set.getString(6);
				String city 		= set.getString(7);
				String finaVenue 	= "";
				
				if(type.equalsIgnoreCase("home service")){
					finaVenue = address + ", " + brgy + ", " + city;
				}else{
					finaVenue = venue + ", " + brgy + ", " + city;
				}
				details.add(type);
				details.add(date);
				details.add(customer);
				details.add(finaVenue);
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

	public static List<EmployeeJob> getDeliveryJob(int id){
		Connection con = jdbc.getConnection();
		
		String query = "CALL getDeliveryJob(?);";
		List<EmployeeJob> jobList = new ArrayList<EmployeeJob>();
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				int jobID = set.getInt(1);
				int transID = set.getInt(2);
				String transType = "DELIVERY";
				String jobType = "DELIVERY";
				String jobDate = "";
				try{
					 jobDate = DateHelper.stringDate(set.getDate(3));
				}catch(NullPointerException e){
					jobDate = "";
				}
				String customer = set.getString(4);
				String service = "N/A";
				String status = set.getString(5);
				String address = set.getString(6);
				String brgy = set.getString(7);
				String city = set.getString(8);
				
				EmployeeJob job = new EmployeeJob(jobID, transType, jobType, jobDate, customer, service, status);
				job.setIntTransID(transID);
				job.setStrAddress(address + ", " + brgy + ", " + city);
				jobList.add(job);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return jobList;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static boolean updateJobStatus(int jobID, String strTransType, String strJobType, String strStatus){
		
		Connection con = jdbc.getConnection();
		
		String query = "";
		
		if(strTransType.equals("WALKIN") && strJobType.equals("SERVICE"))
			query = "UPDATE tblServiceWalkIn SET strStatus = ? WHERE intServiceWalkInID = ?;";
		else if(strTransType.equals("WALKIN") && strJobType.equals("PACKAGE"))
			query = "UPDATE tblAssignmentDetail SET strAssignmentStatus = ? WHERE intAssignmentDetailID = ?;";
		else if(strTransType.equals("WALKIN") && strJobType.equals("PROMO"))
			query = "UPDATE tblwalkinpromoservice SET strStatus = ? WHERE intWalkInPromoServiceID = ?;";
		else if(strTransType.equals("WALKIN") && strJobType.equals("PROMO PACKAGE"))
			query = "UPDATE tblAssignmentDetail SET strAssignmentStatus = ? WHERE intAssignmentDetailID = ?;";
		else if(strTransType.equals("HOME SERVICE") || strTransType.equals("EVENT")){
			query = "UPDATE tblreservationassignedemployee SET intStatus = ? WHERE ntRAEID = ?;";
		}
		else if(strTransType.equals("DELIVERY"))
			query = "UPDATE tblDelivery SET strStatus = ? WHERE intDeliveryID = ?;";
		try{
			
			PreparedStatement statement = con.prepareStatement(query);
			
			if(strTransType.equals("HOME SERVICE") || strTransType.equals("EVENT")){
				statement.setInt(1, Reservation.convertStringStatus(strStatus));
				statement.setInt(2, jobID);
			}else{
				statement.setString(1, strStatus);
				statement.setInt(2, jobID);
			}
				
			statement.execute();
			statement.close();
			con.close();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		
	}
}
