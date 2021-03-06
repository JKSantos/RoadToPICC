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
import com.gss.utilities.PriceFormatHelper;


public class UpdateItemAction {
	
	private File file;
	private String contentType;
	private String filename;
	private int intItemID;
	private String strItemCate;
	private String strItemName;
	private String strItemDetails;
	private String strItemCategory;
	private String price;
	private String imageName;
	private int intItemQuantity;
	private List<Integer> intServiceType;
	private int hour;
	private int minute;
	
	public String execute() throws Exception{
	
		int duration = (hour * 60) + minute;
		
		ServiceService service = new ServiceServiceImpl();
		Service update;

		ProductService prodServ = new ProductServiceImpl();
		Product prod;
		
		double dblItemPrice = PriceFormatHelper.convertToDouble(price, "Php ");
		boolean isUpdated = false;
		
		if(strItemCate.equalsIgnoreCase("service")){
			
			System.out.println(imageName);
			
			if(imageName.equalsIgnoreCase("image")){
				update = new Service(intItemID, strItemName, strItemCategory, 1, strItemDetails, dblItemPrice, null, imageName, PackageHelper.convertToSingleInt(this.intServiceType), duration);
				isUpdated = service.updateService(update);
			}
			else{
				update = new Service(intItemID, strItemName, strItemCategory, 1, strItemDetails, dblItemPrice, null, file.getAbsolutePath(), PackageHelper.convertToSingleInt(this.intServiceType), duration);
				isUpdated = service.updateService(update);
			}
			
			if(isUpdated == false)
				return "serviceFailed";
			else
				return "serviceSuccess";
		}
		else{

			if(imageName.equalsIgnoreCase("image")){
			
				prod = new Product(intItemID, strItemName, strItemCategory, strItemDetails, 0, null, dblItemPrice, imageName, 1);
				isUpdated = prodServ.updateProduct(prod);
			}
			else{
				prod = new Product(intItemID, strItemName, strItemCategory, strItemDetails, intItemQuantity, null, dblItemPrice, file.getAbsolutePath(), 1);
				isUpdated = prodServ.updateProduct(prod);
			}
			
			if(isUpdated == false)
				return "failed";
			else
				return "success";
		}	
	}
	
	public int getIntItemQuantity() {
		return intItemQuantity;
	}

	public void setIntItemQuantity(int intItemQuantity) { 
		this.intItemQuantity = intItemQuantity;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getIntItemID() {
		return intItemID;
	}

	public void setIntItemID(int intItemID) {
		this.intItemID = intItemID;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFilename(String filename) {
		this.filename = filename;
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
