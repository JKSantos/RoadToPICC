package com.gss.testers;

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
import com.gss.utilities.ItemDecoder;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.ProductOrderConverter;
import com.gss.utilities.QuantityHelper;

public class CreateProductSales {
	
	static String strName = "NIDA SANTOS";
	static String strStreet = "189-DR. SIXTO ANTONIO AVENUE";
	static int intLocationID = 2;
	static String strContactNo = "09361144842";
	static String orderType = "1";
	static String selectedProducts = "33";
	static String productQuantity = "1";
	static String strTotalPrice = "500";
	static String status = "PENDING";
	
	public static String execute() throws Exception{

		System.out.println("Price: " + strTotalPrice);
		int type = 2;
		List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		List<Discount> discounts = new ArrayList<Discount>();
		
		Invoice invoice = Invoice.createNullInvoice(extraCharges, discounts, PriceFormatHelper.convertToDouble(strTotalPrice, "Php "), "FULL");
		
		if(orderType.equals("delivery")){
			type = 1;
		} else {
			type = 2;
		}
		
		ItemDecoder decoder = new ItemDecoder();
		List<ProductOrder> productList;
		ProductSalesService salesService = new ProductSalesServiceImpl();
		
		ProductService productService = new ProductServiceImpl();
		List<Product> productObjectList = productService.getAllProductsNoImage();
		
		String[] product = selectedProducts.split(",");
		String[] quantity = productQuantity.split(",");
		
		productList = ProductOrderConverter.convertToProductObject(product, QuantityHelper.removeEmptyQuantity(quantity), productObjectList);
		
		ProductSales sales = new ProductSales(1, DateHelper.parseDate("2016-03-04"),DateHelper.parseDate("2016-03-04"), type, strName, strStreet, intLocationID, strContactNo, productList, invoice, "REQUEST");

		if(salesService.createProductSales(sales) == true){
			status = "success";
			return status;
		}
		else{
			status = "failed";
			return status;
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.out.print(execute());
	}

}
