package com.gss.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;

public class Employee {
	
	private int intEmpID;
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
	private List<Job> jobQualification = new ArrayList<Job>();
	private boolean accessGranted;
	private String strJobStatus;
	private List<Specialization> specialization;

	public Employee(int intEmpID, String strEmpLastName, String strEmpFirstName, String strEmpMiddleName,
			Date datEmpBirthdate, String strEmpGender, String strEmpAddress, String strEmpContactNo, String strEmpEmail,
			String strEmpStatus, String strEmpUsername, String strEmpPassword, String blobEmpPhoto,
			byte[] bytActualImage, List<Job> jobQualification, boolean accessGranted, String strJobStatus, List<Specialization> specialization) {
		super();
		this.intEmpID = intEmpID;
		this.strEmpLastName = strEmpLastName;
		this.strEmpFirstName = strEmpFirstName;
		this.strEmpMiddleName = strEmpMiddleName;
		this.datEmpBirthdate = datEmpBirthdate;
		this.strEmpGender = strEmpGender;
		this.strEmpAddress = strEmpAddress;
		this.strEmpContactNo = strEmpContactNo;
		this.strEmpEmail = strEmpEmail;
		this.strEmpStatus = strEmpStatus;
		this.strEmpUsername = strEmpUsername;
		this.strEmpPassword = strEmpPassword;
		this.blobEmpPhoto = blobEmpPhoto;
		this.bytActualImage = bytActualImage;
		this.jobQualification = jobQualification;
		this.accessGranted = accessGranted;
		this.strJobStatus = strJobStatus;
		this.specialization = specialization;
	}

	public String getStrEmpLastName() {
		return strEmpLastName;
	}

	public void setStrEmpLastName(String strEmpLastName) {
		this.strEmpLastName = strEmpLastName;
	}

	public String getStrEmpFirstName() {
		return strEmpFirstName;
	}

	public void setStrEmpFirstName(String strEmpFirstName) {
		this.strEmpFirstName = strEmpFirstName;
	}

	public String getStrEmpMiddleName() {
		return strEmpMiddleName;
	}

	public void setStrEmpMiddleName(String strEmpMiddleName) {
		this.strEmpMiddleName = strEmpMiddleName;
	}

	public String getStrEmpAddress() {
		return strEmpAddress;
	}

	public void setStrEmpAddress(String strEmpAddress) {
		this.strEmpAddress = strEmpAddress;
	}

	public Date getDatEmpBirthdate() {
		return datEmpBirthdate;
	}

	public void setDatEmpBirthdate(Date datEmpBirthdate) {
		this.datEmpBirthdate = datEmpBirthdate;
	}

	public String getStrEmpGender() {
		return strEmpGender;
	}

	public void setStrEmpGender(String strEmpGender) {
		this.strEmpGender = strEmpGender;
	}

	public String getStrEmpUsername() {
		return strEmpUsername;
	}

	public void setStrEmpUsername(String strEmpUsername) {
		this.strEmpUsername = strEmpUsername;
	}

	public String getStrEmpPassword() {
		return strEmpPassword;
	}

	public void setStrEmpPassword(String strEmpPassword) {
		this.strEmpPassword = strEmpPassword;
	}

	public String getStrEmpStatus() {
		return strEmpStatus;
	}

	public void setStrEmpStatus(String strEmpStatus) {
		this.strEmpStatus = strEmpStatus;
	}

	public String getStrEmpContactNo() {
		return strEmpContactNo;
	}

	public void setStrEmpContactNo(String strEmpContactNo) {
		this.strEmpContactNo = strEmpContactNo;
	}

	public int getIntEmpID() {
		return intEmpID;
	}

	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
	}

	public String getBlobEmpPhoto() {
		return blobEmpPhoto;
	}

	public void setBlobEmpPhoto(String blobEmpPhoto) {
		this.blobEmpPhoto = blobEmpPhoto;
	}

	public byte[] getBytActualImage() {
		return bytActualImage;
	}

	public void setBytActualImage(byte[] bytActualImage) {
		this.bytActualImage = bytActualImage;
	}

	

	public List<Job> getJobQualification() {
		return jobQualification;
	}

	public void setJobQualification(List<Job> jobQualification) {
		this.jobQualification = jobQualification;
	}

	public String getStrEmpEmail() {
		return strEmpEmail;
	}

	public void setStrEmpEmail(String strEmpEmail) {
		this.strEmpEmail = strEmpEmail;
	}

	public boolean isAccessGranted() {
		return accessGranted;
	}

	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}

	public String getStrJobStatus() {
		return strJobStatus;
	}

	public void setStrJobStatus(String strJobStatus) {
		this.strJobStatus = strJobStatus;
	}

	public static List<Employee> getEmployeeList() {
		EmployeeService service = new EmployeeServiceImpl();
		return service.getAllEmployeeNoImage();
	}
	
	public static Employee createNullEmployee(int intEmpID){
		return new Employee(intEmpID, "", "", "", new Date(), "", "", "", "", "", "", "", "", null, null, false, "NOT AVAILABLE", null);
	}
	
	public static Employee searchEmployee(int intEmployeeID, List<Employee> employeeList){
		
		for(int i = 0; i < employeeList.size(); i++){
			if(intEmployeeID == employeeList.get(i).getIntEmpID())
				return employeeList.get(i);
		}
		
		return null;
	}
	
	public static List<Employee> queryAllEmployee(){
		EmployeeService service = new EmployeeServiceImpl();
		return service.queryAllEmployee();
	}
	
	public static boolean position(String positionName, int type){
		
		EmployeeService service = new EmployeeServiceImpl();
		
		return service.postion(positionName, type);
	}

	public List<Specialization> getSpecialization() {
		return specialization;
	}

	public void setSpecialization(List<Specialization> specialization) {
		this.specialization = specialization;
	}
}
