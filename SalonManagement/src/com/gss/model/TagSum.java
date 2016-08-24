package com.gss.model;

import java.util.List;

import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class TagSum {
	
	private String dateFrom;
	private String dateTo;
	private int intProductID;
	private String strProductName;
	private int intQuantity;
	private int intDefective;
	private int intLost;
	private int intExpired;
	private int intConsumed;
	
	public TagSum(String dateFrom, String dateTo, int intProductID, String strProductName, int intQuantity,
			int intDefective, int intLost, int intExpired, int intConsumed) {
		super();
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.intProductID = intProductID;
		this.strProductName = strProductName;
		this.intQuantity = intQuantity;
		this.intDefective = intDefective;
		this.intLost = intLost;
		this.intExpired = intExpired;
		this.intConsumed = intConsumed;
	}



	public String getDateFrom() {
		return dateFrom;
	}



	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}



	public String getDateTo() {
		return dateTo;
	}



	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}



	public int getIntProductID() {
		return intProductID;
	}



	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}



	public String getStrProductName() {
		return strProductName;
	}



	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}



	public int getIntQuantity() {
		return intQuantity;
	}



	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}



	public int getIntDefective() {
		return intDefective;
	}



	public void setIntDefective(int intDefective) {
		this.intDefective = intDefective;
	}



	public int getIntLost() {
		return intLost;
	}



	public void setIntLost(int intLost) {
		this.intLost = intLost;
	}



	public int getIntExpired() {
		return intExpired;
	}



	public void setIntExpired(int intExpired) {
		this.intExpired = intExpired;
	}



	public int getIntConsumed() {
		return intConsumed;
	}



	public void setIntConsumed(int intConsumed) {
		this.intConsumed = intConsumed;
	}



	public static List<TagSum> getTagSum(String dateFrom, String dateTo){
		ProductTags tags = new ProductTagImpl();
		
		return tags.getTagSum(dateFrom, dateTo);
	}
}
