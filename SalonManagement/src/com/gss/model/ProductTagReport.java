package com.gss.model;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class ProductTagReport {
	
	private int intTagID;
	private String strProductName;
	private Date datDateTagged;
	private String tagType;
	private int intQuantity;
	private int intEmpID;
	private String strEmployee;
	
	public ProductTagReport(int intTagID, String strProductName, Date datDateTagged, String tagType, int intQuantity,
			int intEmpID, String strEmployee) {
		super();
		this.intTagID = intTagID;
		this.strProductName = strProductName;
		this.datDateTagged = datDateTagged;
		this.tagType = tagType;
		this.intQuantity = intQuantity;
		this.intEmpID = intEmpID;
		this.strEmployee = strEmployee;
	}
	public int getIntTagID() {
		return intTagID;
	}
	public void setIntTagID(int intTagID) {
		this.intTagID = intTagID;
	}
	public String getStrProductName() {
		return strProductName;
	}
	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}
	public Date getDatDateTagged() {
		return datDateTagged;
	}
	public void setDatDateTagged(Date datDateTagged) {
		this.datDateTagged = datDateTagged;
	}
	public String getTagType() {
		return tagType;
	}
	public void setTagType(String tagType) {
		this.tagType = tagType;
	}
	public int getIntQuantity() {
		return intQuantity;
	}
	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}
	public String getStrEmployee() {
		return strEmployee;
	}
	public void setStrEmployee(String strEmployee) {
		this.strEmployee = strEmployee;
	}
	public static List<ProductTagReport> getProductTagReport() throws SQLException{
		ProductTags service = new ProductTagImpl();
		
		return service.getAllProductTag();
	}
	public static List<ProductTagReport> getFilteredProductTagReport(String dateFilterFrom, String dateFilterTo, String employeeFilter, String tagTypeFilter) throws SQLException{
		ProductTags service = new ProductTagImpl();
		
		return service.getFilteredProductTagReport();
	}
	public int getIntEmpID() {
		return intEmpID;
	}
	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
	}
}
