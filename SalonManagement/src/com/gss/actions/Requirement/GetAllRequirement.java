package com.gss.actions.Requirement;

import java.util.List;

import com.gss.dao.RequirementDao;
import com.gss.model.Requirement;

public class GetAllRequirement {
	
	private List<Requirement> requirements;
	
	public String execute(){
		this.requirements = RequirementDao.getAllRequirement();
		
		return "success";
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}
}
