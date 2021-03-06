package com.gss.model;

public class ProductOrder {
	
	private int intID;
	private Product product;
	private int intQuantity;
	private String discountType;
	private double discountAmount;
	private int intStatus;
	
	public ProductOrder(int intID, Product product, int intQuantity, int intStatus){
		this.intID = intID;
		this.product = product;
		this.intQuantity = intQuantity;
		this.intStatus = intStatus;
	}

	public int getIntID() {
		return intID;
	}

	public void setIntID(int intID) {
		this.intID = intID;
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

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}
