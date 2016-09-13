package com.gss.dao.Employee;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Employee;
import com.gss.model.Job;

public class EmployeeMobileLogIn {
	
	public static Employee logIn(String username, String password){
		
		String strQuery1 = "CALL logInEmployee(?, ?)";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		
		Employee emp = null;
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			st.setString(1, username);
			st.setString(2, password);
			
			
			ResultSet set = st.executeQuery();

			while(set.next()){
				
				List<Job> jobs = new ArrayList<Job>(); 
				int intEmpID = set.getInt(1);
				String strEmpLastName = set.getString(2);
				String strEmpFirstName = set.getString(3);
				String strEmpMiddleName = set.getString(4);
				Date datEmpBirthdate = set.getDate(5);
				String strEmpGender = set.getString(6);
				String strEmpAddress = set.getString(7);
				String strEmpContactNo = set.getString(8);
				String strEmpEmail = set.getString(9);
				String strEmpStatus = set.getString(10);
				String strEmpUsername = set.getString(11);
				String strEmpPassword = set.getString(12);
				String blobEmpPhoto = ":8080/SalonManagement/getImage?ImageID="+ intEmpID +"&type=employee";
				boolean access = set.getBoolean(14);
				String strJobStatus = set.getString(15);
				
				byte[] blobAsBytes = null;
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				
				while(jobSet.next()){
					String jobDesc = jobSet.getString(1);
					int jobStatus = jobSet.getInt(2);
					
					Job job = new Job(jobDesc, jobStatus);
					jobs.add(job);
				}
				
				emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus);

			}
			
			emp.getIntEmpID();
			
			st.close();
			con.close();
			
			return emp;
		}
		catch(SQLException e){
			
			System.out.print(e.fillInStackTrace());
			System.out.print("Null Pointer");
			return null;
		}
		catch(NullPointerException n){
			emp = Employee.createNullEmployee(0);
			return emp;
		}
	}

}
