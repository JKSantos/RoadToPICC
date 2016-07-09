package com.gss.model;

public class Catalogue {

	private int intCatalogueID;
	private String strCatalogueName;
	private String strCatalogueType;
	private double dblCataloguePrice;
	private byte[] bytCataloguePhoto;
	private String strCataloguePath;
	private int intCatalogueStatus;
	
	public Catalogue(int intCatalogueID, String strCatalogueName, String strCatalogueType, double dblCataloguePrice, byte[] bytCataloguePhoto, String strCataloguePath, int intCatalogueStatus){
		
		this.setIntCatalogueID(intCatalogueID);
		this.setStrCatalogueName(strCatalogueName);
		this.setStrCatalogueType(strCatalogueType);
		this.setDblCataloguePrice(dblCataloguePrice);
		this.setBytCataloguePhoto(bytCataloguePhoto);
		this.setStrCataloguePath(strCataloguePath);
		this.setIntCatalogueStatus(intCatalogueStatus);
	}

	public int getIntCatalogueID() {
		return intCatalogueID;
	}

	public void setIntCatalogueID(int intCatalogueID) {
		this.intCatalogueID = intCatalogueID;
	}

	public String getStrCatalogueName() {
		return strCatalogueName;
	}

	public void setStrCatalogueName(String strCatalogueName) {
		this.strCatalogueName = strCatalogueName;
	}

	public String getStrCatalogueType() {
		return strCatalogueType;
	}

	public void setStrCatalogueType(String strCatalogueType) {
		this.strCatalogueType = strCatalogueType;
	}

	public double getDblCataloguePrice() {
		return dblCataloguePrice;
	}

	public void setDblCataloguePrice(double dblCataloguePrice) {
		this.dblCataloguePrice = dblCataloguePrice;
	}

	public byte[] getBytCataloguePhoto() {
		return bytCataloguePhoto;
	}

	public void setBytCataloguePhoto(byte[] bytCataloguePhoto) {
		this.bytCataloguePhoto = bytCataloguePhoto;
	}

	public String getStrCataloguePath() {
		return strCataloguePath;
	}

	public void setStrCataloguePath(String strCataloguePath) {
		this.strCataloguePath = strCataloguePath;
	}

	public int getIntCatalogueStatus() {
		return intCatalogueStatus;
	}

	public void setIntCatalogueStatus(int intCatalogueStatus) {
		this.intCatalogueStatus = intCatalogueStatus;
	}
}
