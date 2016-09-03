package com.gss.actions.BusinessDependencies;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Dependency;

public class GetDependenciesAction {
	
	private List<Dependency> dependencies;
	
	public String execute(){
		try {
			this.dependencies = Dependency.getAllDependencies();
			
			return "success";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "failed";
		}
	}

	public List<Dependency> getDependencies() {
		return dependencies;
	}
}
