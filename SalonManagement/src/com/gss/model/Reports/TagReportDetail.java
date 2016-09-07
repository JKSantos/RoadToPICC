package com.gss.model.Reports;

public class TagReportDetail {
	
	private String classification; //Month Name, Quarter Name, or Year 
	private int totalDefective;
	private int totalLost;
	private int totalExpired;
	private int totalConsumed;
	
	public TagReportDetail(String classification, int totalDefective, int totalLost, int totalExpired,
			int totalConsumed) {
		super();
		this.classification = classification;
		this.totalDefective = totalDefective;
		this.totalLost = totalLost;
		this.totalExpired = totalExpired;
		this.totalConsumed = totalConsumed;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public int getTotalDefective() {
		return totalDefective;
	}
	public void setTotalDefective(int totalDefective) {
		this.totalDefective = totalDefective;
	}
	public int getTotalLost() {
		return totalLost;
	}
	public void setTotalLost(int totalLost) {
		this.totalLost = totalLost;
	}
	public int getTotalExpired() {
		return totalExpired;
	}
	public void setTotalExpired(int totalExpired) {
		this.totalExpired = totalExpired;
	}
	public int getTotalConsumed() {
		return totalConsumed;
	}
	public void setTotalConsumed(int totalConsumed) {
		this.totalConsumed = totalConsumed;
	}
}
