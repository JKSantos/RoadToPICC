package com.gss.actions.Reports.ProductPurchase;

import java.util.List;

import com.gss.dao.Reports.ProductSalesReportRepository;
import com.gss.model.Reports.ProductPurchaseTotal;
import com.gss.model.Reports.ProductPurchases;

public class GetProductPurchases {
	
	
	private String dateFrom;
	private String dateTo;
	
	private List<ProductPurchases> productOrderPurchase;
	private List<ProductPurchases> productReservationPurchase;
	private List<ProductPurchases> productWalkInPurchase;
	private List<ProductPurchaseTotal> productPurchaseTotal;
	
	public String execute(){
		this.productOrderPurchase = ProductSalesReportRepository.getProductOrderProductPurchases(dateFrom, dateTo);
		this.productReservationPurchase = ProductSalesReportRepository.getReservationProductPurchases(dateFrom, dateTo);
		this.productWalkInPurchase = ProductSalesReportRepository.getWalkinProductPurchases(dateFrom, dateTo);
		this.productPurchaseTotal = ProductSalesReportRepository.getProductPurchasesTotal(dateFrom, dateTo);
		
		return "success";
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

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}


	public List<ProductPurchaseTotal> getProductPurchaseTotal() {
		return productPurchaseTotal;
	}
	

}
