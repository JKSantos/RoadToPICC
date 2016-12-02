package com.gss.actions.BusinessDependencies;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Dependency;

public class GetAllDependenciesAction {
	
	private List<Dependency> dependencies;
	
	public String execute() throws SQLException{
		
		this.dependencies = Dependency.getAllDependencies();
		
		if(dependencies.size() == 9) {
			
			dependencies.add(new Dependency(1, "service_fee", "20"));
			dependencies.add(new Dependency(1, "processing_fee", "5"));
		}
		return "success";
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

}
