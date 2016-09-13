package com.gss.actions.Location;

import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;

public class DeactivateLocation {
	
	private int intLocationID;
	
	public String execute(){
		
		LocationService service = new LocationServiceImpl();
		System.out.println(this.intLocationID);
		if(service.deactivateLocation(this.intLocationID) == true)
			return "success";
		else
			return "failed";
		
	}

	public int getIntLocationID() {
		return intLocationID;
	}

	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}
}
