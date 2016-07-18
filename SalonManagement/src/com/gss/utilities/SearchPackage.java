package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Package;

public class SearchPackage {
	
	public List<Package> searchList(String[] strPackageID, List<Package> packageList){
		
		List<Package> selectedPackages = new ArrayList<Package>();
		
		for(int intCtr = 0; intCtr < strPackageID.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < packageList.size(); intCtr2++){
				Package pack = packageList.get(intCtr2);
				
				if(Integer.parseInt(strPackageID[intCtr]) == pack.getIntPackageID())
					selectedPackages.add(pack);
			}
		}
		
		return selectedPackages;
	}

}
