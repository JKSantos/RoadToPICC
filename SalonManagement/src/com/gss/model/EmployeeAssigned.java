package com.gss.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAssigned {
	
	private int intAssignmentDetailID; 	//id for the table tblAssignmentDetail
	private int intAssignmentID;		//id for foreign key, tblAssignment
	private Employee employeeAssigned;
	private int intAssignmentStatus;
	
	public EmployeeAssigned(int intAssignmentDetailID, int intAssignmentID, Employee employeeAssigned, int intAssignmentStatus){
		this.setIntAssignmentDetailID(intAssignmentDetailID);
		this.setIntAssignmentID(intAssignmentID);
		this.setEmployeeAssigned(employeeAssigned);
		this.setIntAssignmentStatus(intAssignmentStatus);
	}

	public int getIntAssignmentDetailID() {
		return intAssignmentDetailID;
	}

	public void setIntAssignmentDetailID(int intAssignmentDetailID) {
		this.intAssignmentDetailID = intAssignmentDetailID;
	}

	public int getIntAssignmentID() {
		return intAssignmentID;
	}

	public void setIntAssignmentID(int intAssignmentID) {
		this.intAssignmentID = intAssignmentID;
	}

	public Employee getEmployeeAssigned() {
		return employeeAssigned;
	}

	public void setEmployeeAssigned(Employee employeeAssigned) {
		this.employeeAssigned = employeeAssigned;
	}

	public int getIntAssignmentStatus() {
		return intAssignmentStatus;
	}

	public void setIntAssignmentStatus(int intAssignmentStatus) {
		this.intAssignmentStatus = intAssignmentStatus;
	}
	
	public static List<EmployeeAssigned> getAllAssignedEmployee(int intReservationID){
		
		List<EmployeeAssigned> employeeList = new ArrayList<EmployeeAssigned>();
		
		return employeeList;
	}
}
