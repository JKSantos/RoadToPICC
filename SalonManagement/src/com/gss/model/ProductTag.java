package com.gss.model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

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
	
	public static String toString(int intTagType){
		
		String tag = null;
		
		switch(intTagType){
			case 1:
				tag = "DEFECTIVE";
				break;
			case 2:
				tag = "LOST";
				break;
			case 3:
				tag = "EXPIRED";
				break;
			case 4:
				tag = "CONSUMED";
				break;
		}
		return tag;
	}
	
	public static List<ProductTag> getAllTagList(){
		ProductTags service = new ProductTagImpl();
		
		try {
			return service.getAllTag();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean restoreTag(ProductTag tag) throws SQLException{
		ProductTags prod = new ProductTagImpl();
		
		return prod.restoreTag(tag);
	}
}
