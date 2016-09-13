package com.gss.actions.Location;

import com.gss.dao.LocationJDBCRepository;

public class ValidateLocaton {
	
	private String barangay;
	private String city;
	
	private String result;
	
	public String execute(){
		
		this.result = LocationJDBCRepository.checkLocationName(barangay, city);
		
		return result;
	}

	public void setBarangay(String barangay) {
		this.barangay = barangay;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getResult() {
		return result;
	}
}
