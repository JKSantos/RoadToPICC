package com.gss.actions.WalkIn;

import com.gss.dao.WalkInTransRepository;
import com.gss.model.WalkIn;

public class GetWalkInByID {
	
	private WalkIn walkin;
	
	private int intWalkInID;
	
	public String execute(){
		this.walkin = WalkInTransRepository.getWalkInByID(intWalkInID);
		
		return "success";
	}

	public WalkIn getWalkin() {
		return walkin;
	}

	public void setIntWalkInID(int intWalkInID) {
		this.intWalkInID = intWalkInID;
	}
}
