package com.gss.actions.ProductSales;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductPackage;
import com.gss.model.ProductSales;
import com.gss.service.ProductSalesService;
import com.gss.service.ProductSalesServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ItemDecoder;
import com.gss.utilities.ProductOrderConverter;

public class CreateOrderAction {
	
	private String strName;
	private String strStreet;
	private int intLocationID;
	private String strContactNo;
	private int orderType;
	private String dateCreated;
	private String selectedProducts;
	private String productQuantity;
	
	public String execute() throws SQLException{
		
		List<ProductOrder> productList;
		ProductSalesService salesService = new ProductSalesServiceImpl();
		
		ProductService productService = new ProductServiceImpl();
		List<Product> productObjectList = productService.getAllProductsNoImage();
		String[] checkedProducts = selectedProducts.split(", ");
		String[] productQuantity = this.productQuantity.split(", ");
		
		ItemDecoder decoder = new ItemDecoder();
		checkedProducts = decoder.productOrderByChecked(productObjectList, checkedProducts);
		productQuantity = (decoder.getProductQuantity(checkedProducts, productQuantity));
		
		productList = ProductOrderConverter.convertToProductObject(checkedProducts, productQuantity, productObjectList);
		
		
		ProductSales sales = new ProductSales(1, DateHelper.parseDate(dateCreated),DateHelper.parseDate(dateCreated), orderType, strName, strStreet, intLocationID, strContactNo, productList, 1, "PENDING");

		if(salesService.createProductSales(sales) == true)
			return "success";
		else
			return "failed";
		
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

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
