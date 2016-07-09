package com.gss.actions;

import java.io.File;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class CreateServProdAction {
	
	private File file;
	private String contentType;
	private String filename;
	private String strItemCate;
	private String strItemName;
	private String strItemDetails;
	private String strItemCategory;
	private Double dblItemPrice;
	private String imageName;
	
	public String execute(){

		boolean isRecorded = false;
		String path = file.getAbsolutePath();

		if(strItemCate.equalsIgnoreCase("Product")){
			
			Product product = new Product(1, strItemName, strItemCategory, strItemDetails, 0, null, dblItemPrice, path, 1);
		
			ProductService prodService = new ProductServiceImpl();
		
			isRecorded = prodService.createProduct(product);
		}
		else{
			
			Service service = new Service(1, strItemName, strItemCategory, 1, strItemDetails, dblItemPrice, null, path);
		
			ServiceService servService = new ServiceServiceImpl();
		
			isRecorded = servService.createService(service);
		}

		if(isRecorded == true){
			
			System.out.print("success");
			return "success"; 
		}
		else{
			System.out.print("failed");
			return "failed";
		}
	}
	
	public File getUpload() {
		return file;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public String getUploadContentType() {
		return contentType;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUploadFilename() {
		return filename;
	}

	public void setUploadFileName(String fileName) {
		this.filename = fileName;
	}

	public String getStrItemCate() {
		return strItemCate;
	}

	public void setStrItemCate(String strItemCate) {
		this.strItemCate = strItemCate;
	}

	public String getStrItemName() {
		return strItemName;
	}

	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName;
	}

	public String getStrItemDetails() {
		return strItemDetails;
	}

	public void setStrItemDetails(String strItemDetails) {
		this.strItemDetails = strItemDetails;
	}

	public String getStrItemCategory() {
		return strItemCategory;
	}

	public void setStrItemCategory(String strItemCategory) {
		this.strItemCategory = strItemCategory;
	}

	public Double getDblItemPrice() {
		return dblItemPrice;
	}

	public void setDblItemPrice(Double dblItemPrice) {
		this.dblItemPrice = dblItemPrice;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	

}
