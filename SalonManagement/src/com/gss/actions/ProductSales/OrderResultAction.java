package com.gss.actions.ProductSales;

import java.sql.SQLException;
import java.util.Date;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.dao.ProductSalesJDBCRepository;
import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;
import com.gss.utilities.NotifyCustomerViaSMS;

public class OrderResultAction {
	
	private int intOrderID;
	private Date datDeliveryDate = new Date();
	private String result = "success";
	
	public String acceptOrder() throws Exception{
		
		ProductSalesService service = new ProductSalesServiceImpl();
		boolean updated = service.acceptProductSales(this.intOrderID, this.datDeliveryDate);
		
		if(updated == false)
			result = "failed";
		else{
			ProductSales sales = new ProductSalesJDBCRepository().getProductBySalesID(this.intOrderID);
			NotifyCustomerViaSMS test = new NotifyCustomerViaSMS();
			test.sendSMS(getMessage(), sales.getStrContactNo());
		}
		return result;
		
	}
	
	public String declineOrder() throws SQLException{
		
		ProductSalesService service = new ProductSalesServiceImpl();
		boolean updated = service.declineProductSales(this.intOrderID);
		
		if(updated == false)
			result = "failed";
		
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setIntOrderID(int intOrderID) {
		this.intOrderID = intOrderID;
	}

	public void setDatDeliveryDate(Date datDeliveryDate) {
		this.datDeliveryDate = datDeliveryDate;
	}
	
	public String getMessage(){
		return "Your order request was accepted!";
	}
}
