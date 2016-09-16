package com.gss.actions.Requirement;

import com.gss.dao.RequirementDao;

public class CreateRequirement {
	
	private String strRequirementName;
	private int id;
	
	public String execute(){
		id = RequirementDao.createRequirement(strRequirementName);
		
		if(id != 0)
			return "success";
		else if(id == -1)
			return "failed";
		else
			return "existing";
	}

	public void setStrRequirementName(String strRequirementName) {
		this.strRequirementName = strRequirementName;
	}
	
	public int getId() {
		return id;
	}
}
