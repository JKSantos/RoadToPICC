package com.gss.actions.Package;

import java.util.List;

import com.gss.model.Package;

public class QueryAllPackage {
	
	private List<Package> packageList;
	
	public String execute(){
		
		this.packageList = Package.queryAllPackage();
		
		return "success";
	}

	public List<Package> getPackageList() {
		return packageList;
	}
}
