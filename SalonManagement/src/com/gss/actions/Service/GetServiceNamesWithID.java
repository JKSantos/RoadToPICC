package com.gss.actions.Service;

import java.util.List;

import com.gss.dao.ServiceJDBCRepository;

public class GetServiceNamesWithID {
	
	private List<String> names;
	
	private int id;
	
	public String execute(){
		this.names = ServiceJDBCRepository.getServiceNames(id);
		
		return "success";
	}

	public List<String> getNames() {
		return names;
	}

	public void setId(int id) {
		this.id = id;
	}
}
