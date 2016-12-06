package com.gss.actions.ProductSales;

import java.sql.SQLException;
import java.util.Date;

import com.gss.dao.PaymentJDBCRepositoryImpl;
import com.gss.dao.ProductSalesJDBCRepository;
import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.NotifyCustomerViaSMS;

public class OrderResultAction {
	
	private int intOrderID;
	private int intEmpID;
	private Date datDeliveryDate = new Date();
	private String time;
	private String result = "success";
	private String reason = "";
	
	public String acceptOrder() throws Exception{
		
		ProductSalesService service = new ProductSalesServiceImpl();
		boolean updated = service.acceptProductSales(this.intOrderID, this.datDeliveryDate);
		
		if(updated == false)
			result = "failed";
		else{
			ProductSales sales = new ProductSalesJDBCRepository().getProductBySalesID(this.intOrderID);
			NotifyCustomerViaSMS test = new NotifyCustomerViaSMS();
			test.sendSMS(getMessage(), sales.getStrContactNo());
			
			ProductSalesJDBCRepository.assignEmployee(intEmpID, intOrderID, time);
		}
		return result;
		
	}
	
	public String declineOrder() throws Exception{
		
		ProductSalesService service = new ProductSalesServiceImpl();
		ProductSales sales = new ProductSalesJDBCRepository().getProductBySalesID(this.intOrderID);
		
		NotifyCustomerViaSMS test = new NotifyCustomerViaSMS();
		test.sendSMS(getDeclineMessage(), sales.getStrContactNo());
		boolean updated = service.declineProductSales(this.intOrderID);
		
		
		System.out.println(this.intOrderID);
		System.out.println(">>>" + sales.getIntSalesID());
		
		
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
		return "Your order request was accepted and will be delivered on "+DateHelper.stringDate(this.datDeliveryDate);
	}
	
	public String getDeclineMessage() {
		return "Sorry but your order was rejected. Reason for rejection: " + this.reason;
	}

	public void setIntEmpID(int intEmpID) {
		this.intEmpID = intEmpID;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
