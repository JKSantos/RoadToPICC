package com.gss.actions.BusinessDependencies;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Dependency;

public class GetAllDependenciesAction {
	
	private List<Dependency> dependencies;
	
	public String execute() throws SQLException{
		
		this.dependencies = Dependency.getAllDependencies();
		
		return "success";
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}

}
