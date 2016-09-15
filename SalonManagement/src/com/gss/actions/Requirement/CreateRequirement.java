package com.gss.actions.Requirement;

import com.gss.dao.RequirementDao;

public class CreateRequirement {
	
	private String strRequirementName;
	
	public String execute(){
		return RequirementDao.createRequirement(strRequirementName);
	}

	public void setStrRequirementName(String strRequirementName) {
		this.strRequirementName = strRequirementName;
	}
}
