package com.gss.actions.Reports.CancelledReservations;

import java.util.List;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.CancelledReservation;
import com.gss.utilities.ReportsHelper;

public class GetCancelledReservaiton {
	
	private List<CancelledReservation> cancelledReservations;
	
	private String type;	
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		
		if(type.equalsIgnoreCase("monthly")){
			this.cancelledReservations = CancelledReservationsDao.getCancelledReservations(ReportsHelper.monthlyReport(), type);
		}else if(type.equalsIgnoreCase("annual")){
			this.cancelledReservations = CancelledReservationsDao.getCancelledReservations(ReportsHelper.annualReport(yearFrom, yearTo), type);
		}else{
			this.cancelledReservations = CancelledReservationsDao.getCancelledReservations(ReportsHelper.quarterlyReport(), type);
		}
		
		return "success";
	}

	public List<CancelledReservation> getCancelledReservations() {
		return cancelledReservations;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	
}
