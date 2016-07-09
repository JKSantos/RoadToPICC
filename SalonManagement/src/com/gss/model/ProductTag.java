package com.gss.model;

public class ProductTag {
	
	private int intProductID;
	private int intTagType;
	private int intQuantity;
	
	
	public ProductTag(int intProductID, int intQuantity, int intTagType){
		
		this.intProductID = intProductID;
		this.intTagType = intTagType;
		this.intQuantity = intQuantity;
		
	}


	public int getIntProductID() {
		return intProductID;
	}


	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}


	public int getIntTagType() {
		return intTagType;
	}


	public void setIntTagType(int intTagType) {
		this.intTagType = intTagType;
	}


	public int getIntQuantity() {
		return intQuantity;
	}


	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}
	
	
}
