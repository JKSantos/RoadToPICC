package com.gss.model;

public class ProductPackage {
	
	private int intProductPackageID;
	private int intPackageID;
	private Product product;
	private int intProductQuantity;
	private int intStatus;
	
	public ProductPackage(int intProductPackageID, int intPackageID, Product product, int intProductQuantity, int intStatus){
		
		this.setIntProductPackageID(intProductPackageID);
		this.product = product;
		this.intProductQuantity = intProductQuantity;
		this.intStatus = intStatus;
		this.intPackageID = intPackageID;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getIntProductQuantity() {
		return intProductQuantity;
	}

	public void setIntProductQuantity(int intProductQuantity) {
		this.intProductQuantity = intProductQuantity;
	}

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}

	public int getIntProductPackageID() {
		return intProductPackageID;
	}

	public void setIntProductPackageID(int intProductPackageID) {
		this.intProductPackageID = intProductPackageID;
	}

	public int getIntPackageID() {
		return intPackageID;
	}

	public void setIntPackageID(int intPackageID) {
		this.intPackageID = intPackageID;
	}

}
