package com.gss.actions;

public class CreateServiceAction {
	
	private String strServiceName;
	private String strServiceDesc;
	private double dblServicePrice;
	private String strServiceCategory;
	private byte[] bytServicePhoto;
	
	public String execute(){
		
		return "success";
	}
	
	public String getStrServiceName() {
		return strServiceName;
	}
	public void setStrServiceName(String strServiceName) {
		this.strServiceName = strServiceName;
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
	public String getStrServiceCategory() {
		return strServiceCategory;
	}
	public void setStrServiceCategory(String strServiceCategory) {
		this.strServiceCategory = strServiceCategory;
	}
	public byte[] getBytServicePhoto() {
		return bytServicePhoto;
	}
	public void setBytServicePhoto(byte[] bytServicePhoto) {
		this.bytServicePhoto = bytServicePhoto;
	}

}
