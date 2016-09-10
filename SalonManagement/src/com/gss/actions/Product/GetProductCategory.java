package com.gss.actions.Product;

import java.util.List;

import com.gss.dao.ProductJDBCRepository;

public class GetProductCategory {
	
	private List<com.gss.model.ProductCategory> categories;
	
	public String execute(){
		this.categories = ProductJDBCRepository.getCategoryList();
		
		return "success";
	}

	public List<com.gss.model.ProductCategory> getCategories() {
		return categories;
	}
}
