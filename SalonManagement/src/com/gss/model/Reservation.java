package com.gss.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {
	
	private int intReservationID;
	private Customer customer;
	private ReservationInclusion includedItems;
	private int intReservationType;
	private Date dateCreated;
	private Date datFrom;
	private Date datTo;
	private Time timFrom;
	private Time timTo;
	private int headCount;
	private List<EmployeeAssigned> employeeAssigned;
	private Invoice invoice;
	private List<Payment> paymentList;
	private List<ExtraCharge> extraChargeList;
	private String strStatus;
	private String strVenue;
	
	public Reservation(int intReservationID, Customer customer, ReservationInclusion includedItems,
			int intReservationType, Date dateCreated, Date datFrom, Date datTo, Time timFrom, Time timTo, int headCount,
			List<EmployeeAssigned> employeeAssigned, Invoice invoice, List<Payment> paymentList,
			List<ExtraCharge> extraChargeList, String strStatus, String strVenue) {
		super();
		this.intReservationID = intReservationID;
		this.customer = customer;
		this.includedItems = includedItems;
		this.intReservationType = intReservationType;
		this.dateCreated = dateCreated;
		this.datFrom = datFrom;
		this.datTo = datTo;
		this.timFrom = timFrom;
		this.timTo = timTo;
		this.headCount = headCount;
		this.employeeAssigned = employeeAssigned;
		this.invoice = invoice;
		this.paymentList = paymentList;
		this.extraChargeList = extraChargeList;
		this.strStatus = strStatus;
		this.strVenue = strVenue;
	}
	public int getIntReservationID() {
		return intReservationID;
	}
	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public ReservationInclusion getIncludedItems() {
		return includedItems;
	}
	public void setIncludedItems(ReservationInclusion includedItems) {
		this.includedItems = includedItems;
	}
	public int getIntReservationType() {
		return intReservationType;
	}
	public void setIntReservationType(int intReservationType) {
		this.intReservationType = intReservationType;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDatFrom() {
		return datFrom;
	}
	public void setDatFrom(Date datFrom) {
		this.datFrom = datFrom;
	}
	public Date getDatTo() {
		return datTo;
	}
	public void setDatTo(Date datTo) {
		this.datTo = datTo;
	}
	public Time getTimFrom() {
		return timFrom;
	}
	public void setTimFrom(Time timFrom) {
		this.timFrom = timFrom;
	}
	public Time getTimTo() {
		return timTo;
	}
	public void setTimTo(Time timTo) {
		this.timTo = timTo;
	}
	public int getHeadCount() {
		return headCount;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public List<EmployeeAssigned> getEmployeeAssigned() {
		return employeeAssigned;
	}
	public void setEmployeeAssigned(List<EmployeeAssigned> employeeAssigned) {
		this.employeeAssigned = employeeAssigned;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public List<Payment> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	public List<ExtraCharge> getExtraChargeList() {
		return extraChargeList;
	}
	public void setExtraChargeList(List<ExtraCharge> extraChargeList) {
		this.extraChargeList = extraChargeList;
	}
	public String getStrStatus() {
		return strStatus;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public String getStrVenue() {
		return strVenue;
	}
	public void setStrVenue(String strVenue) {
		this.strVenue = strVenue;
	}
	public List<Reservation> getAllReservation(){
		
	}

}
