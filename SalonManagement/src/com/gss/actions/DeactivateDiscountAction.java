package com.gss.actions;

import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;

public class DeactivateDiscountAction {
	
	private int intDiscountID;
	
	public String execute(){
		
		DiscountService service = new DiscountServiceImpl();
		
		if(service.deactivateDiscount(intDiscountID) == true){
			return "success";
		}
		else{
			return "failed";
		}
		
	}

	public int getIntDiscountID() {
		return intDiscountID;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}

}
