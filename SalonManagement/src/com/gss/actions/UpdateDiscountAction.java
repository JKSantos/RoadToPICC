package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.service.DiscountServiceImpl;
import com.gss.utilities.SearchPackage;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchPromo;
import com.gss.utilities.SearchService;

public class UpdateDiscountAction {

	private int intDiscountID;
	private String strApplicability;
	private String strDiscountName;
	private String strDiscountDetails;
	private String strDiscountGuidelines;
	private String strDiscountType;
	private Double dblDiscountPrice;
	private String checkedServices;
	private String checkedProducts;
	private String checkedPackages;
	private String checkedPromos;

	public String execute(){

		DiscountServiceImpl service = new DiscountServiceImpl();
		Discount discount;
		String result = "failed";
		
		List<Product> productList = new ArrayList<Product>();
		List<Service> serviceList = new ArrayList<Service>();
		List<Package> packageList = new ArrayList<Package>();
		List<Promo> promoList = new ArrayList<Promo>();
		
		if(!checkedProducts.equals(""))
			productList = new SearchProduct().searchList(checkedProducts.split(","), Product.getAllProduct());
		if(!checkedServices.equals(""))
			serviceList = new SearchService().searchList(checkedServices.split(","), Service.getAllService());
		if(!checkedPackages.equals(""))
			packageList = new SearchPackage().searchList(checkedPackages.split(","), Package.getAllPackage());
		if(!checkedPromos.equals(""))
			promoList = new SearchPromo().searchList(checkedPackages.split(","), Promo.getAllPromo());
		
		try{
				discount = new Discount(1, strApplicability, strDiscountName, strDiscountDetails, strDiscountGuidelines, Integer.parseInt(strDiscountType), dblDiscountPrice, productList, serviceList, packageList, promoList, 1);
				
				if(service.createDiscount(discount) == true)
					result = "success";
			
				
			return result;
		}
		catch(NullPointerException e){
			discount = new Discount(1, strApplicability, strDiscountName, strDiscountDetails, strDiscountGuidelines, 2, dblDiscountPrice, productList, serviceList, packageList, promoList, 1);

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

	public int getIntDiscountID() {
		return intDiscountID;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}

	public void setStrDiscountGuidelines(String strDiscountGuidelines) {
		this.strDiscountGuidelines = strDiscountGuidelines;
	}

	public void setCheckedServices(String checkedServices) {
		this.checkedServices = checkedServices;
	}

	public void setCheckedProducts(String checkedProducts) {
		this.checkedProducts = checkedProducts;
	}

	public void setCheckedPackages(String checkedPackages) {
		this.checkedPackages = checkedPackages;
	}

	public void setCheckedPromos(String checkedPromos) {
		this.checkedPromos = checkedPromos;
	}
	
	public void setStrApplicability(String strApplicability){
		this.strApplicability = strApplicability;
	}

}
