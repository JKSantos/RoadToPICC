package com.gss.actions.Reports.ProductPurchase;

import java.util.List;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.ProductPurchases;
import com.gss.utilities.ReportDate;

public class GetProductPurchasesTabular {
	
	private List<ProductPurchases> purchases;
	private String dateFrom;
	private String dateTo;
	
	public String execute(){
		
		ReportDate date = new ReportDate(dateFrom, dateTo);
		
		this.purchases = ProductPurchasesRepository.getProductPurchases(date);
		
		return "success";
	}

	public List<ProductPurchases> getPurchases() {
		return purchases;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom + " 00:00:00";
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo + " 23:59:59";
	}
}
