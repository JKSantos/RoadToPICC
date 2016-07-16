package com.gss.actions;

import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class UpdateExtraChargeAction {
	
	private int intECID;
	private String strECName;
	private String strECDetails;
	private String price;
	
	public String execute() throws Exception{
		
		System.out.println(intECID + " " + strECName);
		System.out.println(strECDetails + " " + price);
		
		double dblPrice = PriceFormatHelper.convertToDouble((price + "0"), "Php ");
		ExtraChargeService service = new ExtraChargeServiceImpl();
		ExtraCharge extra = new ExtraCharge(intECID, strECName, strECDetails, dblPrice, 1);
		
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
