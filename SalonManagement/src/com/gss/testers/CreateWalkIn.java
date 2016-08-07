package com.gss.testers;

import java.util.Date;
import java.util.List;

import com.gss.model.Invoice;
import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.ProductWalkIn;
import com.gss.model.PromoWalkIn;
import com.gss.model.ServiceWalkIn;

public class CreateWalkIn {
	
	private int intWalkInID;
	private String strWalkInType;
	private String strName;
	private String strContactNo;
	private Date datWalkIn;
	private Double dblTotalPrice;
	
	private String strServicesID;
	private String strProductsID;
	private String strPackagesID;
	private String strPromosID;
	private String strServiceQuantities;
	private String strProductQuantities;
	private String strPackageQuantities;
	private String strPromoQuantities;
	
	//di kasama sa lalagyan ng laman
	private List<ServiceWalkIn> services;
	private List<ProductWalkIn> products;
	private List<PackageWalkIn> packages;
	private List<PromoWalkIn> promo;
	
	//di kasama sa lalagyan ng laman
	private Invoice invoice;
	private Payment payment = null;
	private String strWalkInStatus = "";
	private String strPaymentStatus = "UNPAID";
	
	public static void main(String[] args){
		
		
		
	}

}
