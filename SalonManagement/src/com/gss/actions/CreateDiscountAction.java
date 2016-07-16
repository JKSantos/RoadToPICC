package com.gss.actions;

import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.service.DiscountServiceImpl;

public class CreateDiscountAction {

	private String strDiscountName;
	private String strDiscountDetails;
	private String strDiscountGuidelines;
	private String strDiscountType;
	private Double dblDiscountPrice;
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;

	public String execute(){

		DiscountServiceImpl service = new DiscountServiceImpl();
		Discount discount;
		String result = "failed";
		
		try{
				discount = new Discount(1, strDiscountName, strDiscountDetails, strDiscountGuidelines, Integer.parseInt(strDiscountType), dblDiscountPrice, 1);
				
				if(service.createDiscount(discount) == true)
					result = "success";
			
				
			return result;
		}
		catch(NullPointerException e){
			discount = new Discount(1, strDiscountName, strDiscountDetails, strDiscountGuidelines, 2, dblDiscountPrice, 1);

			if(service.createDiscount(discount) == true)
				return "success";
			else
				return "failed";
		}
	}

	public String getStrDiscountName() {
		return strDiscountName;
	}

	public void setStrDiscountName(String strDiscountName) {
		this.strDiscountName = strDiscountName;
	}

	public String getStrDiscountDetails() {
		return strDiscountDetails;
	}

	public void setStrDiscountDetails(String strDiscountDetails) {
		this.strDiscountDetails = strDiscountDetails;
	}

	public Double getDblDiscountPrice() {
		return dblDiscountPrice;
	}

	public void setDblDiscountPrice(Double dblDiscountPrice) {
		this.dblDiscountPrice = dblDiscountPrice;
	}

	public String getStrDiscountType() {
		return strDiscountType;
	}

	public void setStrDiscountType(String strDiscountType) {
		this.strDiscountType = strDiscountType;
	}

	public String getStrDiscountGuidelines() {
		return strDiscountGuidelines;
	}

	public void setStrDiscountGuidelines(String strDiscountGuidelines) {
		this.strDiscountGuidelines = strDiscountGuidelines;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Package> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<Package> packageList) {
		this.packageList = packageList;
	}

	public List<Promo> getPromoList() {
		return promoList;
	}

	public void setPromoList(List<Promo> promoList) {
		this.promoList = promoList;
	}

}
