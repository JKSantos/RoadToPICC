package com.gss.model.Reports;

public class CancelledReservation {
	
	private int event;
	private int homeService;
	
	

	public CancelledReservation(int event, int homeService) {
		super();
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
	
	

}
