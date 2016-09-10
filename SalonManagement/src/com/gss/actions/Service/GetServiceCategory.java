package com.gss.actions.Service;

import java.util.List;

import com.gss.dao.ServiceJDBCRepository;

public class GetServiceCategory {
	
	private List<com.gss.model.ServiceCategory> categoryList;
	
	public String execute(){
		this.categoryList = ServiceJDBCRepository.getCategories();
		
		return "success";
	}

	public List<com.gss.model.ServiceCategory> getCategoryList() {
		return categoryList;
	}

}
