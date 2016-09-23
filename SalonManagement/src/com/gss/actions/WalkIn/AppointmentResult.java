package com.gss.actions.WalkIn;

import com.gss.dao.WalkInJDBCRepository;

public class AppointmentResult {
	
	private int intAppointmentID;
	private String status;
	
	public String execute(){
		
		boolean result = WalkInJDBCRepository.updateWalkInStatus(intAppointmentID, status);
		
		if(result == true)
			return "success";
		else
			return "failed";
		
	}

	public void setIntAppointmentID(int intAppointmentID) {
		this.intAppointmentID = intAppointmentID;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
