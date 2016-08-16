package com.gss.model;

import java.util.List;

import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;

public class Product {
	
	private int intProductID;
	private String strProductName;
	private String strProductCategory;
	private String strProductDesc;
	private int intProductQuantity;
	private byte[] productPhoto;
	private double dblProductPrice;
	private String strPhotoPath;
	private int intProductStatus;
	
	public Product(int intProductID, String strProductName, String strProductCategory, String strProductDesc, int intProductQuantity, byte[] productPhoto, double dblProductPrice, String strPhotoPath, int intProductStatus){
		
		this.setIntProductID(intProductID);
		this.strProductName = strProductName;
		this.strProductCategory = strProductCategory;
		this.strProductDesc = strProductDesc;
		this.intProductQuantity = intProductQuantity;
		this.productPhoto = productPhoto;
		this.dblProductPrice = dblProductPrice;
		this.strPhotoPath = strPhotoPath;
		this.setIntProductStatus(intProductStatus);
	}

	public int getIntProductID() {
		return intProductID;
	}
	
	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}

	public String getStrProductName() {
		return strProductName;
	}

	public void setStrProductName(String strProductName) {
		this.strProductName = strProductName;
	}

	public String getStrProductCategory() {
		return strProductCategory;
	}

	public void setStrProductCategory(String strProductCategory) {
		this.strProductCategory = strProductCategory;
	}

	public String getStrProductDesc() {
		return strProductDesc;
	}

	public void setStrProductDesc(String strProductDesc) {
		this.strProductDesc = strProductDesc;
	}

	public int getIntProductQuantity() {
		return intProductQuantity;
	}

	public void setIntProductQuantity(int intProductQuantity) {
		this.intProductQuantity = intProductQuantity;
	}

	public byte[] getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(byte[] productPhoto) {
		this.productPhoto = productPhoto;
	}

	public double getDblProductPrice() {
		return dblProductPrice;
	}

	public void setDblProductPrice(double dblProductPrice) {
		this.dblProductPrice = dblProductPrice;
	}

	public String getStrPhotoPath() {
		return strPhotoPath;
	}

	public void setStrPhotoPath(String strPhotoPath) {
		this.strPhotoPath = strPhotoPath;
	}

	public int getIntProductStatus() {
		return intProductStatus;
	}

	public void setIntProductStatus(int intProductStatus) {
		this.intProductStatus = intProductStatus;
	}
	
	public static List<Product> getAllProduct(){
		ProductService service = new ProductServiceImpl();
		
		return service.getAllProductsNoImage();
	}
	
	public static Product createNullProduct(int intProductID){
		return new Product(35, "", "", "", 1, null, 1, "", 1);
	}

	public static List<Product> queryAllProduct() {
		ProductService service = new ProductServiceImpl();
		
		return service.queryAllProduct();
	}
}
