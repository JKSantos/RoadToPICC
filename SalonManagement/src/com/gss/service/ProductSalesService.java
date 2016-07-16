package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.ProductSales;

public interface ProductSalesService {
	
public boolean createProductSales(ProductSales sales) throws SQLException;
	
	public boolean updateProductSales(ProductSales Sales) throws SQLException;
	
	public List<ProductSales> getAllProductSales();
	
	public boolean deactivateProductSales(int intID) throws SQLException;

}
