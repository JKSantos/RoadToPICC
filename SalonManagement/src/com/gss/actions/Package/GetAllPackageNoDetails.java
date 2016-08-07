package com.gss.actions.Package;

import java.util.List;

import com.gss.model.Package;

public class GetAllPackageNoDetails {
	
	private List<Package> packageList;
	private String result = "success";
	
	public String execute(){
		
		this.packageList = (Package.getAllPackageNoDetails());
		
		return result;	
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public String getResult(){
		return this.result;
	}
}
