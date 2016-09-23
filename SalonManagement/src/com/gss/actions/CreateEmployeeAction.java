package com.gss.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.gss.model.Employee;
import com.gss.model.Job;
import com.gss.model.Specialization;
import com.gss.service.EmployeeServiceImpl;
import com.gss.testers.EmployeeCredentialsSender;
import com.gss.utilities.DateHelper;
import com.gss.utilities.DefaultImage;
import com.gss.utilities.JobQualificationHelper;
import com.gss.utilities.NotifyCustomerViaSMS;
import com.gss.utilities.RandomStringGenerator;
import com.gss.utilities.SendMail;
import com.opensymphony.xwork2.ActionContext;
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
	
	private List<String> selectedSpecialization = new ArrayList<String>();
	
	public String execute(){
		
		boolean access = false;
		EmployeeServiceImpl empService;
		Employee emp;
		this.strEmpContactNo = this.strEmpContactNo.replaceAll("-", "");
		System.out.println(this.strEmpContactNo);
		List<Job> selectedJob = new JobQualificationHelper().convertToJob(this.selectedJob);
		NotifyCustomerViaSMS sms = new NotifyCustomerViaSMS();
		
		String path = "";
		
		String[] unConvertedDate = strBirthdate.split("/");
		
		if(chkGrantAccess.equalsIgnoreCase("on"))
			access = true;
		
		String strUser = RandomStringGenerator.generateRandomString(8);
		
		String strPass = RandomStringGenerator.generateRandomString(8);
		
		this.strBirthdate = new DateHelper().convert(unConvertedDate);
		this.datEmpBirthdate = DateHelper.parseDate(strBirthdate);
		this.username = strUser;
		this.password = strPass;
		this.datEmpBirthdate = DateHelper.parseDate(strBirthdate);
		this.strEmpContactNo.replaceAll("-", "");
		String path1 = "";
		List<Specialization> specialization = Specialization.convertToObject(this.selectedSpecialization);
		
		try{
			emp = new Employee(1, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), datEmpBirthdate, strEmpGender, strEmpAddress.trim().toUpperCase(), strEmpContactNo, strEmpEmail, "A", strUser, strPass, file.getAbsolutePath(), null, selectedJob, access, "Not Available", specialization);
			empService = new EmployeeServiceImpl();
			path1 = "/Employee_Default.jpg";

		}catch(NullPointerException e){
			emp = new Employee(1, strEmpLastName.trim().toUpperCase(), strEmpFirstName.trim().toUpperCase(), strEmpMiddleName.trim().toUpperCase(), datEmpBirthdate, strEmpGender, strEmpAddress.trim().toUpperCase(), strEmpContactNo, strEmpEmail, "A", strUser, strPass, path1, null, selectedJob, access, "Not Available", specialization);
			empService = new EmployeeServiceImpl();
		}
		
		if(empService.create(emp) == true)
		{	
			EmployeeCredentialsSender.sendEmail(emp);
			sms.sendSMS(getMessage(), this.strEmpContactNo);
		}
		else
		{	
			return "failed";
		}	return "success";

	}


	public void setStrEmpLastName(String strEmpLastName) {
		this.strEmpLastName = strEmpLastName.trim().toUpperCase();
	}


	public void setStrEmpFirstName(String strEmpFirstName) {
		this.strEmpFirstName = strEmpFirstName.trim().toUpperCase();
	}

	public void setStrEmpMiddleName(String strEmpMiddleName) {
		this.strEmpMiddleName = strEmpMiddleName.trim().toUpperCase();
	}


	public void setStrEmpGender(String strEmpGender) {
		this.strEmpGender = strEmpGender;
	}


	public void setStrEmpAddress(String strEmpAddress) {
		this.strEmpAddress = strEmpAddress.trim().toUpperCase();
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
		
		 String message = ("Good day Mr./Ms. " + this.strEmpLastName.toUpperCase() + ", your username is "+this.username+" and your password is " + this.password + ". You can change it using Salon App or Salon Management System if you are granted the system access.");
		
		return message;
	}


	public void setSelectedSpecialization(List<String> selectedSpecialization) {
		this.selectedSpecialization = selectedSpecialization;
	}



}
