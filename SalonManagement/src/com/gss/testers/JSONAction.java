package com.gss.testers;

import com.opensymphony.xwork2.ActionSupport;

public class JSONAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String jsonData;
	
	public String execute(){
		
		System.out.println(jsonData);
		
		
		return SUCCESS;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
}
