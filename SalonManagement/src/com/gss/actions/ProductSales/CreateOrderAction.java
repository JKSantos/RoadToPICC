package com.gss.actions.ProductSales;

import java.sql.SQLException;

import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ItemDecoder;
import com.gss.utilities.ProductOrderConverter;
import com.gss.utilities.QuantityHelper;

public class CreateOrderAction{
	
	private String strName;
	private String strStreet;
	private int intLocationID;
	private String strContactNo;
	private String orderType;
	private String selectedProducts;
	private String productQuantity;
	private String status;
	
	public String execute() throws SQLException{

		int type = 2;
		
		if(orderType.equals("delivery")){
			type = 1;
		}
		
		System.out.print(this.intLocationID);
		
		ItemDecoder decoder = new ItemDecoder();
		List<ProductOrder> productList;
		ProductSalesService salesService = new ProductSalesServiceImpl();
		
		ProductService productService = new ProductServiceImpl();
		List<Product> productObjectList = productService.getAllProductsNoImage();
		
		String[] product = this.selectedProducts.split(",");
		String[] quantity = this.productQuantity.split(",");
		
		productList = ProductOrderConverter.convertToProductObject(product, QuantityHelper.removeEmptyQuantity(quantity), productObjectList);
		
		ProductSales sales = new ProductSales(1, DateHelper.parseDate("2016-03-04"),DateHelper.parseDate("2016-03-04"), type, strName, strStreet, intLocationID, strContactNo, productList, 1, "REQUEST");

		if(salesService.createProductSales(sales) == true){
			this.status = "success";
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
	
}
