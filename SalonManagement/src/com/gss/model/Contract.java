
package com.gss.model;

import java.util.Date;

import com.gss.utilities.DateHelper;

public class Contract {
	
	public String date;
	public String managerName;
	public String providerName;
	public String providerAddess;
	public String receiverName;
	public String receiverAddress;
	public Reservation reservation;
	
	public Contract(String managerName, String providerName, String providerAddess, String receiverName,
			String receiverAddress, Reservation reservation) {
		super();
		this.managerName = managerName;
		this.providerName = providerName;
		this.providerAddess = providerAddess;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.reservation = reservation;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderAddess() {
		return providerAddess;
	}
	public void setProviderAddess(String providerAddess) {
		this.providerAddess = providerAddess;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public String getDate() {
		return date;
	}
	public void setDate() {
		this.date = DateHelper.stringDate();
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
}
