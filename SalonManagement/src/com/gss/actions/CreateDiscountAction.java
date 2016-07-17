package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.ProductService;
import com.gss.utilities.SearchPackage;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchPromo;
import com.gss.utilities.SearchService;

public class CreateDiscountAction {

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
		
		String result = "failed";
		
		try{
				discount = new Discount(1, strDiscountName, strDiscountDetails, strDiscountGuidelines, Integer.parseInt(strDiscountType), dblDiscountPrice, productList, serviceList, packageList, promoList, 1);
				
				if(service.createDiscount(discount) == true)
					result = "success";
			
				
			return result;
		}
		catch(NullPointerException e){
			discount = new Discount(1, strDiscountName, strDiscountDetails, strDiscountGuidelines, 2, dblDiscountPrice, productList, serviceList, packageList, promoList, 1);

			if(service.createDiscount(discount) == true)
				return "success";
			else
				return "failed";
		}
	}

	public void setStrDiscountName(String strDiscountName) {
		this.strDiscountName = strDiscountName;
	}

	public void setStrDiscountDetails(String strDiscountDetails) {
		this.strDiscountDetails = strDiscountDetails;
	}

	public void setDblDiscountPrice(Double dblDiscountPrice) {
		this.dblDiscountPrice = dblDiscountPrice;
	}

	public void setStrDiscountType(String strDiscountType) {
		this.strDiscountType = strDiscountType;
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

}
