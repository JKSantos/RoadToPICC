package com.gss.actions;

import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class DeactivateExtraChargeAction {
	
	private int intECID;
	
	public String execute(){
		
		ExtraChargeService service = new ExtraChargeServiceImpl();
		
		if(service.deactivateExtraCharge(intECID) == true){
			return "success";
		} 
		else{
			return "failed";
		}
		
	}

	public int getIntECID() {
		return intECID;
	}

	public void setIntECID(int intECID) {
		this.intECID = intECID;
	}

}
