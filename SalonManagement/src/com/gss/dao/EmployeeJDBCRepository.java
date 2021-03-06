package com.gss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.gss.connection.JDBCConnection;
import com.gss.model.Employee;
import com.gss.model.EmployeeCategory;
import com.gss.model.Job;
import com.gss.model.Specialization;
import com.gss.utilities.JobQualificationHelper;
import com.opensymphony.xwork2.ActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
public class EmployeeJDBCRepository implements EmployeeRepository{

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
	
	@Override
	public List<Employee> getAllEmployee() {
		
		String strQuery1 = "CALL getAllEmployee()";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
		
			
			ResultSet set = st.executeQuery(strQuery1);

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
				imageBlob = set.getBlob(13);
				strJobStatus = set.getString(15);
				
				int blobLength = (int) imageBlob.length();  
				byte[] blobAsBytes = imageBlob.getBytes(1, blobLength);
				
				
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
			
			System.out.print(e.getMessage());
			System.out.print("Null Pointer");
			return null;
		}
	}

	@Override
	public Employee getEmployeeByUserPass(String username, String password) {
		
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
				}
				else
				{
					strEmpUsername = set.getString(11);
					strEmpPassword = set.getString(12);
				}
				
				blobEmpPhoto = "Empty";
				imageBlob = set.getBlob(13);
				access = set.getBoolean(14);
				strJobStatus = set.getString(15);
				System.out.println(access);
				
				int blobLength = (int) imageBlob.length();  
				byte[] blobAsBytes = imageBlob.getBytes(1, blobLength);
				
				
				PreparedStatement getJobs = con.prepareStatement(strQuery2);
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				
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
				
				emp = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				spec.close();
				specSet.close();
			}
			
			st.close();
			con.close();
			
			return emp;
		}
		catch(Exception e){
			
			System.out.print(e.fillInStackTrace());
			System.out.print("Null Pointer");
			return null;
		}
		
	}

	@Override
	public Employee getEmployeeByID(int intID) {
		
		Connection con 					= jdbc.getConnection();
		Employee employee 				= null;
		String getEmployee 				= "CALL getEmployeeByID(?);";
		String getEmpJobs 				= "CALL fetchJob(?)";
		
		
		
		try{
			PreparedStatement getEmp 	= con.prepareStatement(getEmployee);
			PreparedStatement getJobs 	= con.prepareStatement(getEmpJobs);
			ResultSet employeeResult;
			
			getEmp.setInt(1, intID);
			employeeResult = getEmp.executeQuery();
			
			while(employeeResult.next()){
				
				List<Job> jobs = new ArrayList<Job>(); 
				intEmpID = employeeResult.getInt(1);
				strEmpLastName = employeeResult.getString(2);
				strEmpFirstName = employeeResult.getString(3);
				strEmpMiddleName = employeeResult.getString(4);
				datEmpBirthdate = employeeResult.getDate(5);
				strEmpGender = employeeResult.getString(6);
				strEmpAddress = employeeResult.getString(7);
				strEmpContactNo = employeeResult.getString(8);
				strEmpEmail = employeeResult.getString(9);
				strEmpStatus = employeeResult.getString(10);
				if(employeeResult.getString(11) == null){
					strEmpUsername = "NO ACCESS";
					strEmpPassword = "NO ACCESS";
					access = false;
				}
				else
				{
					strEmpUsername = employeeResult.getString(11);
					strEmpPassword = employeeResult.getString(12);
					access = employeeResult.getBoolean(14);
				}
				
				blobEmpPhoto = "Empty";
				imageBlob = employeeResult.getBlob(13);
				strJobStatus = employeeResult.getString(15);
				
				int blobLength = (int) imageBlob.length();  
				byte[] blobAsBytes = null;
				
				getJobs.setInt(1, intEmpID);
				ResultSet jobSet = getJobs.executeQuery();
				
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
				
				employee = new Employee(intEmpID, strEmpLastName, strEmpFirstName, strEmpMiddleName, datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, strEmpStatus, strEmpUsername, strEmpPassword, blobEmpPhoto, blobAsBytes, jobs, access, strJobStatus, specialization);
				
			}
			
			con.close();
			
			
			return employee;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean createEmployee(Employee emp) {
		
		String strQuery1 = "CALL `createEmp`(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String strQuery2 = "CALL `createJobQualification`(?, ?);";
		String strQuery3 = "INSERT INTO tblSpecialization(intServiceCategoryID, intEmployeeID) VALUES(?, ?);";
		List<Job> job = emp.getJobQualification();
		
				
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		
		int intEmpID = 0;
		File imageFile = null;
		FileInputStream fileInput = null;
		try{
			imageFile = new File(emp.getBlobEmpPhoto());
			fileInput = new FileInputStream(imageFile);
		}catch(Exception e){
			imageFile = new File(emp.getBlobEmpPhoto());
			try {
				fileInput = new FileInputStream(((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
				        .getRealPath("WEB-INF/Reports/Employee_Default.jpg"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		try{
			
			java.sql.Date sqlDate = new java.sql.Date(emp.getDatEmpBirthdate().getTime());
			PreparedStatement createJob = con.prepareStatement(strQuery2);
			PreparedStatement pre = con.prepareStatement(strQuery1);
			PreparedStatement spec = con.prepareStatement(strQuery3);
			pre.setString(1, emp.getStrEmpLastName());
			pre.setString(2, emp.getStrEmpFirstName());
			pre.setString(3, emp.getStrEmpMiddleName());
			pre.setDate(4, sqlDate);
			pre.setString(5, emp.getStrEmpGender());
			pre.setString(6, emp.getStrEmpAddress());
			pre.setString(7, emp.getStrEmpContactNo());
			pre.setString(8, emp.getStrEmpEmail());
			pre.setString(9, emp.getStrEmpStatus());
			pre.setString(10, emp.getStrEmpUsername());
			pre.setString(11, emp.getStrEmpPassword());
			pre.setBinaryStream(12, (InputStream)fileInput, (int)imageFile.length());
			pre.setBoolean(13, emp.isAccessGranted());

			
			ResultSet setEmpID = pre.executeQuery();
			while(setEmpID.next()){
				intEmpID = setEmpID.getInt(1);
			}
			setEmpID.close();
			pre.close();
			
			for(int i = 0; i < job.size(); i++){
				createJob.setInt(1, intEmpID);
				createJob.setString(2, job.get(i).getStrJobDesc());
				createJob.execute();
			}
			
			for(int index = 0; index < emp.getSpecialization().size(); index++){
				Specialization specialization =  emp.getSpecialization().get(index);
				spec.setInt(1, specialization.getId());
				spec.setInt(2, intEmpID);
				spec.addBatch();
			}
			
			spec.executeBatch();
			spec.close();
			
			
			createJob.close();
			con.close();
			
			return true;
		}
		catch(SQLException e){
			//Error
			e.printStackTrace();
			
			return false;
		}
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		
		String strQuery1 = "CALL updateEmployee( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String updateJob = "CALL updateJob(?, ?)";
		String delete = "DELETE From tblSpecialization WHERE intEmployeeID = ?;";
		String insert = "INSERT INTO tblSpecialization(intServiceCategoryID, intEmployeeID) VALUES(?, ?);";
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		int intEmpID = emp.getIntEmpID();
		List<Job> oldJobList = new ArrayList<Job>();
		
		try{
			java.sql.Date sqlDate = new java.sql.Date(emp.getDatEmpBirthdate().getTime());
			
			PreparedStatement pre = con.prepareStatement(strQuery1);
			PreparedStatement spec = con.prepareStatement(insert);
			PreparedStatement del = con.prepareStatement(delete);

			if(emp.getBlobEmpPhoto().equalsIgnoreCase("image")){
				pre.setInt(1, emp.getIntEmpID());
				pre.setString(2, emp.getStrEmpLastName());
				pre.setString(3, emp.getStrEmpFirstName());
				pre.setString(4, emp.getStrEmpMiddleName());
				pre.setDate(5, sqlDate);
				pre.setString(6, emp.getStrEmpGender());
				pre.setString(7, emp.getStrEmpAddress());
				pre.setString(8, emp.getStrEmpContactNo());
				pre.setString(9, emp.getStrEmpEmail());
				pre.setString(10, emp.getStrEmpStatus());
				pre.setString(11, emp.getStrEmpUsername());
				pre.setString(12, emp.getStrEmpPassword());
				pre.setInt(13, 101);
				pre.setBoolean(14, emp.isAccessGranted());
			}
			else{
				
				File imageFile = new File(emp.getBlobEmpPhoto());
				FileInputStream fileInput = new FileInputStream(imageFile);
				
				pre.setInt(1, emp.getIntEmpID());
				pre.setString(2, emp.getStrEmpLastName());
				pre.setString(3, emp.getStrEmpFirstName());
				pre.setString(4, emp.getStrEmpMiddleName());
				pre.setDate(5, sqlDate);
				pre.setString(6, emp.getStrEmpGender());
				pre.setString(7, emp.getStrEmpAddress());
				pre.setString(8, emp.getStrEmpContactNo());
				pre.setString(9, emp.getStrEmpEmail());
				pre.setString(10, emp.getStrEmpStatus());
				pre.setString(11, emp.getStrEmpUsername());
				pre.setString(12, emp.getStrEmpPassword());
				pre.setBinaryStream(13, (InputStream)fileInput, (int)imageFile.length());
				pre.setBoolean(14, emp.isAccessGranted());
			}
			
			pre.execute();
			
			del.setInt(1, emp.getIntEmpID());
			del.execute();
			del.close();
			
			List<Job> jobList = emp.getJobQualification();
			
			for(int i = 0; i < jobList.size(); i++){
				PreparedStatement update = con.prepareStatement(updateJob);
				update.setString(1, jobList.get(i).getStrJobDesc());
				update.setInt(2,  emp.getIntEmpID());
				update.execute();
				update.close();
			}
			
			for(int index = 0; index < emp.getSpecialization().size(); index++){
				Specialization specialization =  emp.getSpecialization().get(index);
				spec.setInt(1, specialization.getId());
				spec.setInt(2, intEmpID);
				spec.addBatch();
			}
			
			spec.executeBatch();
			spec.close();
			
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<EmployeeCategory> getAllCategory() {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblJob WHERE intStatus = 1;";
		List<EmployeeCategory> empCategory = new ArrayList<EmployeeCategory>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				String strID = String.valueOf(set.getInt(1));
				String strName = set.getString(2);
				
				EmployeeCategory cate = new EmployeeCategory(strID, strName);
				
				empCategory.add(cate);
			}
			
			pre.close();
			set.close();
			con.close();
			
			return empCategory;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			System.out.println("May mali sa create");
			return null;
		}
	}

	@Override
	public List<Job> getEmployeeJob(int empID) {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String query = "CALL getEmpJob(?);";
		List<Job> empJob = new ArrayList<Job>();
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, empID);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				String strDesc = set.getString(1);
				int intStat = set.getInt(2);
				Job job = new Job(strDesc, intStat);
				empJob.add(job);
			}
			
			pre.close();
			set.close();
			con.close();
			
			return empJob;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			System.out.println("May mali sa update");
			return null;
		}
	}

	@Override
	public boolean deactivateEmployee(int empID) {
		
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		String query = "CALL deactivateEmployee(?)";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, empID);
			
			pre.executeQuery();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.print(e.fillInStackTrace());
			
			return false;
		}
	}

	@Override
	public List<Employee> getAllEmployeeNoImage() {
		String strQuery1 = "CALL getAllEmployee()";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			
			ResultSet set = st.executeQuery(strQuery1);

			while(set.next()){
				
				List<Job> jobs = new ArrayList<Job>(); 
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
				imageBlob = set.getBlob(13);
				strJobStatus = set.getString(15);
				
				int blobLength = (int) imageBlob.length();  
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
			}
			
			st.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			System.out.print(e.getMessage());
			System.out.print("Null Pointer");
			System.out.println("May mali sa getemployee");
			return null;
		}
	}

	@Override
	public List<Employee> queryAllEmployee() {
		String strQuery1 = "CALL queryAllEmployee()";
		String strQuery2 = "CALL fetchJob(?)";
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getConnection();
		List<Employee> empList = new ArrayList<Employee>();
		
		try{
			PreparedStatement st = con.prepareStatement(strQuery1);
			
			ResultSet set = st.executeQuery(strQuery1);

			while(set.next()){
				
				List<Job> jobs = new ArrayList<Job>(); 
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
				imageBlob = set.getBlob(13);
				strJobStatus = set.getString(15);
				
				int blobLength = (int) imageBlob.length();  
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
			}
			
			st.close();
			con.close();
			
			return empList;
		}
		catch(Exception e){
			
			System.out.print(e.getMessage());
			System.out.print("Null Pointer");
			System.out.println("May mali sa getemployee");
			return null;
		}
	}

	public boolean postion(String positionName, int type){
		
		Connection con = jdbc.getConnection();
		
		String query = "";
		
		if(type == 1)
			query = "CALL addPosition(?);";
		else
			query = "CALL removePosition(?);";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, positionName.trim().toUpperCase());
			
			statement.execute();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;	
		}
	}
	
	public static String searchJob(String jobName){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT intJobID FROM tblJob WHERE strJobDesc = ? AND intStatus = 1;";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, jobName.trim().toUpperCase());
			ResultSet set = statement.executeQuery();
			
			statement.execute();
			
			int id = 0;
			
			while(set.next()){
				id = set.getInt(1);
			}
			
			if(id == 0){
				return "OK";
			}else{
				return "EXISTING";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Job> getJob(){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT * FROM tblJob WHERE intStatus = 1;";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
			
			List<Job> jobList = new ArrayList<Job>();
			
			while(set.next()){
				int jobID = set.getInt(1);
				String jobName = set.getString(2);
				jobList.add(new Job(jobName, jobID));
			}
			
			return jobList;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static boolean changeUserName(String userName, int empID){
		
		Connection con = jdbc.getConnection();
		
		String query = "UPDATE tblAccount SET strEmpUsername = ? WHERE intEmpID = ?;";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, userName);
			statement.setInt(2, empID);
			
			statement.execute();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean changePassword(String passWord, int empID){
		Connection con = jdbc.getConnection();
		
		String query = "UPDATE tblAccount SET strEmpPassword = ? WHERE intEmpID = ?;";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, passWord);
			statement.setInt(2, empID);
			
			statement.execute();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getCurrentPassword(int empID){
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strEmpPassword FROM tblAccount WHERE intEmpID = ?;";
		String oldPassword = "";
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, empID);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				oldPassword = set.getString(1);
			}
			
			return oldPassword;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public static List<String> getAllUsername(){
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strEmpUsername FROM tblAccount;";
		List<String> usernames = new ArrayList<String>();
		try{
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet set = statement.executeQuery();
				
			while(set.next()){
				 usernames.add(set.getString(1));
			}
			
			return usernames;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
