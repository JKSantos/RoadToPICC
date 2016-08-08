package com.gss.actions.ProductSales;

import java.util.List;

import com.gss.model.ProductSales;

public class GetAllProductRequest {
	
	List<ProductSales> productSalesList;
	
	public String execute(){
		
		this.productSalesList = ProductSales.getAllProductRequest();
		
		return "success";
	}

	public List<ProductSales> getProductSalesList() {
		return productSalesList;
	}
}
