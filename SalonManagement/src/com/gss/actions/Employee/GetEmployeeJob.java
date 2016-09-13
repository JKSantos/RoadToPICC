package com.gss.actions.Employee;

import java.util.List;

import com.gss.dao.Employee.EmployeeJobRepository;
import com.gss.model.EmployeeJob;

public class GetEmployeeJob {
	
	private List<EmployeeJob> walkinList;
	private List<EmployeeJob> reservationList;
	private List<EmployeeJob> deliveryList;
	private int intEmployeeID;
	
	public String execute(){
		this.walkinList = EmployeeJobRepository.getWalkInJob(intEmployeeID);
		this.reservationList = EmployeeJobRepository.getReservationJob(intEmployeeID);
		this.deliveryList = EmployeeJobRepository.getDeliveryJob(intEmployeeID);
		
		return "success";
	}

	public void setIntEmployeeID(int intEmployeeID) {
		this.intEmployeeID = intEmployeeID;
	}

	public List<EmployeeJob> getWalkinList() {
		return walkinList;
	}

	public List<EmployeeJob> getReservationList() {
		return reservationList;
	}

	public List<EmployeeJob> getDeliveryList() {
		return deliveryList;
	}
	
}
