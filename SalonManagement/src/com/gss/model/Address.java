package com.gss.model;

public class Address {
	
	private String strStreet;
	private Barangay barangay;
	private City city;
	
	public Address(String strStreet, Barangay barangay, City city){
		
		this.strStreet = strStreet;
		this.barangay = barangay;
		this.city = city;
	}

	public String getStrStreet() {
		return strStreet;
	}

	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}

	public Barangay getBarangay() {
		return barangay;
	}

	public void setBarangay(Barangay barangay) {
		this.barangay = barangay;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
