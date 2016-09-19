package com.gss.actions.Reports.ProductPurchase;

import java.util.List;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.ProductPurchase;
import com.gss.utilities.ReportsHelper;

public class GetProductPurchaseReport {
	
	private ProductPurchase purchase_statistical;
	private List<ProductPurchase> purchase_tabular;
	
	private String type;
	
	//if annual
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		
		if(type.equalsIgnoreCase("monthly")){
			this.purchase_statistical = ProductPurchasesRepository.getProductPurchases(ReportsHelper.monthlyReport(), type);
		}else if(type.equalsIgnoreCase("annual")){
			this.purchase_statistical = ProductPurchasesRepository.getProductPurchases(ReportsHelper.annualReport(yearFrom, yearTo), type);
		}else{
			this.purchase_statistical = ProductPurchasesRepository.getProductPurchases(ReportsHelper.quarterlyReport(), type);
		}
		
		return "success";
	}
	
	
	public ProductPurchase getPurchase_statistical() {
		return purchase_statistical;
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

	public List<ProductPurchase> getPurchase_tabular() {
		return purchase_tabular;
	}
}
