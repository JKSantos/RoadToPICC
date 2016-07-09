package com.gss.utilities;

import java.util.List;

import com.gss.model.ServicePackage;

public class ServicePackageComparison {
	
	public String compare(ServicePackage servPack, List<ServicePackage> serviceList)throws NullPointerException{
		
		for(int intCtr = 0; intCtr < serviceList.size(); intCtr++){
			
			ServicePackage newPack = servPack;
			ServicePackage oldPack = serviceList.get(intCtr);
			
			int newID = newPack.getIntServicePackageID();
			int oldID = oldPack.getIntServicePackageID();
			int newQuantity = newPack.getIntQuantity();
			int oldQuantity = oldPack.getIntQuantity();
				
			if(newID == oldID && newQuantity == oldQuantity){
				System.out.println(">>same " + servPack.getIntServicePackageID() + " " + oldPack.getIntServicePackageID());
				return "same";
			}
			else if(newID == oldID && newQuantity != oldQuantity && newID == oldID && newQuantity != oldQuantity){
				System.out.println(">>update " + servPack.getIntServicePackageID() + " " + oldPack.getIntServicePackageID());
				return "update";
			}
			else if(intCtr == (serviceList.size() - 1)){
				System.out.println(">>new " + servPack.getIntServicePackageID() + " " + oldPack.getIntServicePackageID());
				return "new";
			}
			
		}
		
		return null;
		
	}

}
