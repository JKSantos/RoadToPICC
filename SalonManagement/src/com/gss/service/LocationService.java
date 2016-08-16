package com.gss.service;

import java.util.List;

import com.gss.model.Location;

public interface LocationService {
	
	public boolean createLocation(Location location);
	
	public boolean updateLocation(Location location);
	
	public boolean deactivateLocation(int intLocationID);
	
	public List<Location> getAllLocation();

	public List<Location> queryAllLocation();

}
