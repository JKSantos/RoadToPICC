package com.gss.actions;

import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class DeactivateItemAction {
	
	private int intItemID;
	
	
	public String deactivateProduct(){
		
		ProductService service = new ProductServiceImpl();
		
		if(service.deactivateProduct(intItemID) == true){
			return "success";
		}
		else{
			return "failed";
		}
	}
	
	public String deactivateService(){
		
		ServiceService service = new ServiceServiceImpl();
		
		System.out.println(intItemID + ">>>>>>>>>>>>>>");

		if(service.deactivateService(intItemID) == true){
			return "success";
		}
		else{
			return "failed";
		}
	}

	public int getIntItemID() {
		return intItemID;
	}

	public void setIntItemID(int intItemID) {
		this.intItemID = intItemID;
	}

}
