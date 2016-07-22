package com.gss.actions.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.gss.utilities.DateHelper;
import com.gss.utilities.DiscountChecker;
import com.gss.utilities.QuantityHelper;
import com.gss.utilities.TimeHelper;

public class CreateReservation {
	
	private Customer customer;
	private ReservationInclusion includedItems;
	private int intReservationType = 2;
	private Date dateCreated = new Date();
	private Date datFrom;
	private Date datTo = new Date();		//if reservation is home service, this is not needed
	private String timFrom;
	private String timTo;
	private String strVenue; //if type is HomeService, value is equal to customer address
	private int headCount;
	private List<EmployeeAssigned> employeeAssigned;
	private Invoice invoice;
	private String strStatus;
	
	private String fromMeridian;	//for timeFrom, AM or PM
	private String toMeridian = "AM";		//for timTo, AM or PM, if reservation is home service, this is not needed
	private String selectedProducts; //only accepts comma separated string
	private String selectedServices;	//only accepts comma separated string
	private String selectedPackages;	//only accepts comma separated string
	private String selectedPromos;	//only accepts comma separated string
	
	private String productQuantity;	//only accepts comma separated string
	private String serviceQuantity;	//only accepts comma separated string
	private String packageQuantity;	//only accepts comma separated string
	private String promoQuantity;	//only accepts comma separated string
	
	private List<String> selectedEmployees;	
	private List<String> selectedExtraCharges;	
	private List<String> selectedDiscounts;	

	private Discount discount;
	
	public String execute() throws SQLException{
	
		Reservation reservation;
		
		//for invoice
		
			//ExtraCharges
			List<ExtraCharge> extraCharges = new ArrayList<ExtraCharge>();
		
			for(int index = 0; index < this.selectedDiscounts.size(); index++){
				ExtraCharge extra = ExtraCharge.createNullExtra(Integer.parseInt(this.selectedExtraCharges.get(index)));
				extraCharges.add(extra);
			}
		
			//Discounts
			List<Discount> discounts = new ArrayList<Discount>();
			
			for(int index = 0; index < this.selectedDiscounts.size(); index++){
				Discount discount = Discount.createNullDiscount(Integer.parseInt(this.selectedDiscounts.get(index)));
				discounts.add(discount);
				this.discount = discount;
			}
			
				invoice = Invoice.createNullInvoice(extraCharges, discounts);
		
		//for ReservationInlusion
		List<ProductOrder> products = new ArrayList<ProductOrder>();
		List<ReservedService> services = new ArrayList<ReservedService>();
		List<ReservedPackage> packages = new ArrayList<ReservedPackage>();
		List<ReservedPromo> promos = new ArrayList<ReservedPromo>();
		includedItems = new ReservationInclusion(products, services, packages, promos);
				
				//ProductOrder
				String[] selectedProducts = this.selectedProducts.split(",");
				String[] productQuantity = QuantityHelper.removeEmptyQuantity(this.productQuantity.split(","));
				
				for(int index = 0; index < selectedProducts.length; index++){
					Product product = Product.createNullProduct(Integer.parseInt(selectedProducts[index]));
					ProductOrder productOrder = DiscountChecker.checkProductDiscount(product, discount, Integer.parseInt(productQuantity[index]));
					products.add(productOrder);
				}
					
				//Service
				String[] selectedServices = this.selectedServices.split(",");
				String[] serviceQuantity = QuantityHelper.removeEmptyQuantity(this.serviceQuantity.split(","));
				
				for(int index = 0; index < selectedProducts.length; index++){
					Service service = Service.createNullService(Integer.parseInt(selectedServices[index]));
					ReservedService reservedService = DiscountChecker.checkServiceDiscount(service, discount, Integer.parseInt(serviceQuantity[index]));
					services.add(reservedService);
				}
					
				//Package
				String[] selectedPackages = this.selectedPackages.split(",");
				String[] packageQuantity = this.packageQuantity.split(",");
				
				for(int index = 0; index < selectedPackages.length; index++){
					Package packagee = Package.createNullPackage(Integer.parseInt(selectedPackages[index]));
					ReservedPackage reservedPackage = DiscountChecker.checkPackageDiscount(packagee, discount, Integer.parseInt(packageQuantity[index]));
					packages.add(reservedPackage);
				}
				
				//Promo
				
				String[] selectedPromos = this.selectedPromos.split(",");
				String[] promoQuantity = this.promoQuantity.split(",");
				
				for(int index = 0; index < selectedPromos.length; index++){
					Promo promo = Promo.createNullPromo(Integer.parseInt(selectedPromos[index]));
					ReservedPromo reservedPromo = DiscountChecker.checkPromoDiscount(promo, discount, Integer.parseInt(promoQuantity[index]));
					promos.add(reservedPromo);
				}
				
				//for Employee Assigned
				for(int index = 0; index < this.selectedEmployees.size(); index++){
					Employee emp = Employee.createNullEmployee(Integer.parseInt(this.selectedEmployees.get(index)));
					employeeAssigned.add(new EmployeeAssigned(1, 1, emp, 1));
				}
		
				reservation = new Reservation(1, customer, includedItems, intReservationType, dateCreated, datFrom, datTo, TimeHelper.parseTime(timFrom, fromMeridian), TimeHelper.parseTime(timTo, toMeridian), strVenue, headCount, employeeAssigned, invoice, strStatus);
				
		if(Reservation.createReservation(reservation) == true)
			return "success";
		else
			return "failed";
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setIntReservationType(int intReservationType) {
		this.intReservationType = intReservationType;
	}
	public void setDatFrom(String datFrom) {
		this.datFrom = DateHelper.parseDate(datFrom);
	}
	public void setDatTo(String datTo) {
		this.datTo = DateHelper.parseDate(datTo);
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
	public void setHeadCount(int headCount) {
		this.headCount = headCount;
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
	public void setSelectedEmployees(List<String> selectedEmployees) {
		this.selectedEmployees = selectedEmployees;
	}
	public void setSelectedExtraCharges(List<String> selectedExtraCharges) {
		this.selectedExtraCharges = selectedExtraCharges;
	}
	public void setSelectedDiscounts(List<String> selectedDiscounts) {
		this.selectedDiscounts = selectedDiscounts;
	}
	public void setFromMeridian(String fromMeridian) {
		this.fromMeridian = fromMeridian;
	}
	public void setToMeridian(String toMeridian) {
		this.toMeridian = toMeridian;
	}
}
