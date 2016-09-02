package com.gss.actions.ProductSales;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.ProductOrderConverter;
import com.gss.utilities.QuantityHelper;

public class CreateOrderAction{
	
	private String strName;
	private String strStreet = "";
	private int intLocationID = 2;
	private String strContactNo;
	private String orderType;
	private String selectedProducts;
	private String productQuantity;
	private String strTotalPrice;
	private String status;
	private int intCreatedID;
	
	public String execute() throws Exception{

		System.out.println("Price: " + this.strTotalPrice);
		int type = 2;
		List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		List<Discount> discounts = new ArrayList<Discount>();
		
		Invoice invoice = Invoice.createNullInvoice(extraCharges, discounts, PriceFormatHelper.convertToDouble(this.strTotalPrice, "Php "), "FULL");
		
		if(orderType.equals("delivery")){
			type = 1;
		} else {
			type = 2;
		}
		
		List<ProductOrder> productList;
		ProductSalesService salesService = new ProductSalesServiceImpl();
		
		ProductService productService = new ProductServiceImpl();
		List<Product> productObjectList = productService.getAllProductsNoImage();
		
		String[] product = this.selectedProducts.split(",");
		String[] quantity = this.productQuantity.split(",");
		
		productList = ProductOrderConverter.convertToProductObject(product, QuantityHelper.removeEmptyQuantity(quantity), productObjectList);
		
		ProductSales sales = new ProductSales(1, DateHelper.parseDate("2016-03-04"),DateHelper.parseDate("2016-03-04"), type, strName, strStreet, intLocationID, strContactNo, productList, invoice, "REQUEST");

		int result = salesService.createProductSales(sales);
		
		if(result != 0){
			this.status = "success";
			this.intCreatedID = result; 
			return this.status;
		}
		else{
			this.status = "failed";
			return this.status;
		}
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public void setStrStreet(String strStreet) {
		this.strStreet = strStreet;
	}

	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}

	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setSelectedProducts(String selectedProducts) {
		this.selectedProducts = selectedProducts;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStrTotalPrice() {
		return strTotalPrice;
	}

	public void setStrTotalPrice(String strTotalPrice) {
		this.strTotalPrice = strTotalPrice;
	}
	
	public int getIntCreatedID(){
		return this.intCreatedID;
	}
	
}