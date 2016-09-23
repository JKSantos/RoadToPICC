package com.gss.actions;

import com.gss.dao.ExtraChargeJDBCRepository;
import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class UpdateExtraChargeAction {
	
	private int intECID;
	private String strECName;
	private String strECDetails;
	private String price;
	
	private String result;
	
	public String execute() throws Exception{
		
		double dblECPrice = PriceFormatHelper.convertToDouble((price + "0"), "Php ");
		ExtraChargeService service = new ExtraChargeServiceImpl();
		ExtraCharge extra = new ExtraCharge(intECID, strECName.trim().toUpperCase(), strECDetails.trim().toUpperCase(), dblECPrice, 1);
		
		if(ExtraChargeJDBCRepository.checkExtraChargeName(strECName.trim().toUpperCase(), intECID).equalsIgnoreCase("valid")){
			if(service.updateExtraCharge(extra) == true){
				result = "success";
				return result;
			}
			else{
				result = "failed";
				return result;
		}
		}else{
			result = "existing";
			return result;
		}

	}

	public String getResult() {
		return result;
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
