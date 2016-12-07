package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import com.gss.connection.JDBCConnection;
import com.gss.model.Customer;
import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.EmployeeAssigned;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Package;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.ReservationInclusion;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.EmployeeService;
import com.gss.service.EmployeeServiceImpl;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.PromoService;
import com.gss.service.PromoServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.*;

public class ReservationJDBCRepository implements ReservationRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	private int intReservationID;
	private Customer customer;
	private ReservationInclusion includedItems;
	private int intReservationType;
	private Date dateCreated;
	private Date datFrom;
	private Date datTo;
	private Time timFrom;
	private Time timTo;
	private String strVenue;
	private int headCount;
	private List<EmployeeAssigned> employeeAssigned;
	private Invoice invoice;
	private String strStatus;
	private int intLocationID;
	private String strContract;
	
	
	private List<ProductOrder> productList = new ArrayList<ProductOrder>();
	private List<ReservedService> serviceList = new ArrayList<ReservedService>();
	private List<ReservedPackage> packageList = new ArrayList<ReservedPackage>();
	private List<ReservedPromo> promoList = new ArrayList<ReservedPromo>();
	
	private int intInvoiceID;
	
	public Reservation getReservationByInvoice(int invoiceID){
		
		Connection con 						= jdbc.getConnection();
		String getReservations 				= "SELECT * FROM tblReservation WHERE intInvoiceID = ?;";
		Reservation reservation = null;
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			preReservaton.setInt(1, invoiceID);
			ResultSet reservationResult = preReservaton.executeQuery();
			
			while(reservationResult.next()){
				
				this.intReservationID 		= reservationResult.getInt(1);
				this.intReservationType 	= reservationResult.getInt(2);
				this.dateCreated 			= reservationResult.getDate(3);
				this.datFrom 				= reservationResult.getDate(4);
				this.datTo 					= reservationResult.getDate(5);
				this.timFrom				= reservationResult.getTime(6);
				this.timTo					= reservationResult.getTime(7);
				this.customer 				= new Customer(this.intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				this.headCount				= reservationResult.getInt(14);
				this.invoice				= getInvoice(reservationResult.getInt(15));
				this.strStatus				= reservationResult.getString(16);
				this.employeeAssigned		= getAllAssignedEmployee(this.intReservationID);
				this.strVenue				= reservationResult.getString(17);
				this.intLocationID			= reservationResult.getInt(18);
				this.strContract			= reservationResult.getString(19);
				
				this.includedItems = new ReservationInclusion(getAllProductOrder(this.intReservationID), getAllReservedService(this.intReservationID), getAllReservedPackage(this.intReservationID), getAllReservedPromo(this.intReservationID));
				
				reservation = new Reservation(this.intReservationID, this.customer, this.includedItems, this.intReservationType, this.dateCreated, this.datFrom, this.datTo, this.timFrom, this.timTo, this.strVenue, this.intLocationID, this.headCount, this.employeeAssigned, this.invoice, this.strStatus, this.strContract);	
			}
			
			return reservation;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public Reservation getReservationByID(int reservationID){
		
		Connection con 						= jdbc.getConnection();
		String getReservations 				= "SELECT * FROM tblReservation WHERE intReservationID = ?;";
		Reservation reservation = null;
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			preReservaton.setInt(1, reservationID);
			ResultSet reservationResult = preReservaton.executeQuery();
			
			while(reservationResult.next()){
				
				this.intReservationID 		= reservationResult.getInt(1);
				this.intReservationType 	= reservationResult.getInt(2);
				this.dateCreated 			= reservationResult.getDate(3);
				this.datFrom 				= reservationResult.getDate(4);
				this.datTo 					= reservationResult.getDate(5);
				this.timFrom				= reservationResult.getTime(6);
				this.timTo					= reservationResult.getTime(7);
				this.customer 				= new Customer(this.intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				this.headCount				= reservationResult.getInt(14);
				this.invoice				= getInvoice(reservationResult.getInt(15));
				this.strStatus				= reservationResult.getString(16);
				this.employeeAssigned		= getAllAssignedEmployee(this.intReservationID);
				this.strVenue				= reservationResult.getString(17);
				this.intLocationID			= reservationResult.getInt(18);
				this.strContract			= reservationResult.getString(19);
				
				this.includedItems = new ReservationInclusion(getAllProductOrder(this.intReservationID), getAllReservedService(this.intReservationID), getAllReservedPackage(this.intReservationID), getAllReservedPromo(this.intReservationID));
				
				reservation = new Reservation(this.intReservationID, this.customer, this.includedItems, this.intReservationType, this.dateCreated, this.datFrom, this.datTo, this.timFrom, this.timTo, this.strVenue, this.intLocationID, this.headCount, this.employeeAssigned, this.invoice, this.strStatus, this.strContract);	
				List<ProductOrder> orders = getAllProductOrder(intReservationID);
				List<ReservedService> services = getAllReservedService(intReservationID);
				List<ReservedPackage> packages = getAllReservedPackage(intReservationID);
				List<ReservedPromo> promos = getAllReservedPromo(intReservationID);
			}
			
			return reservation;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Reservation> getAllReservation() {
		 
		Connection con 						= jdbc.getConnection();
		String getReservations 				= "SELECT * FROM tblReservation";
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			
			ResultSet reservationResult = preReservaton.executeQuery();
			
			while(reservationResult.next()){
				
				this.intReservationID 		= reservationResult.getInt(1);
				this.intReservationType 	= reservationResult.getInt(2);
				this.dateCreated 			= reservationResult.getDate(3);
				this.datFrom 				= reservationResult.getDate(4);
				this.datTo 					= reservationResult.getDate(5);
				this.timFrom				= reservationResult.getTime(6);
				this.timTo					= reservationResult.getTime(7);
				this.customer 				= new Customer(this.intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				this.headCount				= reservationResult.getInt(14);
				this.invoice				= getInvoice(reservationResult.getInt(15));
				this.strStatus				= reservationResult.getString(16);
				this.employeeAssigned		= getAllAssignedEmployee(this.intReservationID);
				this.strVenue				= reservationResult.getString(17);
				this.intLocationID			= reservationResult.getInt(18);
				this.strContract			= reservationResult.getString(19);
				
				this.includedItems = new ReservationInclusion(getAllProductOrder(this.intReservationID), getAllReservedService(this.intReservationID), getAllReservedPackage(this.intReservationID), getAllReservedPromo(this.intReservationID));
				
				Reservation reservation = new Reservation(this.intReservationID, this.customer, this.includedItems, this.intReservationType, this.dateCreated, this.datFrom, this.datTo, this.timFrom, this.timTo, this.strVenue, this.intLocationID, this.headCount, this.employeeAssigned, this.invoice, this.strStatus, this.strContract);	
				reservationList.add(reservation);	
			}
			
			return reservationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public int createReservation(Reservation reservation) throws SQLException {
		
		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);
		
		String createReservation				= "CALL createReservation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String createProduct					= "CALL createProductReservation(?, ?, ?, ?, ?);";
		String createService					= "CALL createServiceReservation(?, ?, ?, ?, ?);";
		String createPackage					= "CALL createPackageReservation(?, ?, ?, ?, ?);";
		String createPromo						= "CALL createPromoReservation(?, ?, ?, ?, ?);";
		String createExtra						= "CALL createInvoiceExtraCharge(?, ?);";
		String createDiscount					= "CALL createInvoiceDiscount(?, ?);";
		String createEmployee					= "CALL createReservationEmployeeAssigned(?, ?);";
		
		try{
			PreparedStatement preReservation = con.prepareStatement(createReservation);
			PreparedStatement preProduct = con.prepareStatement(createProduct);
			PreparedStatement preService = con.prepareStatement(createService);
			PreparedStatement prePackage = con.prepareStatement(createPackage);
			PreparedStatement prePromo = con.prepareStatement(createPromo);
			PreparedStatement preExtra = con.prepareStatement(createExtra);
			PreparedStatement preDiscount = con.prepareStatement(createDiscount);
			PreparedStatement preEmployee = con.prepareStatement(createEmployee);
			
			ResultSet reservationResult;
			
			//reservation insertion
			
			preReservation.setInt(1, reservation.getIntReservationType());
			preReservation.setDate(2, new java.sql.Date(reservation.getDatFrom().getTime()));
			preReservation.setDate(3, new java.sql.Date(reservation.getDatTo().getTime()));
			preReservation.setTime(4, reservation.getTimFrom());
			preReservation.setTime(5, reservation.getTimTo());
			preReservation.setString(6, reservation.getCustomer().getStrName());
			preReservation.setString(7, reservation.getCustomer().getStrCustomerType());
			preReservation.setString(8, reservation.getCustomer().getStrCompanyName());
			preReservation.setString(9, reservation.getCustomer().getStrAddress());
			preReservation.setString(10, reservation.getCustomer().getStrContactNo());
			preReservation.setString(11, reservation.getCustomer().getStrEmail());
			preReservation.setInt(12, reservation.getHeadCount());
			preReservation.setString(13, reservation.getStrVenue());
			preReservation.setInt(14, reservation.getIntLocation());
			preReservation.setString(15, reservation.getStrStatus());
			preReservation.setDouble(16, reservation.getInvoice().getDblTotalPrice());
			preReservation.setString(17, reservation.getInvoice().getPaymentType());
			preReservation.setString(18, reservation.getStrContract());
			preReservation.setString(19, reservation.getInvoice().getReceipt());
			
			reservationResult = preReservation.executeQuery();
			
			while(reservationResult.next()){
				intReservationID = reservationResult.getInt(1);
				intInvoiceID = reservationResult.getInt(2);
			}
			
			//products
			
			for(int i = 0; i < reservation.getIncludedItems().getProductList().size(); i++){
				preProduct.setInt(1, intReservationID);
				preProduct.setInt(2, reservation.getIncludedItems().getProductList().get(i).getProduct().getIntProductID());
				preProduct.setInt(3, reservation.getIncludedItems().getProductList().get(i).getIntQuantity());
				preProduct.setString(4, reservation.getIncludedItems().getProductList().get(i).getDiscountType());
				preProduct.setDouble(5, reservation.getIncludedItems().getProductList().get(i).getDiscountAmount());
				preProduct.addBatch();
			}
						
			//services
			
			for(int i = 0; i < reservation.getIncludedItems().getServiceList().size(); i++){
				preService.setInt(1, intReservationID);
				preService.setInt(2, reservation.getIncludedItems().getServiceList().get(i).getService().getIntServiceID());
				preService.setInt(3, reservation.getIncludedItems().getServiceList().get(i).getIntQuantity());
				preService.setString(4, reservation.getIncludedItems().getServiceList().get(i).getDiscountType());
				preService.setDouble(5, reservation.getIncludedItems().getServiceList().get(i).getDiscountAmount());
				preService.addBatch();
			}
			
			//packages
			
			for(int i = 0; i < reservation.getIncludedItems().getPackageList().size(); i++){
				prePackage.setInt(1, intReservationID);
				prePackage.setInt(2, reservation.getIncludedItems().getPackageList().get(i).getPackages().getIntPackageID());
				prePackage.setInt(3, reservation.getIncludedItems().getPackageList().get(i).getIntQuantity());
				prePackage.setString(4, reservation.getIncludedItems().getPackageList().get(i).getDiscountType());
				prePackage.setDouble(5, reservation.getIncludedItems().getPackageList().get(i).getDiscountAmount());
				prePackage.addBatch();
			}
			
			//promos
			
			for(int i = 0; i < reservation.getIncludedItems().getPromoList().size(); i++){
				prePromo.setInt(1, intReservationID);
				prePromo.setInt(2, reservation.getIncludedItems().getPromoList().get(i).getPromo().getIntPromoID());
				prePromo.setInt(3, reservation.getIncludedItems().getPromoList().get(i).getIntQuantity());
				prePromo.setString(4, reservation.getIncludedItems().getPromoList().get(i).getDiscountType());
				prePromo.setDouble(5, reservation.getIncludedItems().getPromoList().get(i).getDiscountAmount());
				prePromo.addBatch();
			}
			
			//discounts
			
			for(int i = 0; i < reservation.getInvoice().getDiscountList().size(); i++){
				preDiscount.setInt(1, intInvoiceID);
				preDiscount.setInt(2, reservation.getInvoice().getDiscountList().get(i).getIntDiscountID());
				preDiscount.addBatch();
			}
			
			//extracharges
			
			for(int i = 0; i < reservation.getInvoice().getExtraChargeList().size(); i++){
				preExtra.setInt(1, intInvoiceID);
				preExtra.setInt(2, reservation.getInvoice().getExtraChargeList().get(i).getIntECID());
				preExtra.addBatch();
			}
			
			//assigned employees
			
			for(int i = 0; i < reservation.getEmployeeAssigned().size(); i++){
				preEmployee.setInt(1, intReservationID);
				preEmployee.setInt(2, reservation.getEmployeeAssigned().get(i).getEmployeeAssigned().getIntEmpID());
				preEmployee.addBatch();
			}
			
			if(reservation.getIntReservationType() == 2){
				ReservationUpdateStock.updateStock(ReservationUpdateStock.getProducts(reservation));
			}
			
			
			preProduct.executeBatch();
			preService.executeBatch();
			prePackage.executeBatch();
			prePromo.executeBatch();
			preDiscount.executeBatch();
			preExtra.executeBatch();
			preEmployee.executeBatch();
			
			preProduct.close();
			preService.close();
			prePackage.close();
			prePromo.close();
			preDiscount.close();
			preExtra.close();
			preEmployee.close();
			reservationResult.close();
			
			String updateStock						= "CALL updateStock(?, ?);";
			PreparedStatement updateProducts	= con.prepareStatement(updateStock);
			
			for(int index = 0; index < reservation.getIncludedItems().getProductList().size(); index++){
				
				ProductOrder product = reservation.getIncludedItems().getProductList().get(index);
				
				updateProducts.setInt(1, product.getProduct().getIntProductID());
				updateProducts.setInt(2, product.getIntQuantity());
				updateProducts.addBatch();
			}
			
			updateProducts.executeBatch();
			updateProducts.close();
			
			con.commit();
			con.close();
			return intReservationID;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return 0;
		}
	}

	@Override
	public boolean updateReservation(Reservation reservation) throws SQLException {
		
		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);
		String updateReservation 				= "CALL updateReservation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		String removeProducts					= "DELETE FROM tblProductPurchaseReservation WHERE intReservationID = ?;";
		String removeServices					= "DELETE FROM tblServiceReservation WHERE intReservationID = ?;";
		String removePackages					= "DELETE FROM tblPackageReservation WHERE intReservationID = ?;";
		String removePromos						= "DELETE FROM tblPromoReservation WHERE intReservationID = ?;";
		String removeEmployees					= "DELETE FROM tblReservationAssignedEmployee WHERE intReservationID = ?;";
		String removeDiscounts					= "DELETE FROM tblInvoiceDiscount WHERE	intInvoiceID = ?;";
		String removeExtraCharges				= "DELETE FROM tblInvoiceExtraCharge WHERE intExtraChargeID = ?;";
		String createProduct					= "CALL createProductReservation(?, ?, ?, ?, ?);";
		String createService					= "CALL createServiceReservation(?, ?, ?, ?, ?);";
		String createPackage					= "CALL createPackageReservation(?, ?, ?, ?, ?);";
		String createPromo						= "CALL createPromoReservation(?, ?, ?, ?, ?);";
		String createExtra						= "CALL createInvoiceExtraCharge(?, ?);";
		String createDiscount					= "CALL createInvoiceDiscount(?, ?);";
		String createEmployee					= "CALL createReservationEmployeeAssigned(?, ?);";
		
		try{
			
			this.intReservationID = reservation.getIntReservationID();
			this.intInvoiceID = reservation.getInvoice().getIntInvoiceID();
			
			PreparedStatement updReservation = con.prepareStatement(updateReservation);
			PreparedStatement updProducts = con.prepareStatement(removeProducts);
			PreparedStatement updServices = con.prepareStatement(removeServices);
			PreparedStatement updPackages = con.prepareStatement(removePackages);
			PreparedStatement updPromos = con.prepareStatement(removePromos);
			PreparedStatement updEmployees = con.prepareStatement(removeEmployees);
			PreparedStatement updDiscounts = con.prepareStatement(removeDiscounts);
			PreparedStatement updExtraCharges = con.prepareStatement(removeExtraCharges);
			
			updReservation.setInt(1, this.intReservationID);
			updReservation.setInt(2, reservation.getIntReservationType());
			updReservation.setDate(3, new java.sql.Date(reservation.getDatFrom().getTime()));
			updReservation.setDate(4, new java.sql.Date(reservation.getDatTo().getTime()));
			updReservation.setTime(5, reservation.getTimFrom());
			updReservation.setTime(6, reservation.getTimTo());
			updReservation.setString(7, reservation.getCustomer().getStrName());
			updReservation.setString(8, reservation.getCustomer().getStrCustomerType());
			updReservation.setString(9, reservation.getCustomer().getStrCompanyName());
			updReservation.setString(10, reservation.getCustomer().getStrAddress());
			updReservation.setString(11, reservation.getCustomer().getStrContactNo());
			updReservation.setString(12, reservation.getCustomer().getStrEmail());
			updReservation.setInt(13, reservation.getHeadCount());
			updReservation.setString(14, reservation.getStrVenue());
			updReservation.setInt(15, reservation.getIntLocation());
			updReservation.setString(16, reservation.getStrContract());
			updReservation.setString(17, reservation.getInvoice().getPaymentType());
			updReservation.setString(18, reservation.getInvoice().getReceipt());
			updReservation.setDouble(19, reservation.getInvoice().getDblTotalPrice());
			
			updReservation.execute();
			updReservation.close();
			
			updProducts.setInt(1, this.intReservationID);
			updServices.setInt(1, this.intReservationID);
			updPackages.setInt(1, this.intReservationID);
			updPromos.setInt(1, this.intReservationID);
			updEmployees.setInt(1, this.intReservationID);
			updDiscounts.setInt(1, this.intInvoiceID);
			updExtraCharges.setInt(1, this.intInvoiceID);
			
			updProducts.execute();
			updServices.execute();
			updPackages.execute();
			updPromos.execute();
			updEmployees.execute();
			updDiscounts.execute();
			updExtraCharges.execute();
			
			updProducts.close();
			updServices.close();
			updPackages.close();
			updPromos.close();
			updEmployees.close();
			updDiscounts.close();
			updExtraCharges.close();
			
			PreparedStatement preProduct = con.prepareStatement(createProduct);
			PreparedStatement preService = con.prepareStatement(createService);
			PreparedStatement prePackage = con.prepareStatement(createPackage);
			PreparedStatement prePromo = con.prepareStatement(createPromo);
			PreparedStatement preExtra = con.prepareStatement(createExtra);
			PreparedStatement preDiscount = con.prepareStatement(createDiscount);
			PreparedStatement preEmployee = con.prepareStatement(createEmployee);
						
			//products
			
			for(int i = 0; i < reservation.getIncludedItems().getProductList().size(); i++){
				preProduct.setInt(1, intReservationID);
				preProduct.setInt(2, reservation.getIncludedItems().getProductList().get(i).getProduct().getIntProductID());
				preProduct.setInt(3, reservation.getIncludedItems().getProductList().get(i).getIntQuantity());
				preProduct.setString(4, reservation.getIncludedItems().getProductList().get(i).getDiscountType());
				preProduct.setDouble(5, reservation.getIncludedItems().getProductList().get(i).getDiscountAmount());
				preProduct.addBatch();
			}
						
			//services
			
			for(int i = 0; i < reservation.getIncludedItems().getServiceList().size(); i++){
				preService.setInt(1, intReservationID);
				preService.setInt(2, reservation.getIncludedItems().getServiceList().get(i).getService().getIntServiceID());
				preService.setInt(3, reservation.getIncludedItems().getServiceList().get(i).getIntQuantity());
				preService.setString(4, reservation.getIncludedItems().getServiceList().get(i).getDiscountType());
				preService.setDouble(5, reservation.getIncludedItems().getServiceList().get(i).getDiscountAmount());
				preService.addBatch();
			}
			
			//packages
			
			for(int i = 0; i < reservation.getIncludedItems().getPackageList().size(); i++){
				prePackage.setInt(1, intReservationID);
				prePackage.setInt(2, reservation.getIncludedItems().getPackageList().get(i).getPackages().getIntPackageID());
				prePackage.setInt(3, reservation.getIncludedItems().getPackageList().get(i).getIntQuantity());
				prePackage.setString(4, reservation.getIncludedItems().getPackageList().get(i).getDiscountType());
				prePackage.setDouble(5, reservation.getIncludedItems().getPackageList().get(i).getDiscountAmount());
				prePackage.addBatch();
			}
			
			//promos
			
			for(int i = 0; i < reservation.getIncludedItems().getPromoList().size(); i++){
				prePromo.setInt(1, intReservationID);
				prePromo.setInt(2, reservation.getIncludedItems().getPromoList().get(i).getPromo().getIntPromoID());
				prePromo.setInt(3, reservation.getIncludedItems().getPromoList().get(i).getIntQuantity());
				prePromo.setString(4, reservation.getIncludedItems().getPromoList().get(i).getDiscountType());
				prePromo.setDouble(5, reservation.getIncludedItems().getPromoList().get(i).getDiscountAmount());
				prePromo.addBatch();
			}
			
			//discounts
			
			for(int i = 0; i < reservation.getInvoice().getDiscountList().size(); i++){
				preDiscount.setInt(1, intInvoiceID);
				preDiscount.setInt(2, reservation.getInvoice().getDiscountList().get(i).getIntDiscountID());
				preDiscount.addBatch();
			}
			
			//extracharges
			
			for(int i = 0; i < reservation.getInvoice().getExtraChargeList().size(); i++){
				preExtra.setInt(1, intInvoiceID);
				preExtra.setInt(2, reservation.getInvoice().getExtraChargeList().get(i).getIntECID());
				preExtra.addBatch();
			}
			
			//assigned employees
			
			for(int i = 0; i < reservation.getEmployeeAssigned().size(); i++){
				preEmployee.setInt(1, intReservationID);
				preEmployee.setInt(2, reservation.getEmployeeAssigned().get(i).getEmployeeAssigned().getIntEmpID());
				preEmployee.addBatch();
			}
			
			if(reservation.getIntReservationType() == 2){
				ReservationUpdateStock.updateStock_increment(ReservationUpdateStock.getProducts(reservation));
				ReservationUpdateStock.updateStock(ReservationUpdateStock.getProducts(reservation));
			}
			
			preProduct.executeBatch();
			preService.executeBatch();
			prePackage.executeBatch();
			prePromo.executeBatch();
			preDiscount.executeBatch();
			preExtra.executeBatch();
			preEmployee.executeBatch();
			
			preProduct.close();
			preService.close();
			prePackage.close();
			prePromo.close();
			preDiscount.close();
			preExtra.close();
			preEmployee.close();
			
			con.commit();
			con.close();
			
//			if (updateItems(reservation) == false)
//				return false;
//			else{
//				con.commit();
//				con.close();
//				return true;
//			}get

			return true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}

	@Override
	public boolean cancelReservation(int id) throws SQLException {
		
		Connection con = new JDBCConnection().getConnection();
		
		try{
			Reservation reservation = getReservationByID(id);
			
			ReservationUpdateStock.updateStock_increment(ReservationUpdateStock.getProducts(reservation));
			
			updateReservationStatus(id, "CANCELLED");
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<EmployeeAssigned> getAllAssignedEmployee(int ReservationID) {
		
		Connection con 				= jdbc.getConnection();
		String getEmployees 		= "SELECT * FROM tblReservationAssignedEmployee WHERE intReservationID = ?;";	
		
		try{
			EmployeeService service = new EmployeeServiceImpl();
			
			List<EmployeeAssigned> empAssigned = new ArrayList<EmployeeAssigned>();
			List<Employee> employeeList = service.getAllEmployeeNoImage();
			
			PreparedStatement preEmployee = con.prepareStatement(getEmployees);
			preEmployee.setInt(1, ReservationID);
			
			ResultSet employees = preEmployee.executeQuery();
			
			while(employees.next()){
				
				int intID = employees.getInt(1);
				int intReservationID = employees.getInt(2);
				int intEmployeeID = employees.getInt(3);
				int intStatus = employees.getInt(4);
				
				Employee emp = Employee.searchEmployee(intEmployeeID, employeeList);
				EmployeeAssigned empAss = new EmployeeAssigned(intID, intReservationID, emp, intStatus);
				empAssigned.add(empAss);
			}
			return empAssigned;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ProductOrder> getAllProductOrder(int ReservationID) {

		Connection con					= jdbc.getConnection();
		String getProducts 				= "SELECT * FROM tblProductPurchaseReservation WHERE intReservationID = ?;";		
		
		try{
			
			ProductService service 		= new ProductServiceImpl();
			List<Product> productList 	= service.getAllProductsNoImage();
			
			List<ProductOrder> productOrder = new ArrayList<ProductOrder>();
			
			PreparedStatement preProduct = con.prepareStatement(getProducts);
			ResultSet products;
			
			preProduct.setInt(1, ReservationID);
			products = preProduct.executeQuery();
			
			while(products.next()){
				int intID = products.getInt(1);
				int intReservationID = products.getInt(2);
				int intProductID = products.getInt(3);
				int intQuantity = products.getInt(4);
				String discountType = products.getString(5);
				double discountAmount = products.getDouble(6);
				int intStatus = products.getInt(7);
				
				ProductOrder order = new ProductOrder(intID, new SearchProduct().search(intProductID, productList), intQuantity, intStatus);
				order.setDiscountType(discountType);
				order.setDiscountAmount(discountAmount);
				
				productOrder.add((order));
				
			}

			return productOrder;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReservedService> getAllReservedService(int ReservationID) {
		
		Connection con					= jdbc.getConnection();
		String getServices 				= "SELECT * FROM tblServiceReservation WHERE intReservationID = ?;";		
		
		try{
			
			ServiceService service 		= new ServiceServiceImpl();
			List<Service> serviceList 	= service.getAllServiceNoImage();
			
			List<ReservedService> reservedService = new ArrayList<ReservedService>();
			
			PreparedStatement preService = con.prepareStatement(getServices);
			ResultSet services;
			
			preService.setInt(1, ReservationID);
			services = preService.executeQuery();
			
			while(services.next()){
				int intID = services.getInt(1);
				int intReservationID = services.getInt(2);
				int intServiceID = services.getInt(3);
				int intQuantity = services.getInt(4);
				String discountType = services.getString(5);
				double discountAmount = services.getDouble(6);
				int intStatus = services.getInt(7);
				
				ReservedService reserve = new ReservedService(intID, intReservationID, new SearchService().search(intServiceID, serviceList), intQuantity, intStatus);
				reserve.setDiscountType(discountType);
				reserve.setDiscountAmount(discountAmount);
				
				reservedService.add((reserve));
			}
			
			return reservedService;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReservedPackage> getAllReservedPackage(int ReservationID) {

		Connection con					= jdbc.getConnection();
		String getPackages 				= "SELECT * FROM tblPackageReservation WHERE intReservationID = ?;";		
		
		try{
			
			PackageService service 		= new PackageServiceImpl();
			List<Package> packageList 	= service.getAllPackageNoDetails();
			
			List<ReservedPackage> reservedPackage = new ArrayList<ReservedPackage>();
			
			PreparedStatement prePackage = con.prepareStatement(getPackages);
			ResultSet packages;
			
			prePackage.setInt(1, ReservationID);
			packages = prePackage.executeQuery();
			
			while(packages.next()){
				int intID = packages.getInt(1);
				int intReservationID = packages.getInt(2);
				int intPackageID = packages.getInt(3);
				int intQuantity = packages.getInt(4);
				String discountType = packages.getString(5);
				double discountAmount = packages.getDouble(6);
				int intStatus = packages.getInt(7);
				
				ReservedPackage packagee = new ReservedPackage(intID, intReservationID, new SearchPackage().search(intPackageID, packageList), intQuantity, intStatus);
				packagee.setDiscountType(discountType);
				packagee.setDiscountAmount(discountAmount);
				
//				packagee = ItemDiscount.getPackageDiscount(packagee);
	
				reservedPackage.add(packagee);
			}
			
			this.packageList = reservedPackage;
			return reservedPackage;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ReservedPromo> getAllReservedPromo(int ReservationID) {
		
		Connection con					= jdbc.getConnection();
		String getPromos 				= "SELECT * FROM tblPromoReservation WHERE intReservationID = ?;";		
		
		try{
			
			PromoService service 		= new PromoServiceImpl();
			List<Promo> promoList 	= service.getAllPromoNoDetails();
			
			List<ReservedPromo> reservedPromo = new ArrayList<ReservedPromo>();
			
			PreparedStatement prePromo = con.prepareStatement(getPromos);
			ResultSet promos;
			
			prePromo.setInt(1, ReservationID);
			promos = prePromo.executeQuery();
			
			while(promos.next()){
				int intID = promos.getInt(1);
				int intReservationID = promos.getInt(2);
				int intPackageID = promos.getInt(3);
				int intQuantity = promos.getInt(4);
				String discountType = promos.getString(5);
				double discountAmount = promos.getDouble(6);
				int intStatus = promos.getInt(7);
				
				ReservedPromo promo = new ReservedPromo(intID, intReservationID, new SearchPromo().search(intPackageID, promoList), intQuantity, intStatus);
				
				promo.setDiscountType(discountType);
				promo.setDiscountAmount(discountAmount);
				
				reservedPromo.add(promo);
			}
			
			this.promoList = reservedPromo;
			return reservedPromo;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean updateItems(Reservation reservation) throws SQLException{

		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);
	
		String createProduct					= "CALL createProductReservation(?, ?, ?, ?, ?);";
		String createService					= "CALL createServiceReservation(?, ?, ?, ?, ?);";
		String createPackage					= "CALL createPackageReservation(?, ?, ?, ?, ?);";
		String createPromo						= "CALL createPromoReservation(?, ?, ?, ?, ?);";
		String createExtra						= "CALL createInvoiceExtraCharge(?, ?);";
		String createDiscount					= "CALL createInvoiceDiscount(?, ?);";
		String createEmployee					= "CALL createReservationEmployeeAssigned(?, ?);";
		
		try{
			PreparedStatement preProduct = con.prepareStatement(createProduct);
			PreparedStatement preService = con.prepareStatement(createService);
			PreparedStatement prePackage = con.prepareStatement(createPackage);
			PreparedStatement prePromo = con.prepareStatement(createPromo);
			PreparedStatement preExtra = con.prepareStatement(createExtra);
			PreparedStatement preDiscount = con.prepareStatement(createDiscount);
			PreparedStatement preEmployee = con.prepareStatement(createEmployee);
						
			//products
			
			for(int i = 0; i < reservation.getIncludedItems().getProductList().size(); i++){
				preProduct.setInt(1, intReservationID);
				preProduct.setInt(2, reservation.getIncludedItems().getProductList().get(i).getProduct().getIntProductID());
				preProduct.setInt(3, reservation.getIncludedItems().getProductList().get(i).getIntQuantity());
				preProduct.setString(4, reservation.getIncludedItems().getProductList().get(i).getDiscountType());
				preProduct.setDouble(5, reservation.getIncludedItems().getProductList().get(i).getDiscountAmount());
				preProduct.addBatch();
			}
						
			//services
			
			for(int i = 0; i < reservation.getIncludedItems().getServiceList().size(); i++){
				preService.setInt(1, intReservationID);
				preService.setInt(2, reservation.getIncludedItems().getServiceList().get(i).getService().getIntServiceID());
				preService.setInt(3, reservation.getIncludedItems().getServiceList().get(i).getIntQuantity());
				preService.setString(4, reservation.getIncludedItems().getServiceList().get(i).getDiscountType());
				preService.setDouble(5, reservation.getIncludedItems().getServiceList().get(i).getDiscountAmount());
				preService.addBatch();
			}
			
			//packages
			
			for(int i = 0; i < reservation.getIncludedItems().getPackageList().size(); i++){
				prePackage.setInt(1, intReservationID);
				prePackage.setInt(2, reservation.getIncludedItems().getPackageList().get(i).getPackages().getIntPackageID());
				prePackage.setInt(3, reservation.getIncludedItems().getPackageList().get(i).getIntQuantity());
				prePackage.setString(4, reservation.getIncludedItems().getPackageList().get(i).getDiscountType());
				prePackage.setDouble(5, reservation.getIncludedItems().getPackageList().get(i).getDiscountAmount());
				prePackage.addBatch();
			}
			
			//promos
			
			for(int i = 0; i < reservation.getIncludedItems().getPromoList().size(); i++){
				prePromo.setInt(1, intReservationID);
				prePromo.setInt(2, reservation.getIncludedItems().getPromoList().get(i).getPromo().getIntPromoID());
				prePromo.setInt(3, reservation.getIncludedItems().getPromoList().get(i).getIntQuantity());
				prePromo.setString(4, reservation.getIncludedItems().getPromoList().get(i).getDiscountType());
				prePromo.setDouble(5, reservation.getIncludedItems().getPromoList().get(i).getDiscountAmount());
				prePromo.addBatch();
			}
			
			//discounts
			
			for(int i = 0; i < reservation.getInvoice().getDiscountList().size(); i++){
				preDiscount.setInt(1, intInvoiceID);
				preDiscount.setInt(2, reservation.getInvoice().getDiscountList().get(i).getIntDiscountID());
				preDiscount.addBatch();
			}
			
			//extracharges
			
			for(int i = 0; i < reservation.getInvoice().getExtraChargeList().size(); i++){
				preExtra.setInt(1, intInvoiceID);
				preExtra.setInt(2, reservation.getInvoice().getExtraChargeList().get(i).getIntECID());
				preExtra.addBatch();
			}
			
			//assigned employees
			
			for(int i = 0; i < reservation.getEmployeeAssigned().size(); i++){
				preEmployee.setInt(1, intReservationID);
				preEmployee.setInt(2, reservation.getEmployeeAssigned().get(i).getEmployeeAssigned().getIntEmpID());
				preEmployee.addBatch();
			}
			
			preProduct.executeBatch();
			preService.executeBatch();
			prePackage.executeBatch();
			prePromo.executeBatch();
			preDiscount.executeBatch();
			preExtra.executeBatch();
			preEmployee.executeBatch();
			
			preProduct.close();
			preService.close();
			prePackage.close();
			prePromo.close();
			preDiscount.close();
			preExtra.close();
			preEmployee.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			con.rollback();
			return false;
		}
	}

	@Override
	public Invoice getInvoice(int intInvoiceID) {
		
		Connection con = jdbc.getConnection();
		String getInvoice 					= "SELECT * FROM tblInvoice WHERE intInvoiceID = ?;";
		String getDiscount 					= "SELECT * FROM tblInvoiceDiscount WHERE intInvoiceID = ?;";
		String getExtraCharge 				= "SELECT * FROM tblInvoiceExtraCharge WHERE intInvoiceID = ?;";
		String getPayment					= "SELECT * FROM tblPayment WHERE intInvoiceID = ?;";
		
		DiscountService discountService = new DiscountServiceImpl();
		ExtraChargeService extraService = new ExtraChargeServiceImpl();
		
		int payment = 0;
		Date date = null;
		String paymentType = null;
		String receipt = null;
		double totalBalance = 0;
		double paymentAmount = 0;
		
		try{
			
			PreparedStatement preInvoice 		= con.prepareStatement(getInvoice);
			PreparedStatement preDiscount		= con.prepareStatement(getDiscount);
			PreparedStatement preExtraCharge	= con.prepareStatement(getExtraCharge);
			PreparedStatement prePayment		= con.prepareStatement(getPayment);
			
			ResultSet discountSet;
			ResultSet extraChargeSet;
			ResultSet paymentSet;
			
			preInvoice.setInt(1, intInvoiceID);
			ResultSet invoiceSet = preInvoice.executeQuery();
			
			List<Discount> discountList = new ArrayList<Discount>();
			List<ExtraCharge> extraChargeList = new ArrayList<ExtraCharge>();
			
			List<Discount> savedDiscounts = discountService.getAllDiscount();
			List<ExtraCharge> savedExtraCharge = extraService.getAllExtraCharges();
			
			List<Payment> paymentList = new ArrayList<Payment>();
			
			while(invoiceSet.next()){
				
				date = invoiceSet.getDate(2);
				totalBalance = invoiceSet.getDouble(3);
				paymentType = invoiceSet.getString(4);
				receipt = invoiceSet.getString(6);
	
				preDiscount.setInt(1, intInvoiceID);
				discountSet = preDiscount.executeQuery();
				
				while(discountSet.next()){
					int intID 				= discountSet.getInt(1);
					int invoice 			= discountSet.getInt(2);
					int intDiscountID 		= discountSet.getInt(3);
					
					Discount discount = Discount.searchDiscount(intDiscountID, savedDiscounts);
					discountList.add(discount);
				}
				
				preExtraCharge.setInt(1, intInvoiceID);
				extraChargeSet = preExtraCharge.executeQuery();
				
				while(extraChargeSet.next()){
					int intID 			= extraChargeSet.getInt(1);
					int invoice			= extraChargeSet.getInt(2);
					int intExtraID 		= extraChargeSet.getInt(3);
					
					ExtraCharge extra = ExtraCharge.searchExtraCharge(intExtraID, savedExtraCharge);
					extraChargeList.add(extra);
				}
				
				prePayment.setInt(1, intInvoiceID);
				paymentSet = prePayment.executeQuery();
				
				while(paymentSet.next()){
					int intID 				= paymentSet.getInt(1);
					int invoice		 		= paymentSet.getInt(2);
					String strPaymentType 	= paymentSet.getString(3);
					paymentAmount			= paymentSet.getDouble(4);
					Date dateOfPayment		= paymentSet.getDate(5);
			
					Payment extra = new Payment(intID, invoice,"reservation" , paymentAmount, strPaymentType, dateOfPayment);
					
					paymentList.add(extra);
				}
			}
			
			double remainingBalance = Invoice.getRemainingBalance(totalBalance, paymentList);
			
			
			Invoice invoice = new Invoice(intInvoiceID, date, discountList, extraChargeList, totalBalance, remainingBalance, paymentType, paymentList, Invoice.convertToString(payment), receipt);
			
			return invoice;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Reservation> getAllReservationNoDetails() {
		Connection con 						= jdbc.getConnection();
		String getReservations 				= "CALL getUnpaidReservation()";
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			
			ResultSet reservationResult = preReservaton.executeQuery();
			
			while(reservationResult.next()){
				
				this.intReservationID 		= reservationResult.getInt(1);
				this.intReservationType 	= reservationResult.getInt(2);
				this.dateCreated 			= reservationResult.getDate(3);
				this.datFrom 				= reservationResult.getDate(4);
				this.datTo 					= reservationResult.getDate(5);
				this.timFrom				= reservationResult.getTime(6);
				this.timTo					= reservationResult.getTime(7);
				this.customer 				= new Customer(this.intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				this.headCount				= reservationResult.getInt(14);
				this.invoice				= getInvoice(reservationResult.getInt(15));
				this.strStatus				= reservationResult.getString(16);
				this.employeeAssigned		= getAllAssignedEmployee(this.intReservationID);
				this.strVenue				= reservationResult.getString(17);
				this.intLocationID			= reservationResult.getInt(18);
				this.strContract			= reservationResult.getString(19);
				
				this.includedItems = new ReservationInclusion(getAllProductOrder(this.intReservationID), getAllReservedService(this.intReservationID), getAllReservedPackage(this.intReservationID), getAllReservedPromo(this.intReservationID));
				
				Reservation reservation = new Reservation(this.intReservationID, this.customer, this.includedItems, this.intReservationType, this.dateCreated, this.datFrom, this.datTo, this.timFrom, this.timTo, this.strVenue, this.intLocationID, this.headCount, this.employeeAssigned, this.invoice, this.strStatus, this.strContract);	
				
				System.out.println(reservationResult.getInt(1));
				reservationList.add(reservation);
			}
			
			return reservationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Invoice getInvoiceNoDetails(int intInvoice){
		
		Connection con = jdbc.getConnection();
		String getInvoice 					= "SELECT * FROM tblInvoice WHERE intInvoiceID = ?;";
		String getPayment					= "SELECT * FROM tblPayment WHERE intInvoiceID = ?;";
		
		int payment = 0;
		Date date = null;
		String paymentType = null;
		String receipt = null;
		double totalBalance = 0;
		double paymentAmount = 0;
		
		try{
			
			PreparedStatement preInvoice 		= con.prepareStatement(getInvoice);
			PreparedStatement prePayment		= con.prepareStatement(getPayment);
			
			ResultSet discountSet;
			ResultSet extraChargeSet;
			ResultSet paymentSet;
			
			preInvoice.setInt(1, intInvoice);
			ResultSet invoiceSet = preInvoice.executeQuery();
			
			List<Discount> discountList = new ArrayList<Discount>();
			List<ExtraCharge> extraChargeList = new ArrayList<ExtraCharge>();
			
			List<Payment> paymentList = new ArrayList<Payment>();
			
			while(invoiceSet.next()){
				
				date = invoiceSet.getDate(2);
				totalBalance = invoiceSet.getInt(3);
				paymentType = invoiceSet.getString(4);
				receipt = invoiceSet.getString(6);
				
				prePayment.setInt(1, intInvoice);
				paymentSet = prePayment.executeQuery();
				
				while(paymentSet.next()){
					int intID 				= paymentSet.getInt(1);
					int invoice		 		= paymentSet.getInt(2);
					String strPaymentType	= paymentSet.getString(3);
					paymentAmount			= paymentSet.getDouble(4);
					Date dateOfPayment		= paymentSet.getDate(5);
			
					Payment extra = new Payment(intID, invoice, "reservation", paymentAmount, strPaymentType, dateOfPayment);
					
					paymentList.add(extra);
				}
			}
			
			double remainingBalance = Invoice.getRemainingBalance(totalBalance, paymentList);
			
			
			Invoice invoice = new Invoice(intInvoice, date, discountList, extraChargeList, totalBalance, remainingBalance, paymentType, paymentList, Invoice.convertToString(payment), receipt);
			
			return invoice;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateReservationStatus(int intReservationID2, String status) {
		

		Connection con = new JDBCConnection().getConnection();
		
		String query = "UPDATE tblReservation SET strStatus = ? WHERE intReservationID = ?;";
		
		try{
			PreparedStatement statemen = con.prepareStatement(query);
			statemen.setString(1, status);
			statemen.setInt(2, intReservationID2);
			statemen.execute();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Reservation> getCancelledReservation(ReportDate date){
		
		Connection con 						= new JDBCConnection().getConnection();
		String getReservations 				= "CALL getCancelledReservationTabular(?, ?);";
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			preReservaton.setString(1, date.getDateFrom());
			preReservaton.setString(2, date.getDateTo());
			
			ResultSet reservationResult = preReservaton.executeQuery();
			
			while(reservationResult.next()){
				
				this.intReservationID 		= reservationResult.getInt(1);
				this.intReservationType 	= reservationResult.getInt(2);
				this.dateCreated 			= reservationResult.getDate(3);
				this.datFrom 				= reservationResult.getDate(4);
				this.datTo 					= reservationResult.getDate(5);
				this.timFrom				= reservationResult.getTime(6);
				this.timTo					= reservationResult.getTime(7);
				this.customer 				= new Customer(this.intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				this.headCount				= reservationResult.getInt(14);
				this.invoice				= getInvoice(reservationResult.getInt(15));
				this.strStatus				= reservationResult.getString(16);
				this.employeeAssigned		= getAllAssignedEmployee(this.intReservationID);
				this.strVenue				= reservationResult.getString(17);
				this.intLocationID			= reservationResult.getInt(18);
				this.strContract			= reservationResult.getString(19);
				
				this.includedItems = new ReservationInclusion(getAllProductOrder(this.intReservationID), getAllReservedService(this.intReservationID), getAllReservedPackage(this.intReservationID), getAllReservedPromo(this.intReservationID));
				
				Reservation reservation = new Reservation(this.intReservationID, this.customer, this.includedItems, this.intReservationType, this.dateCreated, this.datFrom, this.datTo, this.timFrom, this.timTo, this.strVenue, this.intLocationID, this.headCount, this.employeeAssigned, this.invoice, this.strStatus, this.strContract);	

				reservationList.add(reservation);
			}
			
			return reservationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static int getReservationID(int intInvoiceID) {
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "SELECT intReservationID FROM tblReservation WHERE intInvoiceID = ?;";
		
		try {
			
			int reservationID = 0;
			
			PreparedStatement get = con.prepareStatement(query);
			get.setInt(1, intInvoiceID);
			
			ResultSet set = get.executeQuery();
			
			while(set.next()) {
				reservationID = set.getInt(1);
			}
			
			get.close();
			set.close();
			con.close();
			
			return reservationID;
			
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
 	}

}
