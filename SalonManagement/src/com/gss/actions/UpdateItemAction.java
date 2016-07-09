package com.gss.actions;

import java.io.File;

import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class UpdateItemAction {
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private int intItemID;
	private String strItemCate;
	private String strItemName;
	private String strItemDetails;
	private String strItemCategory;
	private Double dblItemPrice;
	private String imageName;
	private int intItemQuantity;
	
	public String execute(){
	
		ServiceService service = new ServiceServiceImpl();
		Service update;

		ProductService prodServ = new ProductServiceImpl();
		Product prod;

		boolean isUpdated = false;
		//System.out.println(fileUpload.getAbsolutePath());
		
		if(strItemCate.equalsIgnoreCase("service")){
			
			if(imageName.equalsIgnoreCase("image")){
				update = new Service(intItemID, strItemName, strItemCategory, 1, strItemDetails, dblItemPrice, null, imageName);
				isUpdated = service.updateService(update);
			}
			else{
				update = new Service(intItemID, strItemName, strItemCategory, 1, strItemDetails, dblItemPrice, null, fileUpload.getAbsolutePath());
				isUpdated = service.updateService(update);
			}
		}
		else{

			if(imageName.equalsIgnoreCase("image")){
				
				System.out.println(intItemID + " " + dblItemPrice);
				prod = new Product(intItemID, strItemName, strItemCategory, strItemDetails, intItemQuantity, null, dblItemPrice, imageName, 1);
				isUpdated = prodServ.updateProduct(prod);
			}
			else{
				prod = new Product(intItemID, strItemName, strItemCategory, strItemDetails, intItemQuantity, null, dblItemPrice, fileUpload.getAbsolutePath(), 1);
				isUpdated = prodServ.updateProduct(prod);
			}
			
		}
		
		if(isUpdated == true){
			return "success";
		}
		else{
			return "failed";
		}
		
	}
	
	public int getIntItemQuantity() {
		return intItemQuantity;
	}

	public void setIntItemQuantity(int intItemQuantity) {
		this.intItemQuantity = intItemQuantity;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
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

	public int getIntItemID() {
		return intItemID;
	}

	public void setIntItemID(int intItemID) {
		this.intItemID = intItemID;
	}
	
	

}
