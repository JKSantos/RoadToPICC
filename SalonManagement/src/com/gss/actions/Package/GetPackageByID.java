package com.gss.actions.Package;

import com.gss.model.Package;

public class GetPackageByID {
	
	private int intPackageID;
	private Package packagee;
	
	public String execute(){
		
		this.packagee = (Package.getPackageByID(intPackageID));
		
		return "success";
	}

	public Package getPackagee() {
		return packagee;
	}
	
	public void setIntPackageID(int intPackageID){
		this.intPackageID = intPackageID;
	}
}
