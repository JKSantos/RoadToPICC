package com.gss.model;

public class ProductCategory {
	
	private int intCatID;
	private String strCategory;

	public ProductCategory(int intCatID, String strCategory) {
		super();
		this.intCatID = intCatID;
		this.strCategory = strCategory;
	}
	
	public int getIntCatID() {
		return intCatID;
	}
	public void setIntCatID(int intCatID) {
		this.intCatID = intCatID;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
}
