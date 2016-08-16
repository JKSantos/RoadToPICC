package com.gss.service;

import java.util.List;

import com.gss.dao.ProductJDBCRepository;
import com.gss.dao.ProductRepository;
import com.gss.model.Product;

public class ProductServiceImpl implements ProductService{
		
	public boolean createProduct(Product product) {
		
		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.createProduct(product);
	}


	public boolean updateProduct(Product product) {
	
		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.updateProduct(product);
	}


	public List<Product> getAllProducts() {
	
		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.getAllProducts();
	}


	@Override
	public List<String> getAllCategory() {

		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.getAllCategory();
	}


	@Override
	public boolean deactivateProduct(int intProductID) {

		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.deactivateProduct(intProductID);
	}


	@Override
	public List<Product> getAllProductsNoImage() {
		
		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.getAllProductsNoImage();
	}


	@Override
	public List<Product> queryAllProduct() {
		
		ProductRepository repo = new ProductJDBCRepository();
		
		return repo.queryAllProduct();
	}

}
