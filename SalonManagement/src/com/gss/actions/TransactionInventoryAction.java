package com.gss.actions;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class TransactionInventoryAction {

	private List<Product> productList;

	public String execute(){
		
		ProductService productService = new ProductServiceImpl();
		
		this.productList = productService.getAllProducts();
		System.out.println(productList.size());
		
		return "success";
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}

