package com.gss.model;

import java.util.List;

import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class ExtraCharge {
	
	private int intECID;
	private String strECName;
	private String strECDetails;
	private double dblECPrice;
	private int intECStatus;
	
	public ExtraCharge(int intECID, String strECName, String strECDetails, double dblECPrice, int intECStatus){
		this.intECID = intECID;
		this.strECName = strECName;
		this.strECDetails = strECDetails;
		this.dblECPrice = dblECPrice;
		this.intECStatus = intECStatus;
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

	public int getIntECStatus() {
		return intECStatus;
	}

	public void setIntECStatus(int intECStatus) {
		this.intECStatus = intECStatus;
	}

	public double getDblECPrice() {
		return dblECPrice;
	}

	public void setDblECPrice(double dblECPrice) {
		this.dblECPrice = dblECPrice;
	}
	
	public static ExtraCharge createNullExtra(int intExtraID){
		return new ExtraCharge(intExtraID, "", "", 0, intExtraID);
	}
	
	public static List<ExtraCharge> getAllExtraCharge(){
		ExtraChargeService service = new ExtraChargeServiceImpl();
		
		return service.getAllExtraCharges();
	}
	
	public static ExtraCharge searchExtraCharge(int intExtraID, List<ExtraCharge> extraChargeList){
		
		for(int index = 0; index < extraChargeList.size(); index++){
			if(intExtraID == extraChargeList.get(index).getIntECID())
				return extraChargeList.get(index);
		}
		
		return null;
	}

}
