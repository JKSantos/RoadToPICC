package com.gss.model;

import java.util.List;

public class PromoWalkIn {
	
	private int intPromoWalkInID;
	private Promo promo;
	private List<PackageWalkIn> packages;
	private List<ServiceWalkIn> services;
	
	public PromoWalkIn(int intPromoWalkInID, Promo promo, List<PackageWalkIn> packages, List<ServiceWalkIn> services) {
		super();
		this.intPromoWalkInID = intPromoWalkInID;
		this.promo = promo;
		this.packages = packages;
		this.services = services;
	}
	
	public int getIntPromoWalkInID() {
		return intPromoWalkInID;
	}
	public void setIntPromoWalkInID(int intPromoWalkInID) {
		this.intPromoWalkInID = intPromoWalkInID;
	}
	public Promo getPromo() {
		return promo;
	}
	public void setPromo(Promo promo) {
		this.promo = promo;
	}
	public List<PackageWalkIn> getPackages() {
		return packages;
	}
	public void setPackages(List<PackageWalkIn> packages) {
		this.packages = packages;
	}
	public List<ServiceWalkIn> getServices() {
		return services;
	}
	public void setServices(List<ServiceWalkIn> services) {
		this.services = services;
	}
	public PromoWalkIn createNullPromoWalkIn(Promo promo, List<PackageWalkIn> packages, List<ServiceWalkIn> services){
		return new PromoWalkIn(1, promo, packages, services);
	}
}
