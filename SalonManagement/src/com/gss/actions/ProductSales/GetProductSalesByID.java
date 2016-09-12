package com.gss.actions.ProductSales;

import com.gss.dao.ProductSalesJDBCRepository;
import com.gss.model.ProductSales;

public class GetProductSalesByID {
	
	private ProductSales order;
	private int intOrderID;
	
	public String execute(){
		ProductSalesJDBCRepository service = new ProductSalesJDBCRepository();
		try {
			this.order = service.getProductBySalesID(intOrderID);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.order = null;
			return "failed";
		}
	}

	public ProductSales getOrder() {
		return order;
	}

	public void setIntOrderID(int intOrderID) {
		this.intOrderID = intOrderID;
	}
}
