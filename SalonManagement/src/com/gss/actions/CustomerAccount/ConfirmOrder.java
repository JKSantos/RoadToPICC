package com.gss.actions.CustomerAccount;

import com.gss.dao.ProductSalesJDBCRepository;

public class ConfirmOrder {
	
	private int orderID;
	
	public String execute(){
		
		return ProductSalesJDBCRepository.confirmOrder(orderID);
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
}
