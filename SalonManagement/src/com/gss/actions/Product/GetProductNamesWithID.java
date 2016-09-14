package com.gss.actions.Product;

import java.util.List;

import com.gss.dao.ProductJDBCRepository;

public class GetProductNamesWithID {
		
	private List<String> names;
	
	private int id;
	
	public String execute(){
		
		this.names = ProductJDBCRepository.getProductNames(id);
		
		return "success";
	}

	public List<String> getNames() {
		return names;
	}

	public void setId(int id) {
		this.id = id;
	}

}
