package com.gss.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.Promo;
import com.gss.model.Requirement;
import com.gss.model.Service;
import com.gss.service.DiscountServiceImpl;
import com.gss.utilities.PriceFormatHelper;
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
	private String strDiscountPriceFixed;
	private double strDiscountPricePercent;
	private String checkedServices = "";
	private String checkedProducts = "";
	private String checkedPackages = "";
	private String checkedPromos = "";
	private String requirements = "";
	private String result;

	public String execute() throws NumberFormatException, SQLException{

		DiscountServiceImpl service = new DiscountServiceImpl();
		Discount discount;
		System.out.print(this.intDiscountID);
		List<Product> productList = new ArrayList<Product>();
		List<Service> serviceList = new ArrayList<Service>();
		List<Package> packageList = new ArrayList<Package>();
		List<Promo> promoList = new ArrayList<Promo>();
		List<Requirement> requirementList = new ArrayList<Requirement>();
		
		if(!checkedProducts.equals(""))
			productList = new SearchProduct().searchList(checkedProducts.split(","), Product.getAllProduct());
		if(!checkedServices.equals(""))
			serviceList = new SearchService().searchList(checkedServices.split(","), Service.getAllService());
		if(!checkedPackages.equals(""))
			packageList = new SearchPackage().searchList(checkedPackages.split(","), Package.getAllPackage());
		if(!checkedPromos.equals(""))
			promoList = new SearchPromo().searchList(checkedPromos.split(","), Promo.getAllPromo());
		if(!requirements.equals(""))
			requirementList = Requirement.toOjbect(this.requirements.split(","));
		 
		String result = "failed";
		
		try{
			discount = new Discount(this.intDiscountID, strApplicability, strDiscountName, strDiscountDetails, strDiscountGuidelines, Integer.parseInt(strDiscountType), PriceFormatHelper.convertToDouble(strDiscountPriceFixed, "Php "), productList, serviceList, packageList, promoList, 1);
			discount.setRequirements(requirementList);
			this.result = service.updateDiscount(discount);
			System.out.print(result);
			return this.result;
		}
		catch(Exception e){	
			discount = new Discount(this.intDiscountID, strApplicability, strDiscountName, strDiscountDetails, strDiscountGuidelines, Integer.parseInt(strDiscountType), strDiscountPricePercent, productList, serviceList, packageList, promoList, 1);
			discount.setRequirements(requirementList);
			this.result = service.updateDiscount(discount);
			System.out.print(result);
			return this.result;
		}
	}

	public void setStrDiscountName(String strDiscountName) {
		this.strDiscountName = strDiscountName;
	}

	public void setStrDiscountDetails(String strDiscountDetails) {
		this.strDiscountDetails = strDiscountDetails;
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
	
	public void setStrApplicability(String strApplicability){
		this.strApplicability = strApplicability;
	}

	public void setStrDiscountPriceFixed(String strDiscountPriceFixed) {
		this.strDiscountPriceFixed = strDiscountPriceFixed;
	}

	public void setStrDiscountPricePercent(double strDiscountPricePercent) {
		this.strDiscountPricePercent = strDiscountPricePercent;
	}

	public int getIntDiscountID() {
		return intDiscountID;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}

	public String getResult() {
		return result;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
}
