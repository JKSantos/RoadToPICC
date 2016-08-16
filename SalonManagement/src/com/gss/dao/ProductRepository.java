package com.gss.dao;

import java.util.List;

import com.gss.model.Product;

public interface ProductRepository {
	
	public boolean createProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public List<Product> getAllProducts();
	
	public List<String> getAllCategory();
	
	public boolean deactivateProduct(int intProductID);
	
	public List<Product> getAllProductsNoImage();

	public List<Product> queryAllProduct();

}
