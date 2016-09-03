package com.gss.actions.Package;

import java.util.List;

import com.gss.model.Package;

public class GetPackageByType {

	private String type;
	private List<Package> packageList;
	
	public String execute(){
	
		this.packageList = Package.getPackageByType(type);
		
		return "success";
	}
	
	public List<Package> getPackageList() {
		return packageList;
	}
	public void setType(String type) {
		this.type = type;
	}
}
