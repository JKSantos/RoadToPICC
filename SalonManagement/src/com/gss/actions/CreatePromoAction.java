package com.gss.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Promo;
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
import com.gss.model.Package;
import com.gss.model.PackagePackage;
import com.opensymphony.xwork2.ActionSupport;

public class CreatePromoAction {
	
	
	ItemDecoder decode = new ItemDecoder();
	private String strNonExp = "";
	private String strExp;
	private String strPromoName;
	private String strPromoDesc;
	private String strFree = "";
	private double dblPromoPrice;
	private String servicePromoSelect = "";
	private String productPromoSelect = "";
	private String packagePromoSelect = "";
	private String servicePromoQty = "";
	private String productPromoQty = "";
	private String packagePromoQty = "";
	
	public String execute(){
		
		ServiceService serviceService = new ServiceServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PackageService packageService = new PackageServiceImpl();
		
		List<Service> refService = serviceService.getAllService();
		List<Product> refProduct = productService.getAllProducts();
		List<Package> refPackage = packageService.getAllPackage();
		
		List<ServicePackage> serviceList = new ArrayList<ServicePackage>();
		List<ProductPackage> productList = new ArrayList<ProductPackage>();
		List<PackagePackage> packageList = new ArrayList<PackagePackage>();
		
		PromoService promoService = new PromoServiceImpl();
		boolean result;
		
		String[] services = servicePromoSelect.split(", ");
		String[] products = productPromoSelect.split(", ");
		String[] packages = packagePromoSelect.split(", ");
		
		String[] serviceQty = servicePromoQty.split(", ");
		String[] productQty = productPromoQty.split(", ");
		String[] packageQty = packagePromoQty.split(", ");
		
		if(!servicePromoSelect.equals("")){
			
			String[] serviceOrder = decode.serviceOrderByChecked(refService, services);
			String[] serviceQuantity = decode.getServiceQuantity(serviceOrder, serviceQty);
			
			for(int i = 0; i < services.length; i++){
				for(int j = 0; j < refService.size(); j++){
					
					Service sample = refService.get(j);
					if(Integer.parseInt(services[i]) == sample.getIntServiceID()){
						serviceList.add(new ServicePackage(1, 1, sample, Integer.parseInt(serviceQuantity[i]), 1));
					}
				}
			}
		}
		if(!productPromoSelect.equals("")){
			
			String[] productOrder = decode.productOrderByChecked(refProduct, products);
			String[] productQuantity = decode.getProductQuantity(productOrder, productQty);
			
			for(int i = 0; i < products.length; i++){
				for(int j = 0; j < refProduct.size(); j++){
					
					Product sample = refProduct.get(j);
					if(Integer.parseInt(products[i]) == sample.getIntProductID()){
						productList.add(new ProductPackage(1, 1, sample, Integer.parseInt(productQuantity[i]), 1));
					}
				}
			}
			
			System.out.println(productList.size());
		}
		if(!packagePromoSelect.equals("")){
			
			String[] packageOrder = decode.packageOrderByChecked(refPackage, packages);
			String[] packageQuantity = decode.getPackageQuantity(packageOrder, packageQty);
			
			for(int i = 0; i < packages.length; i++){
				for(int j = 0; j < refPackage.size(); j++){
					
					Package sample = refPackage.get(j);
					if(Integer.parseInt(packages[i]) == sample.getIntPackageID()){
						packageList.add(new PackagePackage(1, 1, sample, Integer.parseInt(packageQuantity[i]), 1));
					}
				}
			}
		}
		
		
		if(!strNonExp.equalsIgnoreCase("on")){
			
			String[] date = strExp.split("/");
			DateHelper helper = new DateHelper();
			String expDate = helper.convert(date);
			
			if(strFree.equals("on")){
				
				Promo promo = new Promo(1, strPromoName, strPromoDesc, 0, 1, serviceList, productList, packageList, expDate, 1);
				result = promoService.createPromo(promo);
			}
			else{
				
				Promo promo = new Promo(1, strPromoName, strPromoDesc, dblPromoPrice, 1, serviceList, productList, packageList, expDate, 1);
				result = promoService.createPromo(promo);
			}
		}
		else{
			
			if(strFree.equals("on")){
				
				Promo promo = new Promo(1, strPromoName, strPromoDesc, 0, 1, serviceList, productList, packageList, "NON-EXPIRY", 1);
				result = promoService.createPromo(promo);
			}
			else{
				
				Promo promo = new Promo(1, strPromoName, strPromoDesc, dblPromoPrice, 1, serviceList, productList, packageList, "NON-EXPIRY", 1);
				result = promoService.createPromo(promo);
			}
			
		}
		
		if(result == true)
			return "success";
		else
			return "failed";
	}

	public String getStrNonExp() {
		return strNonExp;
	}

	public void setStrNonExp(String strNonExp) {
		this.strNonExp = strNonExp;
	}

	public String getStrExp() {
		return strExp;
	}

	public void setStrExp(String strExp) {
		this.strExp = strExp;
	}

	public String getStrPromoName() {
		return strPromoName;
	}

	public void setStrPromoName(String strPromoName) {
		this.strPromoName = strPromoName;
	}

	public String getStrPromoDesc() {
		return strPromoDesc;
	}

	public void setStrPromoDesc(String strPromoDesc) {
		this.strPromoDesc = strPromoDesc;
	}

	public String getStrFree() {
		return strFree;
	}

	public void setStrFree(String strFree) {
		this.strFree = strFree;
	}

	public double getDblPromoPrice() {
		return dblPromoPrice;
	}

	public void setDblPromoPrice(double dblPromoPrice) {
		this.dblPromoPrice = dblPromoPrice;
	}

	public String getServicePromoSelect() {
		return servicePromoSelect;
	}

	public void setServicePromoSelect(String servicePromoSelect) {
		this.servicePromoSelect = servicePromoSelect;
	}

	public String getProductPromoSelect() {
		return productPromoSelect;
	}

	public void setProductPromoSelect(String productPromoSelect) {
		this.productPromoSelect = productPromoSelect;
	}

	public String getPackagePromoSelect() {
		return packagePromoSelect;
	}

	public void setPackagePromoSelect(String packagePromoSelect) {
		this.packagePromoSelect = packagePromoSelect;
	}

	public String getServicePromoQty() {
		return servicePromoQty;
	}

	public void setServicePromoQty(String servicePromoQty) {
		this.servicePromoQty = servicePromoQty;
	}

	public String getProductPromoQty() {
		return productPromoQty;
	}

	public void setProductPromoQty(String productPromoQty) {
		this.productPromoQty = productPromoQty;
	}

	public String getPackagePromoQty() {
		return packagePromoQty;
	}

	public void setPackagePromoQty(String packagePromoQty) {
		this.packagePromoQty = packagePromoQty;
	}
	
	

}
