package com.gss.actions.Payment;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Payment;
import com.gss.model.ProductSales;

public class GetAllUnpaidTransactions {
	
	private List<ProductSales> orderList;
	
	public String execute() throws SQLException{
		
		this.orderList = Payment.getAllUnpaid();
		
		return "success";
	}

	public List<ProductSales> getOrderList() {
		return orderList;
	}
}
