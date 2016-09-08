package com.gss.model.Reports;

public class SalesReportDetail {
	
	private String classification;
	private double homeService;
	private double eventService;
	private double walkin;
	private double delivery;
	private double pickup;
	
	public SalesReportDetail(String classification, double homeService, double eventService, double walkin,
			double delivery, double pickup) {
		super();
		this.classification = classification;
		this.homeService = homeService;
		this.eventService = eventService;
		this.walkin = walkin;
		this.delivery = delivery;
		this.pickup = pickup;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public double getHomeService() {
		return homeService;
	}

	public void setHomeService(double homeService) {
		this.homeService = homeService;
	}

	public double getEventService() {
		return eventService;
	}

	public void setEventService(double eventService) {
		this.eventService = eventService;
	}

	public double getWalkin() {
		return walkin;
	}

	public void setWalkin(double walkin) {
		this.walkin = walkin;
	}

	public double getDelivery() {
		return delivery;
	}

	public void setDelivery(double delivery) {
		this.delivery = delivery;
	}

	public double getPickup() {
		return pickup;
	}

	public void setPickup(double pickup) {
		this.pickup = pickup;
	}
}
