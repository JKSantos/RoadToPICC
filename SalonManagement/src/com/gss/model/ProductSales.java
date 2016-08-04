package com.gss.model;

import java.util.Date;
import java.util.List;

import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;

public class ProductSales {

	private int intSalesID;
	private Date datCreated;
	private Date deliveryDate;
	private int intType;
	private String strName;
	private String strAddress;
	private int intLocationID;
	private String strContactNo;
	private List<ProductOrder> productList;
	private Invoice invoice;
	private String strStatus;
	
	public ProductSales(int intSalesID, Date datCreated, Date deliveryDate, int intType, String strName,
			String strAddress, int intLocationID, String strContactNo, List<ProductOrder> productList, Invoice invoice,
			String strStatus) {
		super();
		this.intSalesID = intSalesID;
		this.datCreated = datCreated;
		this.deliveryDate = deliveryDate;
		this.intType = intType;
		this.strName = strName;
		this.strAddress = strAddress;
		this.intLocationID = intLocationID;
		this.strContactNo = strContactNo;
		this.productList = productList;
		this.invoice = invoice;
		this.strStatus = strStatus;
	}
	public int getIntSalesID() {
		return intSalesID;
	}
	public void setIntSalesID(int intSalesID) {
		this.intSalesID = intSalesID;
	}
	public Date getDatCreated() {
		return datCreated;
	}
	public void setDatCreated(Date datCreated) {
		this.datCreated = datCreated;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getIntType() {
		return intType;
	}
	public void setIntType(int intType) {
		this.intType = intType;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrAddress() {
		return strAddress;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public int getIntLocationID() {
		return intLocationID;
	}
	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}
	public String getStrContactNo() {
		return strContactNo;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	public List<ProductOrder> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductOrder> productList) {
		this.productList = productList;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public static List<ProductSales> getAllProductSales(){
		ProductSalesService service = new ProductSalesServiceImpl();
		
		return service.getAllProductSales();
	}
	
	public static ProductSales search(int invoiceID, List<ProductSales> searchList){
		
		
		
		for(int index = 0; index < searchList.size(); index++){
			
			if(searchList.get(index).getInvoice().getIntInvoiceID() == invoiceID)
				return searchList.get(index);
		}
		return null;
	}

}
