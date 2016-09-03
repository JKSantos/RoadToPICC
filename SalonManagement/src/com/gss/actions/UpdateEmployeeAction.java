package com.gss.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.service.EmployeeServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.JobQualificationHelper;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateEmployeeAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private int intEmpID;
	private String strEmpLastName;
	private String strEmpFirstName;
	private String strEmpMiddleName;
	private String strBirthdate;
	private Date datEmpBirthdate;
	private String strEmpGender;
	private String strEmpAddress;
	private String strEmpContactNo;
	private String strEmpEmail;
	private String strEmpStatus;
	private String strEmpUsername;
	private String strEmpPassword;
	private String strFileName;
	private File file;
	private String contentType;
	private String filename;
	private List<String> selectedJob;
	private String imageName;
	private String chkGrantAccess = "off";
	
	public String execute(){
		
		EmployeeServiceImpl empService = new EmployeeServiceImpl();
		Employee emp = null;

		String[] unConvertedDate = this.strBirthdate.split("/");
		
		boolean access = false;
		
		if(chkGrantAccess.equalsIgnoreCase("on"))
			access = true;
		
		List<Job> jobList = new JobQualificationHelper().convertToJob(this.selectedJob);
		
		this.strBirthdate = new DateHelper().convert(unConvertedDate);

		this.datEmpBirthdate = DateHelper.parseDate(strBirthdate);
		
		if(imageName.equals("image")){
			emp = new Employee(intEmpID, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), this.datEmpBirthdate, strEmpGender, strEmpAddress.trim().toUpperCase(), strEmpContactNo, strEmpEmail, "A", "NO ACCESS", "NO ACCESS", "Image", null, jobList, access, "");
		}
		else{
			imageName = file.getAbsolutePath();
			emp = new Employee(intEmpID, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), datEmpBirthdate, strEmpGender, strEmpAddress, strEmpContactNo, strEmpEmail, "A", "NO ACCESS", "NO ACCESS", imageName, null, jobList, access, "");
		}
		
		if(empService.updateEmployee(emp) == true)
		{	
			System.out.println("EMPLOYEE WAS UPDATED SUCCESSUFULLY");
			return "success";
		}
		else
		{	
			System.out.println("PROBLEM ENCOUNTERED WHILE CREATING EMPLOYEE");
			return "failed";
		}
	}
	

	public int getIntEmpID() {
		return intEmpID;
	}


	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
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

	public String getStrEmpAddress() {
		return strEmpAddress;
	}

	public void setStrEmpAddress(String strEmpAddress) {
		this.strEmpAddress = strEmpAddress;
	}

	public String getStrEmpContactNo() {
		return strEmpContactNo;
	}

	public void setStrEmpContactNo(String strEmpContactNo) {
		this.strEmpContactNo = strEmpContactNo;
	}

	public String getStrEmpStatus() {
		return strEmpStatus;
	}

	public void setStrEmpStatus(String strEmpStatus) {
		this.strEmpStatus = strEmpStatus;
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

	public List<String> getSelectedJob() {
		return selectedJob;
	}

	public void setSelectedJob(List<String> selectedJob) {
		this.selectedJob = selectedJob;
	}

	public File getUpload() {
		return file;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public String getUploadContentType() {
		return contentType;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUploadFilename() {
		return filename;
	}

	public void setUploadFilename(String filename) {
		this.filename = filename;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}


	public String getStrBirthdate() {
		return strBirthdate;
	}


	public void setStrBirthdate(String strBirthdate) {
		this.strBirthdate = strBirthdate;
	}


	public String getStrEmpEmail() {
		return strEmpEmail;
	}


	public void setStrEmpEmail(String strEmpEmail) {
		this.strEmpEmail = strEmpEmail;
	}


	public void setChkGrantAccess(String chkGrantAccess) {
		this.chkGrantAccess = chkGrantAccess;
	}

	
}
