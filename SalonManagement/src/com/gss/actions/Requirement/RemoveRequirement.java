package com.gss.actions.Requirement;

import com.gss.dao.RequirementDao;

public class RemoveRequirement {

	private int intRequirementID;
	
	public String execute(){
		return RequirementDao.removeRequirement(intRequirementID);
	}

	public void setIntRequirementID(int intRequirementID) {
		this.intRequirementID = intRequirementID;
	}
}
