package com.gss.actions.BusinessDependencies;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.model.Dependency;

public class UpdateDependenciesAction {
	
	private String strName = "";
	private String strValue = "";
	
	public String execute() throws SQLException{
		
		String[] strName		= this.strName.split(",");
		String[] strValue		= this.strValue.split(",");
		
		List<Dependency> dependencies = new ArrayList<Dependency>();
		
		for(int index = 0; index < strName.length; index++){
			Dependency dependency = new Dependency(1, strName[index].trim(), strValue[index].trim());
			dependencies.add(dependency);
		}
		
		if(Dependency.updateDependency(dependencies)){
			return "success";
		}
		else{
			return "failed";
		}
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}
}
