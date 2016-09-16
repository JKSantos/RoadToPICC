package com.gss.actions.Product;

import java.util.List;

import com.gss.dao.ProductJDBCRepository;

public class GetProductNames {
	
	private List<String> names;
	
	public String execute(){
		this.names = ProductJDBCRepository.getProductNames();
		
		return "success";
	}

	public List<String> getNames() {
		return names;
	}


}
