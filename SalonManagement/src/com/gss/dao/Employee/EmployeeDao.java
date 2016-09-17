package com.gss.dao.Employee;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.model.Specialization;


public class EmployeeDao {
	private static JDBCConnection jdbc = new JDBCConnection();
	private int intEmpID;
	private String strJobDesc;
	private String strEmpLastName;
	private String strEmpFirstName;
	private String strEmpMiddleName;
	private Date datEmpBirthdate;
	private String strEmpGender;
	private String strEmpAddress;
	private String strEmpContactNo;
	private String strEmpEmail;
	private String strEmpStatus;
	private String strEmpUsername;
	private String strEmpPassword;
	private String blobEmpPhoto;
	private byte[] bytActualImage;
	private Blob imageBlob;
	private boolean access;
	private String strJobStatus;
	
	
	public List<Employee> walkIn() {
		
		String strQuery1 = "CALL verifyEmployeeSchedule_walkin(?);";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setInt(1, 1);
			ResultSet set = st.executeQuery();
			

			while(set.next()){
				
				
				intEmpID = set.getInt(1);
				strEmpLastName = set.getString(2);
				strEmpFirstName = set.getString(3);
				strEmpMiddleName = set.getString(4);
				datEmpBirthdate = set.getDate(5);
				strEmpGender = set.getString(6);
				strEmpAddress = set.getString(7);
				strEmpContactNo = set.getString(8);
				strEmpEmail = set.getString(9);
				strEmpStatus = set.getString(10);
				if(set.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
					access = set.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				List<Job> jobs = new ArrayList<Job>(); 
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				PreparedStatement spec = con.prepareStatement("CALL getSpecialization(?);");
				spec.setInt(1, intEmpID);
				ResultSet specSet = spec.executeQuery();
				
				List<Specialization> specialization = new ArrayList<Specialization>();
				
				while(specSet.next()){
					specialization.add(new Specialization(specSet.getInt(1), specSet.getString(2)));
				}
				spec.close();
				specSet.close();
				
				Employee emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
				empList.add(emp);
			
				
				getJobs.close();
				jobSet.close();
			}
			
			st.close();
			set.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> appointment(String date, String time) {
		
		String strQuery1 = "CALL verifyEmployeeSchedule_appointment(?, ?);";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setString(1, date);
			st.setString(2, time);
			
			ResultSet set = st.executeQuery();

			while(set.next()){
				
				
				intEmpID = set.getInt(1);
				strEmpLastName = set.getString(2);
				strEmpFirstName = set.getString(3);
				strEmpMiddleName = set.getString(4);
				datEmpBirthdate = set.getDate(5);
				strEmpGender = set.getString(6);
				strEmpAddress = set.getString(7);
				strEmpContactNo = set.getString(8);
				strEmpEmail = set.getString(9);
				strEmpStatus = set.getString(10);
				if(set.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
					access = set.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				List<Job> jobs = new ArrayList<Job>(); 
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				PreparedStatement spec = con.prepareStatement("CALL getSpecialization(?);");
				spec.setInt(1, intEmpID);
				ResultSet specSet = spec.executeQuery();
				
				List<Specialization> specialization = new ArrayList<Specialization>();
				
				while(specSet.next()){
					specialization.add(new Specialization(specSet.getInt(1), specSet.getString(2)));
				}
				spec.close();
				specSet.close();
				
				Employee emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
				empList.add(emp);
			
				
				getJobs.close();
				jobSet.close();
			}
			
			st.close();
			set.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> delivery(String date, String time, int location) {
		
		String strQuery1 = "CALL verifyEmployeeSchedule_delivery(?, ?, ?);";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setString(1, date);
			st.setString(2, time);
			st.setInt(3, location);
			
			ResultSet set = st.executeQuery();

			while(set.next()){
				
				
				intEmpID = set.getInt(1);
				strEmpLastName = set.getString(2);
				strEmpFirstName = set.getString(3);
				strEmpMiddleName = set.getString(4);
				datEmpBirthdate = set.getDate(5);
				strEmpGender = set.getString(6);
				strEmpAddress = set.getString(7);
				strEmpContactNo = set.getString(8);
				strEmpEmail = set.getString(9);
				strEmpStatus = set.getString(10);
				if(set.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
					access = set.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				List<Job> jobs = new ArrayList<Job>(); 
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				PreparedStatement spec = con.prepareStatement("CALL getSpecialization(?);");
				spec.setInt(1, intEmpID);
				ResultSet specSet = spec.executeQuery();
				
				List<Specialization> specialization = new ArrayList<Specialization>();
				
				while(specSet.next()){
					specialization.add(new Specialization(specSet.getInt(1), specSet.getString(2)));
				}
				spec.close();
				specSet.close();
				
				Employee emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
				empList.add(emp);
			
				
				getJobs.close();
				jobSet.close();
			}
			
			st.close();
			set.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> homeService(String date, String time) {
		
		String strQuery1 = "CALL verifyEmployeeSchedule_homeService(?, ?);";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setString(1, date);
			st.setString(2, time);
			
			ResultSet set = st.executeQuery();

			while(set.next()){
				
				
				intEmpID = set.getInt(1);
				strEmpLastName = set.getString(2);
				strEmpFirstName = set.getString(3);
				strEmpMiddleName = set.getString(4);
				datEmpBirthdate = set.getDate(5);
				strEmpGender = set.getString(6);
				strEmpAddress = set.getString(7);
				strEmpContactNo = set.getString(8);
				strEmpEmail = set.getString(9);
				strEmpStatus = set.getString(10);
				if(set.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
					access = set.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				List<Job> jobs = new ArrayList<Job>(); 
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				PreparedStatement spec = con.prepareStatement("CALL getSpecialization(?);");
				spec.setInt(1, intEmpID);
				ResultSet specSet = spec.executeQuery();
				
				List<Specialization> specialization = new ArrayList<Specialization>();
				
				while(specSet.next()){
					specialization.add(new Specialization(specSet.getInt(1), specSet.getString(2)));
				}
				spec.close();
				specSet.close();
				
				Employee emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
				empList.add(emp);
			
				
				getJobs.close();
				jobSet.close();
			}
			
			st.close();
			set.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> event(String date, String time, String timeTo) {
		
		String strQuery1 = "CALL verifyEmployeeSchedule_event(?, ?, ?);";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setString(1, date);
			st.setString(2, time);
			st.setString(3, timeTo);
			
			ResultSet set = st.executeQuery();

			while(set.next()){
				
				
				intEmpID = set.getInt(1);
				strEmpLastName = set.getString(2);
				strEmpFirstName = set.getString(3);
				strEmpMiddleName = set.getString(4);
				datEmpBirthdate = set.getDate(5);
				strEmpGender = set.getString(6);
				strEmpAddress = set.getString(7);
				strEmpContactNo = set.getString(8);
				strEmpEmail = set.getString(9);
				strEmpStatus = set.getString(10);
				if(set.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
					access = set.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				List<Job> jobs = new ArrayList<Job>(); 
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				PreparedStatement spec = con.prepareStatement("CALL getSpecialization(?);");
				spec.setInt(1, intEmpID);
				ResultSet specSet = spec.executeQuery();
				
				List<Specialization> specialization = new ArrayList<Specialization>();
				
				while(specSet.next()){
					specialization.add(new Specialization(specSet.getInt(1), specSet.getString(2)));
				}
				spec.close();
				specSet.close();
				
				Employee emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
				empList.add(emp);
			
				
				getJobs.close();
				jobSet.close();
			}
			
			st.close();
			set.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
	}

}
