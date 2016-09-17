package com.gss.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Promo;
import com.gss.model.Requirement;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.DateHelper;
import com.gss.utilities.ItemDecoder;
import com.gss.utilities.PackageHelper;
import com.gss.model.Package;
import com.gss.model.PackagePackage;

public class UpdatePromoAction {
	
	
	ItemDecoder decode = new ItemDecoder();
	private int intPromoID;
	private String strNonExp = "";
	private String strExp;
	private String strPromoName;
	private String strPromoDesc;
	private String strPromoGuidelines;
	private String strFree = "";
	private double dblPromoPrice;
	private String servicePromoSelect = "";
	private String productPromoSelect = "";
	private String packagePromoSelect = "";
	private String servicePromoQty = "";
	private String productPromoQty = "";
	private String packagePromoQty = "";
	private String requirement = "";
	private String result;
	private List<Integer> type;
	
	public String execute(){
		
		this.type = new ArrayList<Integer>();
		type.add(1);
		
		ServiceService serviceService = new ServiceServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PackageService packageService = new PackageServiceImpl();
		
		List<Service> refService = serviceService.getAllService();
		List<Product> refProduct = productService.getAllProducts();
		List<Package> refPackage = packageService.getAllPackage();
		
		List<ServicePackage> serviceList = new ArrayList<ServicePackage>();
		List<ProductPackage> productList = new ArrayList<ProductPackage>();
		List<PackagePackage> packageList = new ArrayList<PackagePackage>();
		List<Requirement> requirements = new ArrayList<Requirement>();
		
		PromoService promoService = new PromoServiceImpl();
		String result;
		
		String[] services = servicePromoSelect.split(", ");
		String[] products = productPromoSelect.split(", ");
		String[] packages = packagePromoSelect.split(", ");
		
		String[] serviceQty = servicePromoQty.split(", ");
		String[] productQty = productPromoQty.split(", ");
		String[] packageQty = packagePromoQty.split(", ");
		
		if(!servicePromoSelect.equals("")){
			
			for(int i = 0; i < services.length; i++){
				for(int j = 0; j < refService.size(); j++){
					
					Service sample = refService.get(j);
					if(Integer.parseInt(services[i]) == sample.getIntServiceID()){
						serviceList.add(new ServicePackage(1, 1, sample, Integer.parseInt(serviceQty[i]), 1));
					}
				}
			}
		}
		if(!productPromoSelect.equals("")){

			for(int i = 0; i < products.length; i++){
				for(int j = 0; j < refProduct.size(); j++){
					
					Product sample = refProduct.get(j);
					if(Integer.parseInt(products[i]) == sample.getIntProductID()){
						productList.add(new ProductPackage(1, 1, sample, Integer.parseInt(productQty[i]), 1));
					}
				}
			}
			
			System.out.println(productList.size());
		}
		if(!packagePromoSelect.equals("")){
			
			for(int i = 0; i < packages.length; i++){
				for(int j = 0; j < refPackage.size(); j++){
					
					Package sample = refPackage.get(j);
					if(Integer.parseInt(packages[i]) == sample.getIntPackageID()){
						packageList.add(new PackagePackage(1, 1, sample, Integer.parseInt(packageQty[i]), 1));
					}
				}
			}
		}
		if(!requirement.equals(""))
			requirements = Requirement.toOjbect(this.requirement.split(","));
		
		if(!strNonExp.equalsIgnoreCase("on")){
			
			String[] date = strExp.split("/");
			DateHelper helper = new DateHelper();
			String expDate = helper.convert(date);
			
			if(strFree.equals("on")){
				
				Promo promo = new Promo(intPromoID, strPromoName, strPromoDesc, strPromoGuidelines, 0, 1, serviceList, productList, packageList, expDate, 1, PackageHelper.convertToSingleInt(this.type));
				promo.setRequirements(requirements);
				this.result = promoService.updatePromo(promo);
			}
			else{
				
				Promo promo = new Promo(intPromoID, strPromoName, strPromoDesc, strPromoGuidelines, dblPromoPrice, 1, serviceList, productList, packageList, expDate, 1, PackageHelper.convertToSingleInt(this.type));
				promo.setRequirements(requirements);
				this.result = promoService.updatePromo(promo);
			}
		}
		else{
			
			if(strFree.equals("on")){
				
				Promo promo = new Promo(intPromoID, strPromoName, strPromoDesc, strPromoGuidelines, 0, 1, serviceList, productList, packageList, "NON-EXPIRY", 1, PackageHelper.convertToSingleInt(this.type));
				promo.setRequirements(requirements);
				this.result = promoService.updatePromo(promo);
			}
			else{
				
				Promo promo = new Promo(intPromoID, strPromoName, strPromoDesc, strPromoGuidelines, dblPromoPrice, 1, serviceList, productList, packageList, "NON-EXPIRY", 1, PackageHelper.convertToSingleInt(this.type));
				promo.setRequirements(requirements);
				this.result = promoService.updatePromo(promo);
			}
			
		}
		
		return this.result;
	}


	public void setStrNonExp(String strNonExp) {
		this.strNonExp = strNonExp;
	}


	public void setStrExp(String strExp) {
		this.strExp = strExp;
	}


	public void setStrPromoName(String strPromoName) {
		this.strPromoName = strPromoName;
	}


	public void setStrPromoDesc(String strPromoDesc) {
		this.strPromoDesc = strPromoDesc;
	}


	public void setStrFree(String strFree) {
		this.strFree = strFree;
	}


	public void setDblPromoPrice(double dblPromoPrice) {
		this.dblPromoPrice = dblPromoPrice;
	}


	public void setServicePromoSelect(String servicePromoSelect) {
		this.servicePromoSelect = servicePromoSelect;
	}


	public void setProductPromoSelect(String productPromoSelect) {
		this.productPromoSelect = productPromoSelect;
	}


	public void setPackagePromoSelect(String packagePromoSelect) {
		this.packagePromoSelect = packagePromoSelect;
	}


	public void setServicePromoQty(String servicePromoQty) {
		this.servicePromoQty = servicePromoQty;
	}


	public void setProductPromoQty(String productPromoQty) {
		this.productPromoQty = productPromoQty;
	}


	public void setPackagePromoQty(String packagePromoQty) {
		this.packagePromoQty = packagePromoQty;
	}


	public void setIntPromoID(int intPromoID) {
		this.intPromoID = intPromoID;
	}

	public void setStrPromoGuidelines(String strPromoGuidelines) {
		this.strPromoGuidelines = strPromoGuidelines;
	}


	public String getResult() {
		return result;
	}


	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}


	public void setType(List<Integer> type) {
		this.type = type;
	}
	
	

}
