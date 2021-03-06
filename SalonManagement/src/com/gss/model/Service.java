package com.gss.model;

import java.util.List;

import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class Service {
	
	private int intServiceID;
	private String strServiceName;
	private String strServiceCategory;
	private int serviceType;
	private int intServiceStatus;
	private String strServiceDesc;
	private byte[] productPhoto;
	private double dblServicePrice;
	private String strPhotoPath;
	private String stringPrice;
	private int intDuration;
	
	public Service(int intServiceID, String strServiceName, String strServiceCategory, int strServiceStatus, String strServiceDesc, double dblServicePrice, byte[] productPhoto, String strPhotoPath, int serviceType, int intDuration){
		
		this.intServiceID = intServiceID;
		this.strServiceName = strServiceName;
		this.strServiceCategory = strServiceCategory;
		this.intServiceStatus = strServiceStatus;
		this.strServiceDesc = strServiceDesc;
		this.setProductPhoto(productPhoto);
		this.strPhotoPath = strPhotoPath;
		this.dblServicePrice = dblServicePrice;
		this.serviceType = serviceType;
		this.intDuration = intDuration;
	}

	public int getIntServiceID() {
		return intServiceID;
	}

	public void setIntServiceID(int intServiceID) {
		this.intServiceID = intServiceID;
	}

	public String getStrServiceName() {
		return strServiceName;
	}

	public void setStrServiceName(String strServiceName) {
		this.strServiceName = strServiceName;
	}

	public String getStrServiceCategory() {
		return strServiceCategory;
	}

	public void setStrServiceCategory(String strServiceCategory) {
		this.strServiceCategory = strServiceCategory;
	}

	public String getStrServiceDesc() {
		return strServiceDesc;
	}

	public void setStrServiceDesc(String strServiceDesc) {
		this.strServiceDesc = strServiceDesc;
	}

	public double getDblServicePrice() {
		return dblServicePrice;
	}

	public void setDblServicePrice(double dblServicePrice) {
		this.dblServicePrice = dblServicePrice;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public int getIntServiceStatus() {
		return intServiceStatus;
	}

	public void setIntServiceStatus(int strServiceStatus) {
		this.intServiceStatus = strServiceStatus;
	}

	public String getStrPhotoPath() {
		return strPhotoPath;
	}

	public void setStrPhotoPath(String strPhotoPath) {
		this.strPhotoPath = strPhotoPath;
	}
	
	public static List<Service> getAllService(){
		
		ServiceService service = new ServiceServiceImpl();
		
		return service.getAllServiceNoImage();
	}
	
	public static Service createNullService(int intServiceID){
		
		return new Service(intServiceID, "", "", intServiceID, "", 0, null, "", 1, 0);
	}

	public static List<Service> queryAllService() {
		
		ServiceService service = new ServiceServiceImpl();
		
		return service.queryAllService();
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getStringPrice() {
		return stringPrice;
	}

	public void setStringPrice(String stringPrice) {
		this.stringPrice = stringPrice;
	}

	public int getIntDuration() {
		return intDuration;
	}

	public void setIntDuration(int intDuration) {
		this.intDuration = intDuration;
	}
	
	

}
