package com.gss.model;

public class EmployeeCategory {
	
	private String strCategoryID;
	private String strCategoryName;
	
	public EmployeeCategory(String strCategoryID, String strCategoryName){
		
		this.setStrCategoryID(strCategoryID);
		this.setStrCategoryName(strCategoryName);
	}

	public String getStrCategoryID() {
		return strCategoryID;
	}

	public void setStrCategoryID(String strCategoryID) {
		this.strCategoryID = strCategoryID;
	}

	public String getStrCategoryName() {
		return strCategoryName;
	}

	public void setStrCategoryName(String strCategoryName) {
		this.strCategoryName = strCategoryName;
	}
	
	

}
