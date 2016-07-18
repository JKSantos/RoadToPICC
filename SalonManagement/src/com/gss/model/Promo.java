package com.gss.model;

import java.util.List;

import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class Promo {
	
	private int intPromoID;
	private String strPromoName;
	private String strPromoDescription;
	private String strPromoGuidelines;
	private double dblPromoPrice;
	private int intMaxHeadCount;
	private List<ServicePackage> serviceList;
	private List<ProductPackage> productList;
	private List<PackagePackage> packageList;
	private String strPromoAvailability;
	private int intStatus;
	
	public Promo(int intPromoID, String strPromoName, String strPromoDescription, String strPromoGuidelines, double dblPromoPrice, int intMaxHeadCount, List<ServicePackage> serviceList, List<ProductPackage> productList, List<PackagePackage> packageList, String strPromoAvailability, int intStatus){
		
		this.intPromoID = intPromoID;
		this.strPromoName = strPromoName;
		this.strPromoDescription = strPromoDescription;
		this.strPromoGuidelines = strPromoGuidelines;
		this.dblPromoPrice = dblPromoPrice;
		this.intMaxHeadCount = intMaxHeadCount;
		this.serviceList = serviceList;
		this.productList = productList;
		this.packageList = packageList;
		this.setStrPromoAvailability(strPromoAvailability);
	}

	public int getIntPromoID() {
		return intPromoID;
	}

	public void setIntPromoID(int intPromoID) {
		this.intPromoID = intPromoID;
	}

	public String getStrPromoName() {
		return strPromoName;
	}

	public void setStrPromoName(String strPromoName) {
		this.strPromoName = strPromoName;
	}

	public String getStrPromoDescription() {
		return strPromoDescription;
	}

	public void setStrPromoDescription(String strPromoDescription) {
		this.strPromoDescription = strPromoDescription;
	}

	public double getDblPromoPrice() {
		return dblPromoPrice;
	}

	public void setDblPromoPrice(double dblPromoPrice) {
		this.dblPromoPrice = dblPromoPrice;
	}

	public List<ServicePackage> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<ServicePackage> serviceList) {
		this.serviceList = serviceList;
	}

	public List<ProductPackage> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductPackage> productList) {
		this.productList = productList;
	}

	public String getStrPromoAvailability() {
		return strPromoAvailability;
	}

	public void setStrPromoAvailability(String strPromoAvailability) {
		this.strPromoAvailability = strPromoAvailability;
	}

	public int getIntMaxHeadCount() {
		return intMaxHeadCount;
	}

	public void setIntMaxHeadCount(int intMaxHeadCount) {
		this.intMaxHeadCount = intMaxHeadCount;
	}

	public List<PackagePackage> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<PackagePackage> packageList) {
		this.packageList = packageList;
	}

	public int getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(int intStatus) {
		this.intStatus = intStatus;
	}

	public String getStrPromoGuidelines() {
		return strPromoGuidelines;
	}

	public void setStrPromoGuidelines(String strPromoGuidelines) {
		this.strPromoGuidelines = strPromoGuidelines;
	}
	
	public static List<Promo> getAllPromo(){
		
		PromoService service = new PromoServiceImpl();
		
		return service.getAllPromo();
	}

}
