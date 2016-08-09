package com.gss.testers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.ProductWalkIn;
import com.gss.model.PromoWalkIn;
import com.gss.model.ServiceWalkIn;
import com.gss.utilities.PriceFormatHelper;

public class CreateWalkIn {
	
	private int intWalkInID;
	private String strWalkInType;
	private String strName;
	private String strContactNo;
	private Date datWalkIn;
	private String strTotalPrice;
	
	private String strServicesID;
	private String strProductsID;
	private String strPackagesID;
	private String strPromosID;
	private String extraChargesID;
	private String extraChargeQuantities;
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
	
	public static void main(String[] args) throws Exception{
		
		int intWalkInID = 1;
		String strWalkInType = "UNPAID";
		String strName = "JEFFREY";
		String strContactNo = "09361144842";
		Date datWalkIn = new Date();
		String strTotalPrice = "Php 200.00";
		
		String strServicesID = "62,63";
		String strProductsID = "33,35,37";
		String strPackagesID = "";
		String strPromosID = "";
		String extraChargesID = "";
		String extraChargeQuantities = "";
		String strServiceQuantities = "1,2";
		String strProductQuantities = "1,1,1";
		String strPackageQuantities = "";
		String strPromoQuantities = "";
		String strServiceEmployees = "";
		String strPackageEmployees = "";
		String strPromoEmployees = "";
		
		String strServiceEmployee = "";
		
		String strPackageServiceEmployee = "";
		
		String strPromoServiceEmployee = "";
		String strPromoPackageServiceEmployee = "";
		
		//di kasama sa lalagyan ng laman
		List<ServiceWalkIn> services;
		List<ProductWalkIn> products;
		List<PackageWalkIn> packages;
		List<PromoWalkIn> promo;
		
		//di kasama sa lalagyan ng laman
		Invoice invoice;
		Payment payment = null;
		String strWalkInStatus = "";
		String strPaymentStatus = "UNPAID";
		
		List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		List<Discount> discounts = new ArrayList<Discount>();
		Invoice invoice1 = Invoice.createNullInvoice(extraCharges, discounts, PriceFormatHelper.convertToDouble(strTotalPrice, "Php "));
		
		
	}

}
