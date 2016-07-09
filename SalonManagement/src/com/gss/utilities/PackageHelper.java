package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

public class PackageHelper {
	
	public static int convertToSingleInt(List<Integer> intPackageType){
		
		if(intPackageType.size() == 1){
			
			return intPackageType.get(0);
		}
		else if(intPackageType.size() == 2){
			if(intPackageType.contains(1) == true){
				if(intPackageType.contains(2) == true){
					return 4;
				}
			}
			
			if(intPackageType.contains(1) == true){
				if(intPackageType.contains(3) == true){
					return 5;
				}
			}
			
			if(intPackageType.contains(2) == true){
				if(intPackageType.contains(3) == true){
					return 6;
				}
			}
		}
		else
			return 7;
		
		return 0;
	}

}
