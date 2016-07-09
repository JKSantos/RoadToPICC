package com.gss.actions.Product;

import java.util.List;

import com.gss.model.Product;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;

public class GetAllProduct {
	
	public List<Product> productList;
	
	public String execute(){
		
		ProductService service = new ProductServiceImpl();
		this.productList = service.getAllProductsNoImage();
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}
}
