package com.gss.actions;

import java.io.File;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateServProdAction {
	
	private File file;
	private String contentType;
	private String filename;
	private String strItemCate;
	private String strItemName;
	private String strItemDetails;
	private String strItemCategory;
	private String price;
	private String imageName;
	
	public String execute() throws Exception{

		boolean isRecorded = false;
		String path = file.getAbsolutePath();
		double dblItemPrice = PriceFormatHelper.convertToDouble(price, "Php ");
		System.out.print(dblItemPrice);
		
		
		if(strItemCate.equalsIgnoreCase("Product")){
			
			Product product = new Product(1, strItemName.trim().toUpperCase(), strItemCategory, strItemDetails.trim().toUpperCase(), 0, null, dblItemPrice, path, 1);
		
			ProductService prodService = new ProductServiceImpl();
		
			isRecorded = prodService.createProduct(product);
			
			if(isRecorded == true)
				return "success"; 
			else
				return "failed";
		}
		else{
			
			Service service = new Service(1, strItemName.trim().toUpperCase(), strItemCategory, 1, strItemDetails.trim().toUpperCase(), dblItemPrice, null, path);
		
			ServiceService servService = new ServiceServiceImpl();
		
			isRecorded = servService.createService(service);
			
			if(isRecorded == true)
				return "service"; 
			else
				return "serviceF";
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


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	

}
