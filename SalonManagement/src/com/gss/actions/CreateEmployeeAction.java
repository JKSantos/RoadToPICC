package com.gss.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.model.Specialization;
import com.gss.service.EmployeeServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.DefaultImage;
import com.gss.utilities.JobQualificationHelper;
import com.gss.utilities.NotifyCustomerViaSMS;
import com.gss.utilities.SendMail;
import com.opensymphony.xwork2.ActionSupport;

public class CreateEmployeeAction {

	private static final long serialVersionUID = 1L;
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
	private String strEmpBirthdate;
	private File file;
	private String contentType;
	private String filename;
	private List<String> selectedJob;
	private String chkGrantAccess = "off";
	
	private String username;
	private String password;
	
	private List<String> specialization;
	
	public String execute(){
		
		boolean access = false;
		SendMail mail = new SendMail();
		EmployeeServiceImpl empService;
		Employee emp;

		List<Job> selectedJob = new JobQualificationHelper().convertToJob(this.selectedJob);
		NotifyCustomerViaSMS sms = new NotifyCustomerViaSMS();
		
		String path = "";
		
		String concatenatedName = "";
		String[] unConvertedDate = strBirthdate.split("/");
		String[] name = this.strEmpFirstName.split(" ");
		
		for(int i = 0; i < name.length; i++){
			concatenatedName += name[i];
		}
		
		if(chkGrantAccess.equalsIgnoreCase("on"))
			access = true;
		
		String strUser = concatenatedName;
		concatenatedName = "";
		name = this.strEmpLastName.split(" ");
		
		for(int i = 0; i < name.length; i++){
			concatenatedName += name[i];
		}
		
		String strPass = concatenatedName;
		
		this.strBirthdate = new DateHelper().convert(unConvertedDate);
		this.datEmpBirthdate = DateHelper.parseDate(strBirthdate);
		this.username = strUser;
		this.password = strPass;
		this.datEmpBirthdate = DateHelper.parseDate(strBirthdate);
		
		List<Specialization> specialization = Specialization.convertToObject(this.specialization);
		
		try{
			emp = new Employee(1, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), datEmpBirthdate, strEmpGender, strEmpAddress.trim().toUpperCase(), strEmpContactNo, strEmpEmail, "A", strUser, strPass, file.getAbsolutePath(), null, selectedJob, access, "Not Available", specialization);
			empService = new EmployeeServiceImpl();
		}catch(NullPointerException e){
			emp = new Employee(1, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), datEmpBirthdate, strEmpGender, strEmpAddress.trim().toUpperCase(), strEmpContactNo, strEmpEmail, "A", strUser, strPass, "images/fb.jpg", null, selectedJob, access, "Not Available", specialization);
			empService = new EmployeeServiceImpl();
		}
		
		if(empService.create(emp) == true)
		{	
			mail.sendEmail(this.strEmpEmail, strUser, strPass);
			sms.sendSMS(getMessage(), this.strEmpContactNo);
			return "success";
		}
		else
		{	
			return "failed";
		}
	}


	public void setStrEmpLastName(String strEmpLastName) {
		this.strEmpLastName = strEmpLastName;
	}


	public void setStrEmpFirstName(String strEmpFirstName) {
		this.strEmpFirstName = strEmpFirstName;
	}

	public void setStrEmpMiddleName(String strEmpMiddleName) {
		this.strEmpMiddleName = strEmpMiddleName;
	}


	public void setStrEmpGender(String strEmpGender) {
		this.strEmpGender = strEmpGender;
	}


	public void setStrEmpAddress(String strEmpAddress) {
		this.strEmpAddress = strEmpAddress;
	}


	public void setStrEmpContactNo(String strEmpContactNo) {
		this.strEmpContactNo = strEmpContactNo;
	}
	
	public void setSelectedJob(List<String> selectedJob) {
		this.selectedJob = selectedJob;
	}


	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	
	public void setUpload(File file){
		this.file = file;
	}
	

	public void setUploadFilename(String filename) {
		this.filename = filename;
	}



	public void setStrBirthdate(String strBirthdate) {
		this.strBirthdate = strBirthdate;
	}


	public void setStrEmpEmail(String strEmpEmail) {
		this.strEmpEmail = strEmpEmail;
	}


	public void setChkGrantAccess(String chkGrantAccess) {
		this.chkGrantAccess = chkGrantAccess;
	}
	
	public String getMessage(){
		
		 String message = ("Congratulations! You are now an employee of our Salon! Please download the job monitoring app at https://goog.gl/SH345GYS. You can also login on our admin page if you are granted the privilege to do so. \n\nYoure Username is " + username + " and your pasword is " + password + ".");
		
		return message;
	}

	public void setSpecialization(List<String> specialization) {
		this.specialization = specialization;
	}

}
