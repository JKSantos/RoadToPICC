package com.gss.actions.Package;

import java.util.List;
import com.gss.model.Package;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;

public class GetAllPackage {
	
	private List<Package> packageList;
	
	public String execute(){
		
		PackageService service = new PackageServiceImpl();
		this.packageList = service.getAllPackage();
		
		return "success";
	}

	public List<Package> getPackageList() {
		return packageList;
	}
}
