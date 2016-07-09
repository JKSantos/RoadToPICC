package com.gss.model;

public class ProductWalkIn {
	
	private int intProductWalkInID;
	private Product product;
	private int intQuantity;
	
	public ProductWalkIn(int intProductWalkInID, Product product, int intQuantity){
		
		this.intProductWalkInID = intProductWalkInID;
		this.product = product;
		this.intQuantity = intQuantity;
	}

	public int getIntProductWalkInID() {
		return intProductWalkInID;
	}

	public void setIntProductWalkInID(int intProductWalkInID) {
		this.intProductWalkInID = intProductWalkInID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}
}
