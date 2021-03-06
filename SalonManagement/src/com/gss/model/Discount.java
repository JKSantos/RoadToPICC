package com.gss.model;

import java.util.List;

import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;

public class Discount {
	
	private int intDiscountID;
	private String applicability;
	private String strDiscountName;
	private String strDiscountDesc;
	private String strDiscountGuidelines;
	private int intDiscountType;
	private double dblDiscountAmount;
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	private int intDiscountStatus;
	private List<Requirement> requirements;
	private String stringPrice;
	
	public Discount(int intDiscountID, String applicability, String strDiscountName, String strDiscountDesc, String strDiscountGuidelines, int intDiscountType, double dblDiscountAmount, List<Product> productList, List<Service> serviceList, List<Package> packageList, List<Promo> promoList, int intDiscountStatus){
		this.setIntDiscountID(intDiscountID);
		this.setApplicability(applicability);
		this.setStrDiscountName(strDiscountName);
		this.setStrDiscountDesc(strDiscountDesc);
		this.setStrDiscountGuidelines(strDiscountGuidelines);
		this.setIntDiscountType(intDiscountType);
		this.setDblDiscountAmount(dblDiscountAmount);
		this.setProductList(productList);
		this.setServiceList(serviceList);
		this.setPackageList(packageList);
		this.setPromoList(promoList);
		this.setIntDiscountStatus(intDiscountStatus);
	}

	public int getIntDiscountID() {
		return intDiscountID;
	}

	public void setIntDiscountID(int intDiscountID) {
		this.intDiscountID = intDiscountID;
	}

	public String getStrDiscountName() {
		return strDiscountName;
	}

	public void setStrDiscountName(String strDiscountName) {
		this.strDiscountName = strDiscountName;
	}

	public String getStrDiscountDesc() {
		return strDiscountDesc;
	}

	public void setStrDiscountDesc(String strDiscountDesc) {
		this.strDiscountDesc = strDiscountDesc;
	}

	public int getIntDiscountType() {
		return intDiscountType;
	}

	public void setIntDiscountType(int intDiscountType) {
		this.intDiscountType = intDiscountType;
	}

	public double getDblDiscountAmount() {
		return dblDiscountAmount;
	}

	public void setDblDiscountAmount(double dblDiscountAmount) {
		this.dblDiscountAmount = dblDiscountAmount;
	}

	public int getIntDiscountStatus() {
		return intDiscountStatus;
	}

	public void setIntDiscountStatus(int intDiscountStatus) {
		this.intDiscountStatus = intDiscountStatus;
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
	
	public static List<Discount> getAllDiscount(){
		DiscountService service = new DiscountServiceImpl();
		
		return service.getAllDiscount();
	}
	
	public static Discount createNullDiscount(int intDiscountID){
		return new Discount(intDiscountID, "", "", "", "", intDiscountID, 0, null, null, null, null, intDiscountID);
	}
	
	public static Discount searchDiscount(int intDiscountID, List<Discount> discountList){
		
		for(int index = 0; index < discountList.size(); index++){

			if(intDiscountID == discountList.get(index).getIntDiscountID()){
				return discountList.get(index);
			}
		}
		return null;
	}

	public String getApplicability() {
		return applicability;
	}

	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}
	
	public static Discount getDiscountByID(int discountID){
		
		DiscountService service = new DiscountServiceImpl();
		
		return service.getDiscountByID(discountID);
	}
	
	public static List<Discount> getAllDiscountNoDetails(){
		
		DiscountService service = new DiscountServiceImpl();
		
		return service.getAllDiscountNoDetails();
	}

	public static List<Discount> queryAllDiscount() {
		
		DiscountService service = new DiscountServiceImpl();
		
		return service.queryAllDiscount();
	}

	public List<Requirement> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<Requirement> requirements) {
		this.requirements = requirements;
	}

	public String getStringPrice() {
		return stringPrice;
	}

	public void setStringPrice(String stringPrice) {
		this.stringPrice = stringPrice;
	}
}
