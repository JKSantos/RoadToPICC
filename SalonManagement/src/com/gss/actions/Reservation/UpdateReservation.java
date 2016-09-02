package com.gss.actions.Reservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.model.Contract;
import com.gss.model.Customer;
import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.EmployeeAssigned;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.ReservationInclusion;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;
import com.gss.model.Package;
import com.gss.utilities.ContractGenerator;
import com.gss.utilities.DateHelper;
import com.gss.utilities.PriceFormatHelper;
import com.gss.utilities.QuantityHelper;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchService;
import com.gss.utilities.TimeHelper;

public class UpdateReservation {
	
	private int intReservationID;
	
	private String strCustomerType;
	private String strCompanyName;
	private String strName;
	private String strAddress;
	private String strContactNo;
	private String strEmail;
	
	private int intReservationType;
	
	private String datFrom = "";
	private String datTo = "";
	private String timFrom;
	private String timTo = "00:00AM";			
	private String strVenue; 		
	private int intLocationID;
	private int headCount;	
	private String strTotalPrice = ""; 
	private String strStatus = "PENDING";	
	
	private Invoice invoice;		
	
	private String selectedProducts = "";
	private String selectedServices = "";
	private String selectedPackages = "";
	private String selectedPromos = "";	
	
	private String productQuantity = "";	
	private String serviceQuantity = "";	
	private String packageQuantity = "";
	private String promoQuantity = "";	
	
