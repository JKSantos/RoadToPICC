package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.model.ServicePackage;

public class SearchService {
	
	public Service search(int serviceID, List<Service> serviceList){
		
		for(int intCtr = 0; intCtr < serviceList.size(); intCtr++){
			Service serv = serviceList.get(intCtr);
			
			if(serviceID == serv.getIntServiceID())
				return serv;
		}
		return null;
		
	}
	
	public List<ServicePackage> compareServices(List<ServicePackage> newSet, List<ServicePackage> oldSet){
		
		List<ServicePackage> deactivatedSet = new ArrayList<ServicePackage>();
		
		for(int outer = 0; outer < oldSet.size(); outer++){
			
			Service oldService = oldSet.get(outer).getService();
			
			for(int inner = 0; inner < newSet.size(); inner++){
				
				Service newService = newSet.get(inner).getService();
				
				if(oldSet.get(outer).getIntServicePackageID() == newSet.get(inner).getIntServicePackageID() && oldService.getIntServiceID() == newService.getIntServiceID()){
					continue;
				}
				else if(inner == (newSet.size() - 1)){
					deactivatedSet.add(oldSet.get(outer));
				}
				
			}
		}
		
		
		return deactivatedSet;
	}

	public List<Service> searchList(String[] strServiceID, List<Service> serviceList){
		
		List<Service> selectedProducts = new ArrayList<Service>();
		
		for(int intCtr = 0; intCtr < strServiceID.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < serviceList.size(); intCtr2++){
				Service serv = serviceList.get(intCtr2);
				
				if(Integer.parseInt(strServiceID[intCtr]) == serv.getIntServiceID())
					selectedProducts.add(serv);
			}
		}
		
		return selectedProducts;
	}
	
	/*public List<Service> fromMockData(List<Service> mockServices) {
		List<Service> serviceList = new ArrayList<Service>();
		
		
		return serviceList;
	}*/
}
