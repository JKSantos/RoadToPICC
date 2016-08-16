package com.gss.service;

import java.util.List;

import com.gss.dao.LocationJDBCRepository;
import com.gss.dao.LocationRepository;
import com.gss.model.Location;

public class LocationServiceImpl implements LocationService{

	@Override
	public boolean createLocation(Location location) {

		LocationRepository repo = new LocationJDBCRepository();
		
		return repo.createLocation(location);
		
	}

	@Override
	public boolean updateLocation(Location location) {

		LocationRepository repo = new LocationJDBCRepository();
		
		return repo.updateLocation(location);
	}

	@Override
	public List<Location> getAllLocation() {
		
		LocationRepository repo = new LocationJDBCRepository();
		
		return repo.getAllLocation();
	}

	@Override
	public boolean deactivateLocation(int intLocationID) {
		
		LocationRepository repo = new LocationJDBCRepository();
		
		return repo.deactivateLocation(intLocationID);
	}

	@Override
	public List<Location> queryAllLocation() {
		
		LocationRepository repo = new LocationJDBCRepository();
		
		return repo.queryAllLocation();
	}

}
