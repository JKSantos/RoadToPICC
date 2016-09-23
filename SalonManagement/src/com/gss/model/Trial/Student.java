package com.gss.model.Trial;

public class Student {
	
	private int intStudID;
	private String strFName;
	private String strLName;
	private String strMName;
	private String strAddess;
	private String strContactNo;
	
	public Student(int intStudID, String strFName, String strLName, String strMName, String strAddess,
			String strContactNo) {
		super();
		this.intStudID = intStudID;
		this.strFName = strFName;
		this.strLName = strLName;
		this.strMName = strMName;
		this.strAddess = strAddess;
		this.strContactNo = strContactNo;
	}
	public int getIntStudID() {
		return intStudID;
	}
	public void setIntStudID(int intStudID) {
		this.intStudID = intStudID;
	}
	public String getStrFName() {
		return strFName;
	}
	public void setStrFName(String strFName) {
		this.strFName = strFName;
	}
	public String getStrLName() {
		return strLName;
	}
	public void setStrLName(String strLName) {
		this.strLName = strLName;
	}
	public String getStrMName() {
		return strMName;
	}
	public void setStrMName(String strMName) {
		this.strMName = strMName;
	}
	public String getStrAddess() {
		return strAddess;
	}
	public void setStrAddess(String strAddess) {
		this.strAddess = strAddess;
	}
	public String getStrContactNo() {
		return strContactNo;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
}
