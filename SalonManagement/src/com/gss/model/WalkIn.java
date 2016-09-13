package com.gss.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import com.gss.service.WalkInService;
import com.gss.service.WalkInServiceImpl;

public class WalkIn {
	
	private int intWalkInID;
	private String strWalkInType;
	private String strName;
	private String strContactNo;
	private Date datWalkIn;
	private java.sql.Date appointmentDate;
	private Time appointmentTime;
	private List<ServiceWalkIn> services;
	private List<ProductWalkIn> products;
	private List<PackageWalkIn> packages;
	private List<PromoWalkIn> promo;
	private Invoice invoice;
	private Payment payment;
	private String strWalkInStatus;
	private String strPaymentStatus;

	public WalkIn(int intWalkInID, String strWalkInType, String strName, String strContactNo, Date datWalkIn,
			List<ServiceWalkIn> services, List<ProductWalkIn> products, List<PackageWalkIn> packages,
			List<PromoWalkIn> promo, Invoice invoice, Payment payment, String strWalkInStatus,
			String strPaymentStatus) {
		super();
		this.intWalkInID = intWalkInID;
		this.strWalkInType = strWalkInType;
		this.strName = strName;
		this.strContactNo = strContactNo;
		this.datWalkIn = datWalkIn;
		this.services = services;
		this.products = products;
		this.packages = packages;
		this.promo = promo;
		this.invoice = invoice;
		this.payment = payment;
		this.strWalkInStatus = strWalkInStatus;
		this.strPaymentStatus = strPaymentStatus;
	}
	
	public int getIntWalkInID() {
		return intWalkInID;
	}
	public void setIntWalkInID(int intWalkInID) {
		this.intWalkInID = intWalkInID;
	}
	public String getStrWalkInType() {
		return strWalkInType;
	}
	public void setStrWalkInType(String strWalkInType) {
		this.strWalkInType = strWalkInType;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrContactNo() {
		return strContactNo;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	public Date getDatWalkIn() {
		return datWalkIn;
	}
	public void setDatWalkIn(Date datWalkIn) {
		this.datWalkIn = datWalkIn;
	}
	public List<ServiceWalkIn> getServices() {
		return services;
	}
	public void setServices(List<ServiceWalkIn> services) {
		this.services = services;
	}
	public List<ProductWalkIn> getProducts() {
		return products;
	}
	public void setProducts(List<ProductWalkIn> products) {
		this.products = products;
	}
	public List<PackageWalkIn> getPackages() {
		return packages;
	}
	public void setPackages(List<PackageWalkIn> packages) {
		this.packages = packages;
	}
	public List<PromoWalkIn> getPromo() {
		return promo;
	}
	public void setPromo(List<PromoWalkIn> promo) {
		this.promo = promo;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getStrWalkInStatus() {
		return strWalkInStatus;
	}
	public void setStrWalkInStatus(String strWalkInStatus) {
		this.strWalkInStatus = strWalkInStatus;
	}
	public String getStrPaymentStatus() {
		return strPaymentStatus;
	}
	public void setStrPaymentStatus(String strPaymentStatus) {
		this.strPaymentStatus = strPaymentStatus;
	}
	public static List<WalkIn> getAllWalkInNoDetails(){
		WalkInService service = new WalkInServiceImpl();
		
		return service.getAllWalkInNoDetails();
	}
	public static List<WalkIn> getAllWalkIn(){
		WalkInService service = new WalkInServiceImpl();
		
		return service.getAllWalkIn();
	}

	public java.sql.Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(java.sql.Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Time getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
}