	private String selectedEmployees = "";		
	private String selectedExtraCharges = "";	
	private String selectedDiscounts = "";		
	private String paymentType = "";		
	
	
	public String execute() throws Exception{
		
		List<EmployeeAssigned> employeeAssigned = new ArrayList<EmployeeAssigned>();
		ReservationInclusion includedItems;
		
		String[] selectedEmployees = this.selectedEmployees.split(",");
		String[] selectedDiscounts = this.selectedDiscounts.split(",");
		String[] selectedExtraCharges = this.selectedExtraCharges.split(",");
	
		List<Product> prodList = Product.getAllProduct();
		List<Service> serviceList = Service.getAllService();
		
		Reservation reservation = null;
		
		//for invoice
		
			//ExtraCharges
			List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		
			
			if(!this.selectedExtraCharges.equals("")){
				for(int index = 0; index < selectedExtraCharges.length; index++){
					ExtraCharge extra = ExtraCharge.createNullExtra(Integer.parseInt(selectedExtraCharges[index]));
					extraCharges.add(extra);
				}
			}
			//Discounts
			List<Discount> discounts = new ArrayList<Discount>();
			
			if(!this.selectedDiscounts.equals("")){
				for(int index = 0; index < selectedDiscounts.length; index++){
					Discount discount = Discount.createNullDiscount(Integer.parseInt(selectedDiscounts[index]));
					discounts.add(discount);
				}
			}
			
			System.out.println("Price " + this.strTotalPrice);
				invoice = Invoice.createNullInvoice(extraCharges, discounts, PriceFormatHelper.convertToDouble(this.strTotalPrice, "Php "), this.paymentType);
		
		//for ReservationInlusion
		List<ProductOrder> products = new ArrayList<ProductOrder>();
		List<ReservedService> services = new ArrayList<ReservedService>();
		List<ReservedPackage> packages = new ArrayList<ReservedPackage>();
		List<ReservedPromo> promos = new ArrayList<ReservedPromo>();
		includedItems = new ReservationInclusion(products, services, packages, promos);
				
				//ProductOrder
				if(!this.selectedProducts.equals("")){
					String[] selectedProducts = this.selectedProducts.split(",");
					String[] productQuantity = QuantityHelper.removeEmptyQuantity(this.productQuantity.split(","));
					
					for(int index = 0; index < selectedProducts.length; index++){
						Product product = new SearchProduct().search(Integer.parseInt(selectedProducts[index]), prodList);
						ProductOrder productOrder = new ProductOrder(1, product, Integer.parseInt(productQuantity[index]), 1);
						products.add(productOrder);
					}
				}
					
				//Service
				if(!this.selectedServices.equals("")){
					String[] selectedServices = this.selectedServices.split(",");
					String[] serviceQuantity = QuantityHelper.removeEmptyQuantity(this.serviceQuantity.split(","));
				
					for(int index = 0; index < selectedServices.length; index++){
						Service service = new SearchService().search(Integer.parseInt(selectedServices[index]), serviceList);
						ReservedService reservedService = new ReservedService(1, 1, service, Integer.parseInt(serviceQuantity[index]), 1);					services.add(reservedService);
					}
				}
				//Package
				if(!this.selectedPackages.equals("")){
					String[] selectedPackages = this.selectedPackages.split(",");
					String[] packageQuantity = this.packageQuantity.split(",");
				
				
					for(int index = 0; index < selectedPackages.length; index++){
						Package packagee = Package.createNullPackage(Integer.parseInt(selectedPackages[index]));
						ReservedPackage reservedPackage =new ReservedPackage(1, 1, packagee, Integer.parseInt(packageQuantity[index]), 1);
						packages.add(reservedPackage);
					}
				}
				
				//Promo
				if(!this.selectedPromos.equals("")){
					String[] selectedPromos = this.selectedPromos.split(",");
					String[] promoQuantity = this.promoQuantity.split(",");
				
				
					for(int index = 0; index < selectedPromos.length; index++){
						Promo promo = Promo.createNullPromo(Integer.parseInt(selectedPromos[index]));
						ReservedPromo reservedPromo = new ReservedPromo(1, 1, promo, Integer.parseInt(promoQuantity[index]), 1);
						promos.add(reservedPromo);
					}
				}
				
				//for Employee Assigned
				for(int index = 0; index < selectedEmployees.length; index++){
					Employee emp = Employee.createNullEmployee(Integer.parseInt(selectedEmployees[index]));
					employeeAssigned.add(new EmployeeAssigned(1, 1, emp, 1));
				}
				
				
				
				String dateFrom = new DateHelper().convert(this.datFrom.split("/"));
				String dateTo = new DateHelper().convert(this.datTo.split("/"));
				
				Customer customer = new Customer(1, this.strCustomerType, this.strCompanyName, this.strName, this.strAddress, this.strContactNo, this.strEmail);
				ContractGenerator generator = new ContractGenerator();
				Contract contract = new Contract(DateHelper.stringDate(), "JEFFREY SANTOS", "SALON MANGEMENT SYSTEM", "189-DR. SIXTO ANTONIO AVENUE, ROSARIO PASIG CITY", this.strName.toUpperCase(), this.strAddress.toUpperCase(), reservation);
				String contractPath = generator.createContract(contract);
				reservation = new Reservation(this.intReservationID, customer, includedItems, intReservationType, new Date(), DateHelper.parseDate(dateFrom), DateHelper.parseDate(dateTo), TimeHelper.parseTime(timFrom), TimeHelper.parseTime(timTo), strVenue, headCount, this.intLocationID, employeeAssigned, invoice, strStatus, contractPath);
				
		if(Reservation.updateReservation(reservation) == true){
			return "success";	
		}
		else
			return "failed";
	}
	public void setStrCustomerType(String strCustomerType) {
		this.strCustomerType = strCustomerType;
	}
	public void setStrCompanyName(String strCompanyName) {
		this.strCompanyName = strCompanyName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}
	public void setStrContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	public void setStrEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	public void setIntReservationType(int intReservationType) {
		this.intReservationType = intReservationType;
	}
	public void setDatFrom(String datFrom) {
		this.datFrom = datFrom;
	}
	public void setDatTo(String datTo) {
		this.datTo = datTo;
	}
	public void setTimFrom(String timFrom) {
		this.timFrom = timFrom;
	}
	public void setTimTo(String timTo) {
		this.timTo = timTo;
	}
	public void setStrVenue(String strVenue) {
		this.strVenue = strVenue;
	}
	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
	}
	public void setStrTotalPrice(String strTotalPrice) {
		this.strTotalPrice = strTotalPrice;
	}
	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public void setSelectedProducts(String selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	public void setSelectedServices(String selectedServices) {
		this.selectedServices = selectedServices;
	}
	public void setSelectedPackages(String selectedPackages) {
		this.selectedPackages = selectedPackages;
	}
	public void setSelectedPromos(String selectedPromos) {
		this.selectedPromos = selectedPromos;
	}
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	public void setServiceQuantity(String serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}
	public void setPackageQuantity(String packageQuantity) {
		this.packageQuantity = packageQuantity;
	}
	public void setPromoQuantity(String promoQuantity) {
		this.promoQuantity = promoQuantity;
	}
	public void setSelectedEmployees(String selectedEmployees) {
		this.selectedEmployees = selectedEmployees;
	}
	public void setSelectedExtraCharges(String selectedExtraCharges) {
		this.selectedExtraCharges = selectedExtraCharges;
	}
	public void setSelectedDiscounts(String selectedDiscounts) {
		this.selectedDiscounts = selectedDiscounts;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public void setIntReservationID(int intReservationID) {
		this.intReservationID = intReservationID;
	}
}
