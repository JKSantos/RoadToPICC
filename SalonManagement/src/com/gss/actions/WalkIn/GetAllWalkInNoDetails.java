package com.gss.actions.WalkIn;

import java.util.List;

import com.gss.model.WalkIn;

public class GetAllWalkInNoDetails {
	
	private List<WalkIn> walkInList;
	
	public String execute(){
		
		this.walkInList = WalkIn.getAllWalkInNoDetails();
		
		return "success";
	}

	public List<WalkIn> getWalkInList() {
		return walkInList;
	}
}
