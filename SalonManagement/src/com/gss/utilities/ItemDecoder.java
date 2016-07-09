package com.gss.utilities;

import java.util.List;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.model.Package;

public class ItemDecoder {
	
	public String[] getServiceQuantity(String[] serviceOrder, String[] servicesQuantity){
		
		String[] includedQuantity = new String[serviceOrder.length];
		int order = 0;
		
		for(int intCtr = 0; intCtr < servicesQuantity.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < serviceOrder.length; intCtr2++){
				
				if(intCtr == Integer.parseInt(serviceOrder[intCtr2])){
					includedQuantity[order] = servicesQuantity[intCtr];
					order++;
				}
			}
		}
		
		return includedQuantity;
		
	}
	
	public String[] serviceOrderByChecked(List<Service> serviceList, String[] checkedServices){
		
		int intCounter = 0;
		int order = 0;
		String[] serviceOrder = new String[checkedServices.length];
		
		for(int intCtr = 0; intCtr < checkedServices.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < serviceList.size(); intCtr2++){
				
				if(Integer.parseInt(checkedServices[intCtr]) == serviceList.get(intCtr2).getIntServiceID()){
					
					serviceOrder[order] = String.valueOf(intCounter);
					order++;
					intCounter = 0;
					break;
				}
				else
					intCounter++;
				
			}
		}
		
		return serviceOrder;
	}
	
	
	public String[] getProductQuantity(String[] productOrder, String[] productQuantity){
		
		String[] includedQuantity = new String[productOrder.length];
		int order = 0;
		
		for(int intCtr = 0; intCtr < productQuantity.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < productOrder.length; intCtr2++){
				
				if(intCtr == Integer.parseInt(productOrder[intCtr2])){
					includedQuantity[order] = productQuantity[intCtr];
					order++;
				}
			}
		}
		
		return includedQuantity;
		
	}
	
	public String[] productOrderByChecked(List<Product> productList, String[] checkedProducts){
		
		int intCounter = 0;
		int order = 0;
		String[] productOrder = new String[checkedProducts.length];
		
		for(int intCtr = 0; intCtr < checkedProducts.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < productList.size(); intCtr2++){
						
				if(Integer.parseInt(checkedProducts[intCtr]) == productList.get(intCtr2).getIntProductID()){
					
					productOrder[order] = String.valueOf(intCounter);
					order++;
					intCounter = 0;
					break;
				}
				else
					intCounter++;
				
			}
		}
		
		return productOrder;
	}
	
	public String[] getPackageQuantity(String[] packageOrder, String[] packageQuantity){
		
		String[] includedQuantity = new String[packageOrder.length];
		int order = 0;
		
		for(int intCtr = 0; intCtr < packageQuantity.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < packageOrder.length; intCtr2++){
				
				if(intCtr == Integer.parseInt(packageOrder[intCtr2])){
					includedQuantity[order] = packageQuantity[intCtr];
					order++;
				}
			}
		}
		
		return includedQuantity;
		
	}
	
	public String[] packageOrderByChecked(List<Package> packageList, String[] checkedPackage){
		
		int intCounter = 0;
		int order = 0;
		String[] packageOrder = new String[checkedPackage.length];
		
		for(int intCtr = 0; intCtr < checkedPackage.length; intCtr++){
			
			for(int intCtr2 = 0; intCtr2 < packageList.size(); intCtr2++){
				
				if(Integer.parseInt(checkedPackage[intCtr]) == packageList.get(intCtr2).getIntPackageID()){
					
					packageOrder[order] = String.valueOf(intCounter);
					order++;
					intCounter = 0;
					break;
				}
				else
					intCounter++;
				
			}
		}
		
		return packageOrder;
	}

}
