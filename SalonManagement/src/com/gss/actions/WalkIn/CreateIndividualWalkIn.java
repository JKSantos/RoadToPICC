package com.gss.actions.WalkIn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Package;
import com.gss.model.PackageDetails;
import com.gss.model.PackageWalkIn;
import com.gss.model.Product;
import com.gss.model.ProductWalkIn;
import com.gss.model.PromoDetails;
import com.gss.model.PromoWalkIn;
import com.gss.model.Service;
import com.gss.model.ServiceDetails;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;
import com.gss.service.WalkInService;
import com.gss.service.WalkInServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateIndividualWalkIn {
	
	private String strName;
	private String strContactNo;
	private String productString = "";
	private String productQuantity = "";
	private String serviceString = "";		// ID ng service
	private String employeeAssigned = "";
	private List<PackageDetails> packageList = new ArrayList<PackageDetails>();
	private List<PromoDetails> promoList = new ArrayList<PromoDetails>();
	private String strTotalPrice;
	private String discounts = "";
	private String extraCharges = "";
	
	private int intCreatedID;
	
	public String execute() throws Exception{
		
		WalkInService service = new WalkInServiceImpl();
		
		List<ProductWalkIn> productList = new ArrayList<ProductWalkIn>();
		List<ServiceWalkIn> serviceList = new ArrayList<ServiceWalkIn>();
		List<PackageWalkIn> packageList = new ArrayList<PackageWalkIn>();
		List<PromoWalkIn> promoList = new ArrayList<PromoWalkIn>();
		
		String[] serviceID = this.serviceString.split(",");
		String[] employeeID = this.employeeAssigned.split(",");
		
		String[] products = this.productString.split(",");
		String[] productQuantity = this.productQuantity.split(",");
		
		String[] discounts = this.discounts.split(",");
		String[] extraCharges = this.extraCharges.split(",");
		
		
		if(!this.productString.equals("")){
			for(int i = 0; i < products.length; i++){
			
				ProductWalkIn  product = new ProductWalkIn(1, Product.createNullProduct(Integer.parseInt(products[i])), (Integer.parseInt(productQuantity[i])));
			
				productList.add(product);
			}
		}
		
		if(!serviceString.equals("")){
			for(int i = 0; i < serviceID.length; i++){
				
				ServiceWalkIn  serv = new ServiceWalkIn(1, Service.createNullService(Integer.parseInt(serviceID[i])), Employee.createNullEmployee(Integer.parseInt(employeeID[i])), "PENDING");
				
				serviceList.add(serv);
			}
		}
		
		
		for(int i = 0; i < this.packageList.size(); i++){
			
			List<ServiceDetails> details = this.packageList.get(i).getServiceList();
			List<ServiceWalkIn> serv = new ArrayList<ServiceWalkIn>();
			
			for(int j = 0; j < details.size(); j++){
				ServiceDetails serviceDetail = details.get(j);
				ServiceWalkIn walkin = new ServiceWalkIn(1, Service.createNullService(serviceDetail.getIntServiceID()), Employee.createNullEmployee(serviceDetail.getIntEmployeeID()), serviceDetail.getStrStatus());
				serv.add(walkin);
			}
			
			PackageWalkIn packageWalkIn = new PackageWalkIn(1, Package.createNullPackage(this.packageList.get(i).getIntPackageID()), serv);
			packageList.add(packageWalkIn);
		}
		
		
		for(int index = 0; index < this.promoList.size(); index++){
			
			List<PackageWalkIn> packageWalkInList = new ArrayList<PackageWalkIn>();
			List<PackageDetails> packageDetails = this.promoList.get(index).getPackageList();
			List<ServiceDetails> promoServiceDetails = this.promoList.get(index).getServiceList();
			List<ServiceWalkIn> promoServiceList = new ArrayList<ServiceWalkIn>();
			
			for(int i = 0; i < packageDetails.size(); i++){
				
				List<ServiceDetails> details = packageDetails.get(i).getServiceList();
				List<ServiceWalkIn> serv = new ArrayList<ServiceWalkIn>();
				
				for(int j = 0; j < details.size(); j++){
					ServiceDetails serviceDetail = details.get(j);
					ServiceWalkIn walkin = new ServiceWalkIn(1, Service.createNullService(serviceDetail.getIntServiceID()), Employee.createNullEmployee(serviceDetail.getIntEmployeeID()), serviceDetail.getStrStatus());
					serv.add(walkin);
				}
				
				PackageWalkIn packageWalkIn = new PackageWalkIn(1, Package.createNullPackage(packageDetails.get(i).getIntPackageID()), serv);
				packageWalkInList.add(packageWalkIn);
			}
			
			for(int i = 0; i < promoServiceDetails.size(); i++){
				
				ServiceDetails detail = promoServiceDetails.get(i);
				
				ServiceWalkIn  serv = new ServiceWalkIn(1, Service.createNullService(detail.getIntServiceID()), Employee.createNullEmployee(detail.getIntEmployeeID()), detail.getStrStatus());
				
				promoServiceList.add(serv);
			}
			
		}
		
		List<Discount> discountList = new ArrayList<Discount>();
		
		if(!this.discounts.equals("")){
			
			String[] discount = this.discounts.split(",");
			
			for(int index = 0; index < discounts.length; index++){
				discountList.add(Discount.createNullDiscount(Integer.parseInt(discount[index])));
			}
		}
		
		List<ExtraCharge> extraChargeList = new ArrayList<ExtraCharge>();
		
		if(!this.extraCharges.equals("")){
			
			String[] extraCharge = this.extraCharges.split(",");
			
			for(int index = 0; index < extraCharges.length; index++){
				extraChargeList.add(ExtraCharge.createNullExtra(Integer.parseInt(extraCharge[index])));
			}
		}

		Invoice invoice = Invoice.createNullInvoice(extraChargeList, discountList, PriceFormatHelper.convertToDouble(this.strTotalPrice, "Php "), "FULL");
		
		WalkIn walkin = new WalkIn(1, "INDIVIDUAL", this.strName, this.strContactNo, new Date(), serviceList, productList, packageList, promoList, invoice, null, "PENDING", "UNPAID");
	
		int result = service.createWalkIn(walkin);
		
		if(result == 0){
			this.intCreatedID = result;
			return "failed";
		}
		else{
			this.intCreatedID = result;
			return "success";
		}
	
	}
	
	public void setStrName(String strName) {
		this.strName = strName;
	}

	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}

	public void setProductString(String productString) {
		this.productString = productString;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public void setPackageList(List<PackageDetails> packageList) {
		this.packageList = packageList;
	}

	public void setPromoList(List<PromoDetails> promoList) {
		this.promoList = promoList;
	}

	public void setStrTotalPrice(String strTotalPrice) {
		this.strTotalPrice = strTotalPrice;
	}

	public void setDiscounts(String discounts) {
		this.discounts = discounts;
	}

	public void setExtraCharges(String extraCharges) {
		this.extraCharges = extraCharges;
	}

	public int getIntCreatedID() {
		return intCreatedID;
	}

	public void setServiceString(String serviceString) {
		this.serviceString = serviceString;
	}

	public void setEmployeeAssigned(String employeeAssigned) {
		this.employeeAssigned = employeeAssigned;
	}
}
