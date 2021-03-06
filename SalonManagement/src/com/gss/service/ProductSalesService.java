package com.gss.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.model.ProductSales;

public interface ProductSalesService {
	
	public int createProductSales(ProductSales sales) throws SQLException;
	
	public boolean updateProductSales(ProductSales Sales) throws SQLException;
	
	public List<ProductSales> getAllProductSales();
	
	public boolean deactivateProductSales(int intID) throws SQLException;
	
	public boolean acceptProductSales(int intID, Date datDeliveryDate) throws SQLException;
	
	public boolean declineProductSales(int intID) throws SQLException;
	
	public List<ProductSales> getAllProductRequest();

	public List<ProductSales> getAllProductSalesNoDetails();

}
