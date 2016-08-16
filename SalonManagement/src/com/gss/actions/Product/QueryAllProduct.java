package com.gss.actions.Product;

import java.util.List;

import com.gss.model.Product;

public class QueryAllProduct {
	
	private List<Product> productList;
	
	public String execute(){
		
		this.productList = Product.queryAllProduct();
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}
}
