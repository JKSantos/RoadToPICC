package com.gss.actions.Service;

import com.gss.dao.ProductJDBCRepository;
import com.gss.dao.ServiceJDBCRepository;

public class ServiceCategory {
	
	private String categoryName;
	private int type;
	
	private String result;
	
	public String add(){
		
		if(ServiceJDBCRepository.searchCategory(categoryName).equalsIgnoreCase("existing")){
			this.result = "existing";
			return "existing";
		}else{
			if(ServiceJDBCRepository.category(categoryName, type)){
				this.result = "success";
				return "success";
			}else{
				this.result = "failed";
				return "failed";
			}
		}
		
	}
	
	public String remove(){
		if(ServiceJDBCRepository.category(categoryName, type)){
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
