package com.gss.model;

import java.sql.Date;

public class ProductTag {
	
	private int intTagID;
	private Product product;
	private Date dateTagged;
	private int intTagType;
	private Employee tagBy;
	private int intQuantity;
	
	public ProductTag(int intTagID, Product product, Date dateTagged, int intTagType, Employee tagBy, int intQuantity) {
		super();
		this.intTagID = intTagID;
		this.product = product;
		this.dateTagged = dateTagged;
		this.intTagType = intTagType;
		this.tagBy = tagBy;
		this.intQuantity = intQuantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
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


	public Employee getTagBy() {
		return tagBy;
	}


	public void setTagBy(Employee tagBy) {
		this.tagBy = tagBy;
	}


	public Date getDateTagged() {
		return dateTagged;
	}


	public void setDateTagged(Date dateTagged) {
		this.dateTagged = dateTagged;
	}

	public int getIntTagID() {
		return intTagID;
	}

	public void setIntTagID(int intTagID) {
		this.intTagID = intTagID;
	}
	
	
}
