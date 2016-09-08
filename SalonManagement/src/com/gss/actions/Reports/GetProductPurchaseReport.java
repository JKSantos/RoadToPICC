package com.gss.actions.Reports;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.ProductPurchase;
import com.gss.utilities.ReportsHelper;

public class GetProductPurchaseReport {
	
	private ProductPurchase purchase;
	
	private String type;
	
	//if annual
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		
		if(type.equalsIgnoreCase("monthly")){
			this.purchase = ProductPurchasesRepository.getProductPurchases(ReportsHelper.monthlyReport(), type);
		}else if(type.equalsIgnoreCase("annual")){
			this.purchase = ProductPurchasesRepository.getProductPurchases(ReportsHelper.annualReport(yearFrom, yearTo), type);
		}else{
			this.purchase = ProductPurchasesRepository.getProductPurchases(ReportsHelper.quarterlyReport(), type);
		}
		
		return "success";
	}
	
	public ProductPurchase getPurchase() {
		return purchase;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}
	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
}
