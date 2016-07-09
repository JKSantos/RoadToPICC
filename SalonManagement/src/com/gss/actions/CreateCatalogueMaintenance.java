package com.gss.actions;

import java.io.File;
import java.util.List;

import com.gss.model.Catalogue;
import com.gss.model.Service;
import com.gss.service.CatalogueService;
import com.gss.service.CatalogueServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class CreateCatalogueMaintenance {
	
	private String strCatalogueCategoryID;
	private String strCatalogueName;
	private String strCataloguePath;
	private File file;
	private String contentType;
	private String filename;
	
	public String execute(){
		
		String path = file.getAbsolutePath();
		ServiceService ser = new ServiceServiceImpl();
		List<Service> serviceList = ser.getAllService();
		
		CatalogueService service = new CatalogueServiceImpl();
		Catalogue catalogue = new Catalogue(1, strCatalogueName,strCatalogueCategoryID, priceHelper(serviceList, strCatalogueCategoryID), null, path, 1);
		
		if(service.createCatalogue(catalogue) == true){
			return "success";
		}
		else{
			return "failed";
		}
	}
	
	public void setUpload(File file){
		this.file = file;
	}
	
	public File getUpload(){
		return this.file;
	}
	
	public void setUploadContentType(String contentType){
		this.contentType = contentType;
	}
	
	public String getUploadContentType(){
		return this.contentType;
	}
	
	public void setUploadFilename(String filename){
		this.filename = filename;
	}
	
	public String getUploadFilename(){
		return this.filename;
	}

	public String getStrCatalogueCategoryID() {
		return strCatalogueCategoryID;
	}

	public void setStrCatalogueCategoryID(String strCatalogueCategoryID) {
		this.strCatalogueCategoryID = strCatalogueCategoryID;
	}

	public String getStrCatalogueName() {
		return strCatalogueName;
	}

	public void setStrCatalogueName(String strCatalogueName) {
		this.strCatalogueName = strCatalogueName;
	}

	public String getStrCataloguePath() {
		return strCataloguePath;
	}

	public void setStrCataloguePath(String strCataloguePath) {
		this.strCataloguePath = strCataloguePath;
	}
	
	public double priceHelper(List<Service> serviceList, String serviceName){
		
		for(int i = 0; i < serviceList.size(); i++){
			Service service = serviceList.get(0);
			
			if(service.getStrServiceName().equals(serviceName)){
				return service.getDblServicePrice();
			}
		}
		
		return 0;
	}

}
