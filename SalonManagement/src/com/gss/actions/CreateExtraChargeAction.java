package com.gss.actions;

import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateExtraChargeAction {

	private String strECName;
	private String strECDetails;
	private String price;
	
	public String execute() throws Exception{
		
		double dblECPrice = PriceFormatHelper.convertToDouble((price + "0"), "Php ");
		ExtraChargeService service = new ExtraChargeServiceImpl();
		ExtraCharge extra = new ExtraCharge(1, strECName, strECDetails, dblECPrice, 1);
		
		if(service.createExtraCharge(extra) == true)
			return "success";
		else
			return "failed";
		
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

	public void setPrice(String price) {
		this.price = price;
	}

}
