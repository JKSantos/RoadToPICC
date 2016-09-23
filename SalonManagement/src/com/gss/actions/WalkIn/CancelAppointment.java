package com.gss.actions.WalkIn;

import com.gss.dao.WalkInJDBCRepository;

public class CancelAppointment {
	
	private int appointmentID;
	
	public String execute(){
	
		WalkInJDBCRepository repo = new WalkInJDBCRepository();
		boolean result = repo.cancelWalkIn(appointmentID);
		
		if(result == true)
			return "success";
		else
			return "failed";
		
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
}
