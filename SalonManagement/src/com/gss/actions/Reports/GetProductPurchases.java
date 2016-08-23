package com.gss.actions.Reports;

import java.util.List;

import com.gss.dao.Reports.ProductSalesReportRepository;
import com.gss.model.Reports.ProductPurchases;

public class GetProductPurchases {
	
	
	private String dateFrom;
	private String dateTo;
	
	private List<ProductPurchases> productOrderPurchase;
	private List<ProductPurchases> productReservationPurchase;
	private List<ProductPurchases> productWalkInPurchase;
	private List<ProductPurchases> productPurchaseTotal;
	
	public String execute(){
		this.productOrderPurchase = ProductSalesReportRepository.getProductOrderProductPurchases(dateFrom, dateTo);
		this.productReservationPurchase = ProductSalesReportRepository
	}

	public List<ProductPurchases> getProductOrderPurchase() {
		return productOrderPurchase;
	}

	public List<ProductPurchases> getProductReservationPurchase() {
		return productReservationPurchase;
	}

	public List<ProductPurchases> getProductWalkInPurchase() {
		return productWalkInPurchase;
	}

	public List<ProductPurchases> getProductPurchaseTotal() {
		return productPurchaseTotal;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	
}
