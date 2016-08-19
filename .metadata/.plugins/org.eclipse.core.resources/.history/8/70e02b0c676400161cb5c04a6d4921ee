package com.gss.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.gss.dao.ProductSalesJDBCRepository;
import com.gss.dao.ProductSalesRepository;
import com.gss.model.ProductSales;

public class ProductSalesServiceImpl implements ProductSalesService {

	@Override
	public boolean createProductSales(ProductSales sales) throws SQLException {
		
		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.createProductSales(sales);
	}

	@Override
	public boolean updateProductSales(ProductSales Sales) throws SQLException {
		
		return false;
	}

	@Override
	public List<ProductSales> getAllProductSales() {
		
		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.getAllProductSales();		
	}

	@Override
	public boolean deactivateProductSales(int intID) throws SQLException {

		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.deactivateProductSales(intID);	
	}

	@Override
	public boolean acceptProductSales(int intID, Date datDeliveryDate) throws SQLException {
		
		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.acceptProductSales(intID, datDeliveryDate);	
	}

	@Override
	public boolean declineProductSales(int intID) throws SQLException {

		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.declineProductSales(intID);	
	}

	@Override
	public List<ProductSales> getAllProductRequest() {

		ProductSalesRepository repo = new ProductSalesJDBCRepository();
		
		return repo.getAllProductRequest();	
	}

}
