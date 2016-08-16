package com.gss.actions.Location;

import java.util.List;

import com.gss.model.Location;

public class QueryAllLocation {
	
	private List<Location> locationList;
	
	public String execute(){
		
		this.locationList = Location.queryAllLocation();
		
		return "success";
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}
}
