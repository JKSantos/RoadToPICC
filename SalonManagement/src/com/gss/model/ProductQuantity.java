package com.gss.model;

public class ProductQuantity {
	
	private int intID;
	private int intProductID;
	private int intQuantity;
	
	public ProductQuantity(int intID, int intProductID, int intQuantity) {
		super();
		this.intID = intID;
		this.intProductID = intProductID;
		this.intQuantity = intQuantity;
	}
	public int getIntID() {
		return intID;
	}
	public void setIntID(int intID) {
		this.intID = intID;
	}
	public int getIntProductID() {
		return intProductID;
	}
	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}
	public int getIntQuantity() {
		return intQuantity;
	}
	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}
}
