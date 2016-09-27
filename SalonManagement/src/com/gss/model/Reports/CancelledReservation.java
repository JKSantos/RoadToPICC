package com.gss.model.Reports;

public class CancelledReservation {
	
	private String classification;
	private int event;
	private int homeService;

	public CancelledReservation(String classification, int event, int homeService) {
		super();
		this.classification = classification;
		this.event = event;
		this.homeService = homeService;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public int getHomeService() {
		return homeService;
	}

	public void setHomeService(int homeService) {
		this.homeService = homeService;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
}
