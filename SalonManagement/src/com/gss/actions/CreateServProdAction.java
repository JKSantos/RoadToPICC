package com.gss.actions;

import java.io.File;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.PackageHelper;
import com.gss.utilities.PriceFormat;


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
	private List<Integer> intServiceType;
	private int hour;
	private int minute;
	
	public String execute() throws Exception{
		
		int duration = (hour * 60) + minute;

		boolean isRecorded = false;
		String path = file.getAbsolutePath();
		double dblItemPrice = PriceFormat.convertToDouble((price + "0"), "Php ");
		
		
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
			
			System.out.println("Successfully Created");
			
			Service service = new Service(1, strItemName.trim().toUpperCase(), strItemCategory, 1, strItemDetails.trim().toUpperCase(), dblItemPrice, null, path, PackageHelper.convertToSingleInt(this.intServiceType), duration);
		
			ServiceService servService = new ServiceServiceImpl();
		
			isRecorded = servService.createService(service);
			
			System.out.print(isRecorded);
			
			if(isRecorded == true)
				return "service"; 
			else
				return "serviceF";
		}
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String fileName) {
		this.filename = fileName;
	}

	public void setStrItemCate(String strItemCate) {
		this.strItemCate = strItemCate;
	}

	public void setStrItemName(String strItemName) {
		this.strItemName = strItemName.trim().toUpperCase();
	}

	public void setStrItemDetails(String strItemDetails) {
		this.strItemDetails = strItemDetails.trim().toUpperCase();
	}

	public void setStrItemCategory(String strItemCategory) {
		this.strItemCategory = strItemCategory;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public void setIntServiceType(List<Integer> intServiceType) {
		this.intServiceType = intServiceType;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

}
