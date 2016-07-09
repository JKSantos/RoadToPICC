package com.gss.actions;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.gss.model.Catalogue;
import com.gss.model.Employee;
import com.gss.model.Product;
import com.gss.model.Service;
import com.gss.service.CatalogueService;
import com.gss.service.CatalogueServiceImpl;
import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class GetImageAction implements ServletRequestAware{
	
	/**
	 * 
	 */
	int ImageID;
	String type = "";
	byte[] imageByte;
	private static final long serialVersionUID = 1L;
	private HttpServletRequest servletRequest;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		
		this.servletRequest = arg0;
	}
	
	public void execute() throws IOException{
		
		if(type.equalsIgnoreCase("employee")){
			
			EmployeeService service = new EmployeeServiceImpl();
			List<Employee> empList = service.getAllEmployees();
			
			for(int i = 0; i < empList.size(); i++){
				
				if(empList.get(i).getIntEmpID() == this.ImageID){
					this.imageByte = empList.get(i).getBytActualImage();
				}
			}
		}
		else if(type.equalsIgnoreCase("product")){
			ProductService service = new ProductServiceImpl();
			List<Product> prodList = service.getAllProducts();
			
			for(int i = 0; i < prodList.size(); i++){
				
				if(prodList.get(i).getIntProductID() == this.ImageID){
					this.imageByte = prodList.get(i).getProductPhoto();
				}
			}
		}
		else if(type.equalsIgnoreCase("service")){
			ServiceService service = new ServiceServiceImpl();
			List<Service> serviceList = service.getAllService();
			
			for(int i = 0; i < serviceList.size(); i++){
				
				if(serviceList.get(i).getIntServiceID() == this.ImageID){
					this.imageByte = serviceList.get(i).getProductPhoto();
				}
			}
		}
		else if(type.equalsIgnoreCase("catalogue")){
			CatalogueService service = new CatalogueServiceImpl();
			List<Catalogue> catalogueList = service.getAllCatalogue();
			
			for(int i = 0; i < catalogueList.size(); i++){
				
				if(catalogueList.get(i).getIntCatalogueID() == this.ImageID){
					this.imageByte = catalogueList.get(i).getBytCataloguePhoto();
				}
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream stream = response.getOutputStream();
		stream.write(this.imageByte);
		stream.flush();
	}

	public int getImageID() {
		return ImageID;
	}

	public void setImageID(int imageID) {
		ImageID = imageID;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}
	
	public byte[] getCustomImageInBytes() {

		EmployeeService service = new EmployeeServiceImpl();
		List<Employee> empList = service.getAllEmployees();
		
		for(int i = 0; i < empList.size(); i++){
			
			if(empList.get(i).getIntEmpID() == this.ImageID){
				this.imageByte = empList.get(i).getBytActualImage();
			}
		}
		
		return imageByte;
	}
	
	public String getCustomContentType() {
		return "image/jpeg";
	}

	public String getCustomContentDisposition() {
		return "anyname.jpg";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
