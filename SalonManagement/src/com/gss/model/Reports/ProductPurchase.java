package com.gss.model.Reports;

import java.util.List;

public class ProductPurchase {

	private String type;
	private List<ProductPurchaseDetail> details;
	
	public ProductPurchase(String type, List<ProductPurchaseDetail> details) {
		super();
		this.type = type;
		this.details = details;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ProductPurchaseDetail> getDetails() {
		return details;
	}
	public void setDetails(List<ProductPurchaseDetail> details) {
		this.details = details;
	}
	
	
}
