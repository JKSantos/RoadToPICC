package com.gss.actions;
import java.util.List;
import java.util.Map;

import com.gss.model.Product;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;


public class TransactionInventoryAction {

	private List<Product> productList;
	private Map<String, Object> userSession;
	
	public String execute(){
	
		if(!userSession.containsKey("firstName") && !userSession.containsKey("lastName") && !userSession.containsKey("id")){
			
			return "input";
		}
		else{
		
			ProductService productService = new ProductServiceImpl();
			
			this.productList = productService.getAllProducts();
			System.out.println(productList.size());
			
			return "success";
		}
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}

