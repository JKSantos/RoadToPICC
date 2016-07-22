package com.gss.actions.ProductSales;

import java.sql.SQLException;

import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;

public class DeactivateOrderAction {
	
	private int intOrderID;
	private String result = "success";
	
	public String execute() throws SQLException{
		
		ProductSalesService service = new ProductSalesServiceImpl();
		boolean recorder = service.deactivateProductSales(this.intOrderID);
		
		if(recorder == false){
			result = "failed";
		}
		
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setIntOrderID(int intOrderID) {
		this.intOrderID = intOrderID;
	}

}
