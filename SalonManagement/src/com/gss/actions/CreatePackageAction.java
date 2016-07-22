package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.utilities.PackageHelper;
import com.gss.utilities.ItemDecoder;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class CreatePackageAction {
	
	ItemDecoder decode = new ItemDecoder();
	private String strPackageName;
	private String strPackageDesc;
	private List<Integer> intPackageType = new ArrayList<Integer>();
	private double dblPackagePrice;
	private String createPackServType = "";
	private String createPackProdType = "";
	private String createPackServQty = "";
	private String createPackProdQty = "";
	
	public String execute(){		
		
		ServiceService serviceService = new ServiceServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PackageService packageService = new PackageServiceImpl();
		
		List<Service> service = serviceService.getAllService();
		List<Product> product = productService.getAllProducts();
		
		List<ServicePackage> serviceList = new ArrayList<ServicePackage>();
		List<ProductPackage> productList = new ArrayList<ProductPackage>();
		
		String[] selectedServices = this.createPackServType.split(", ");
		String[] selectedProducts = this.createPackProdType.split(", ");
		String[] serviceCount = this.createPackServQty.split(", ");
		String[] productCount = this.createPackProdQty.split(", ");
		
		if(!createPackServType.equals("")){
			
			String[] serviceOrder = decode.serviceOrderByChecked(service, selectedServices);
			String[] serviceQuantity = decode.getServiceQuantity(serviceOrder, serviceCount);
			
			for(int i = 0; i < selectedServices.length; i++){
				for(int j = 0; j < service.size(); j++){
					
					Service sample = service.get(j);
					if(Integer.parseInt(selectedServices[i]) == sample.getIntServiceID()){
						serviceList.add(new ServicePackage(1, 1, sample, Integer.parseInt(serviceCount[i]), 1));
					}
				}
			}
		}
		
		if(!createPackProdType.equals("")){
			
			String[] productOrder = decode.productOrderByChecked(product, selectedProducts);
			String[] productQuantity = decode.getProductQuantity(productOrder, productCount);
			
			for(int i = 0; i < selectedProducts.length; i++){
				for(int j = 0; j < product.size(); j++){
					
					Product sample = product.get(j);
					if(Integer.parseInt(selectedProducts[i]) == sample.getIntProductID()){
						productList.add(new ProductPackage(1, 1, sample, Integer.parseInt(productCount[i]), 1));
					}
				}
			}
		}

		
		Package packagee = new Package(1, strPackageName.toUpperCase().trim(), strPackageDesc.toUpperCase().trim(), PackageHelper.convertToSingleInt(intPackageType), 1, "NON-EXPIRY", dblPackagePrice, serviceList, productList, 1);
		
		
		if(packageService.createPackage(packagee)){
			System.out.println("success");
			return "success";
		}else{
			System.out.println("failed");
			return "failed";
		}	
	}

	public String getStrPackageName() {
		return strPackageName;
	}

	public void setStrPackageName(String strPackageName) {
		this.strPackageName = strPackageName;
	}

	public String getStrPackageDesc() {
		return strPackageDesc;
	}

	public void setStrPackageDesc(String strPackageDesc) {
		this.strPackageDesc = strPackageDesc;
	}

	public List<Integer> getIntPackageType() {
		return intPackageType;
	}

	public void setIntPackageType(List<Integer> intPackageType) {
		this.intPackageType = intPackageType;
	}

	public double getDblPackagePrice() {
		return dblPackagePrice;
	}

	public void setDblPackagePrice(double dblPackagePrice) {
		this.dblPackagePrice = dblPackagePrice;
	}

	public String getCreatePackServType() {
		return createPackServType;
	}

	public void setCreatePackServType(String createPackServType) {
		this.createPackServType = createPackServType;
	}

	public String getCreatePackProdType() {
		return createPackProdType;
	}

	public void setCreatePackProdType(String createPackProdType) {
		this.createPackProdType = createPackProdType;
	}

	public String getCreatePackServQty() {
		return createPackServQty;
	}

	public void setCreatePackServQty(String createPackServQty) {
		this.createPackServQty = createPackServQty;
	}

	public String getCreatePackProdQty() {
		return createPackProdQty;
	}

	public void setCreatePackProdQty(String createPackProdQty) {
		this.createPackProdQty = createPackProdQty;
	}
}
