package com.gss.actions;

import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class UpdateExtraChargeAction {
	
	private int intECID;
	private String strECName;
	private String strECDetails;
	
	public String execute(){

		ExtraChargeService service = new ExtraChargeServiceImpl();
		
		ExtraCharge extra = new ExtraCharge(intECID, strECName, strECDetails, 1);
		
		if(service.updateExtraCharge(extra) == true)
			return "success";
		else
			return "failed";

	}

	public int getIntECID() {
		return intECID;
	}

	public void setIntECID(int intECID) {
		this.intECID = intECID;
	}

	public String getStrECName() {
		return strECName;
	}

	public void setStrECName(String strECName) {
		this.strECName = strECName;
	}

	public String getStrECDetails() {
		return strECDetails;
	}

	public void setStrECDetails(String strECDetails) {
		this.strECDetails = strECDetails;
	}
	
	
}
