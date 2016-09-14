package com.gss.actions.Location;

import com.gss.dao.LocationJDBCRepository;
import com.gss.model.Location;

public class GetByID {
	
	private Location location;
	private int id;
	
	public String execute(){
		this.location = LocationJDBCRepository.getLocationByID(id);
		return "success";
	}
	
	public Location getLocation() {
		return location;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
