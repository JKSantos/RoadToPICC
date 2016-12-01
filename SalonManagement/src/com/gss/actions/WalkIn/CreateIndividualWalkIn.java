package com.gss.actions.WalkIn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.gss.dao.CustomerTransactionHelper;
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
import com.gss.testers.SampleJSON;
import com.gss.utilities.JavaSqlDateTimeHelper;
import com.gss.utilities.PriceFormatHelper;
import com.opensymphony.xwork2.ActionSupport;


public class CreateIndividualWalkIn extends ActionSupport{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String strName;
	private String strContactNo = "";
	private String productString = "";
	private String productQuantity = "";
	private String serviceString = "";		// ID ng service
	private String employeeAssigned = "";
	private List<PackageDetails> packageList = new ArrayList<PackageDetails>();
	private List<PromoDetails> promoList = new ArrayList<PromoDetails>();
	private String packageLists = "";
	private String promoLists = "";
	private String strTotalPrice;
	private String discounts = "";
	private String extraCharges = "";
	private String customerType = "WALKIN";
	private String appointmentDate = "";
	private String appointmentTime = "";
	private String packageString = "";
	private int intCustID;
	
	private int intCreatedID;
	
	public String execute() throws Exception{
		
		if(this.customerType == null){
			this.customerType = "WALKIN";
		}
		
		if(!this.packageLists.equals("")) {
			Gson gson = new Gson();
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(this.packageLists);
			JsonArray arr = element.getAsJsonArray();
			
			for(int i = 0; i < arr.size(); i++){
				JsonElement packageElement = arr.get(i);
				JsonObject packageObject = packageElement.getAsJsonObject();
				JsonElement serviceElement = packageObject.get("serviceList");
				JsonElement idElement = packageObject.get("intPackageID");
				int packageID = idElement.getAsInt();
				System.out.println(packageID);
				List<ServiceDetails> detail = gson.fromJson(serviceElement, new TypeToken<List<ServiceDetails>>(){}.getType());
				this.packageList.add(new PackageDetails(packageID, detail));
			}
			
		}
		
		this.strContactNo = this.strContactNo.replaceAll("\"", "");
		
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
		
		try{
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
		}catch(NullPointerException e){
			//do nothing
		}
		
		try{
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
		}catch(NullPointerException e){
			//do nothing
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

		WalkIn walkin = new WalkIn(1, customerType, this.strName, this.strContactNo, new Date(), serviceList, productList, packageList, promoList, invoice, null, "PENDING", "UNPAID");
		
		try{
			
		String time = appointmentDate.replaceAll("AM", "");	
		time = appointmentDate.replaceAll("PM", "");	

		if(this.customerType.equalsIgnoreCase("APPOINTMENT")){
			walkin.setAppointmentDate(JavaSqlDateTimeHelper.stringToDate(this.appointmentTime));
			walkin.setAppointmentTime(JavaSqlDateTimeHelper.stringToTime(time));
		}
		}catch(Exception e){
			//do nothing
		}
		
		int result = service.createWalkIn(walkin);
		
		if(result == 0){
			this.intCreatedID = result;

			return "error";
			
			
		}
		else{
			this.intCreatedID = result;
			if(this.customerType.equals("APPOINTMENT")){
				System.out.println("Customer ID: " + this.intCustID);
			System.out.println("waLKiN ID: " + this.intCreatedID);
				CustomerTransactionHelper.insertCustomerAppointment(intCreatedID, intCustID, 1);
			}
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

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public void setIntCustID(int intCustID) {
		this.intCustID = intCustID;
	}

	public String getPackageString() {
		return packageString;
	}

	public void setPackageString(String packageString) {
		this.packageString = packageString;
	}

	public void setPackageLists(String packageLists) {
		this.packageLists = packageLists;
	}

	public void setPromoLists(String promoLists) {
		this.promoLists = promoLists;
	}
}
