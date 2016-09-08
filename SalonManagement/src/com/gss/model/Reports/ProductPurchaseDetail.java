package com.gss.model.Reports;

import java.util.List;

public class ProductPurchaseDetail {
	
	private String classification;
	private List<ProductDetail> products;
	
	public ProductPurchaseDetail(String classification, List<ProductDetail> products) {
		super();
		this.classification = classification;
		this.products = products;
	}
	public String getClassification() {
		return classification;
	}
	public List<ProductDetail> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDetail> products) {
		this.products = products;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}

}
