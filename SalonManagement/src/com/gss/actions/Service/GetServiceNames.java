package com.gss.actions.Service;

import java.util.List;
import com.gss.dao.ServiceJDBCRepository;

public class GetServiceNames {

	private List<String> names;
	
	public String execute(){
		this.names = ServiceJDBCRepository.getServiceNames();
		
		return "success";
	}

	public List<String> getNames() {
		return names;
	}
}
