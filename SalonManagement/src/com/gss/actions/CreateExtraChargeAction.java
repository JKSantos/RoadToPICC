package com.gss.actions;

import com.gss.dao.ExtraChargeJDBCRepository;
import com.gss.dao.LocationJDBCRepository;
import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateExtraChargeAction {

	private String strECName;
	private String strECDetails;
	private double price;
	
	private String result;
	
	public String execute() throws Exception{
	
		System.out.println("Name: " + strECName);
		System.out.println("Desc: " + strECDetails);
		System.out.println("Price: " + price);
		
		ExtraChargeService service = new ExtraChargeServiceImpl();
		ExtraCharge extra = new ExtraCharge(1, strECName.trim().toUpperCase(), strECDetails.trim().toUpperCase(), price, 1);
		
		if(ExtraChargeJDBCRepository.checkExtraChargeName(strECName.trim().toUpperCase()).equalsIgnoreCase("valid")){
			if(service.createExtraCharge(extra) == true){
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
	
	public void setStrECName(String strECName) {
		this.strECName = strECName.trim().toUpperCase();
	}

	public void setStrECDetails(String strECDetails) {
		this.strECDetails = strECDetails.trim().toUpperCase();
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
