package com.gss.actions.Product;

import com.gss.dao.ProductJDBCRepository;

public class ProductCategory {
	
	private String categoryName;
	private int type;
	
	private String result;
	
	public String add(){
		
		if(ProductJDBCRepository.searchCategory(categoryName).equalsIgnoreCase("existing")){
			this.result = "existing";
			return "existing";
		}else{
			if(ProductJDBCRepository.category(categoryName, type)){
				this.result = "success";
				return "success";
			}else{
				this.result = "failed";
				return "failed";
			}
		}
		
	}
	
	public String remove(){
		if(ProductJDBCRepository.category(categoryName, type)){
			this.result = "success";
			return "success";
		}else{
			this.result = "failed";
			return "failed";
		}
	}

	public String getResult() {
		return result;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setType(int type) {
		this.type = type;
	}
}
