package com.gss.actions.ProductSales;

import java.util.List;

import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;

public class GetAllOrderAction {
	
	private List<ProductSales> orderList;
	private String status;
	
	public String execute(){
		
		ProductSalesService service = new ProductSalesServiceImpl();
		this.orderList = service.getAllProductSales();
		
		status = "success";
		return status;
	}

	public List<ProductSales> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ProductSales> orderList) {
		this.orderList = orderList;
	}
}
