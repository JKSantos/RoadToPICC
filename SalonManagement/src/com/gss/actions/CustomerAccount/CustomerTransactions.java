package com.gss.actions.CustomerAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.dao.ProductSalesJDBCRepository;
import com.gss.dao.ReservationJDBCRepository;
import com.gss.model.Customer;
import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.EmployeeAssigned;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Package;
import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.model.ProductWalkIn;
import com.gss.model.PromoWalkIn;
import com.gss.model.Reservation;
import com.gss.model.ReservationInclusion;
import com.gss.model.Service;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;

public class CustomerTransactions {
	
	static JDBCConnection jdbc = new JDBCConnection();
	
	public static List<WalkIn> getCustomerAppointment(int customerID){
		
		Connection con = jdbc.getConnection();
		
		List<WalkIn> walkins = new ArrayList<WalkIn>();
		List<ProductWalkIn> productList 	= new ArrayList<ProductWalkIn>();
		List<ServiceWalkIn> serviceList 	= new ArrayList<ServiceWalkIn>();
		List<PackageWalkIn> packageList 	= new ArrayList<PackageWalkIn>();
		List<PromoWalkIn> promoList 		= new ArrayList<PromoWalkIn>();
		
		String walkinQuery = "CALL getCustomerAppointment(?);";
		String productQuery = "SELECT * FROM tblProductPurchase WHERE intWalkInID = ?;";
		String serviceQuery = "SELECT * FROM tblServiceWalkIn WHERE intWalkInID = ?;";
		String packageQuery = "SELECT * FROM tblPackageWalkIn WHERE intWalkInID = ?;";
		String packageServiceQuery = "SELECT * FROM tblAssignmentDetail WHERE intAssignmentID = ?;";
		
		try{
			PreparedStatement walkinStmt = con.prepareStatement(walkinQuery);
			PreparedStatement productStmt = con.prepareStatement(productQuery);
			PreparedStatement serviceStmt = con.prepareStatement(serviceQuery);
			PreparedStatement packageStmt = con.prepareStatement(packageQuery);
			
			walkinStmt.setInt(1, customerID);
			
			ResultSet walkinRes = walkinStmt.executeQuery();
			
			while(walkinRes.next()){
				int id = walkinRes.getInt(1);
				String type = walkinRes.getString(2);
				String name = walkinRes.getString(3);
				String contact1 = walkinRes.getString(4);
				Date date = walkinRes.getDate(5);
				java.sql.Date appDate = walkinRes.getDate(6);
				Time appTime = walkinRes.getTime(7);
				int invoiceId = walkinRes.getInt(8);
				String status = walkinRes.getString(9);
				
				Invoice invoice = getInvoice(invoiceId);
				
				WalkIn walkin = new WalkIn(id, type, name, contact1, date, serviceList, productList, packageList, promoList, invoice, null, status, invoice.getPaymentStatus());;
				walkin.setAppointmentDate(appDate);
				walkin.setAppointmentTime(appTime);
			
				productStmt.setInt(1, id);
				ResultSet productSet = productStmt.executeQuery();
				
				while(productSet.next()){
					int id1 = productSet.getInt(1);
					Product product = Product.createNullProduct(productSet.getInt(3));
					int quantity = productSet.getInt(4);
					
					ProductWalkIn productW = new ProductWalkIn(id1, product, quantity);
					productList.add(productW);
				}
				productSet.close();
				serviceStmt.setInt(1, id);
				ResultSet serviceSet = serviceStmt.executeQuery();
				
				while(serviceSet.next()){
					int id1 = serviceSet.getInt(1);
					Service service1 = Service.createNullService(serviceSet.getInt(3));
					String servicename = getServiceName(serviceSet.getInt(3));
					service1.setStrServiceName(servicename);
					Employee emp = Employee.createNullEmployee(serviceSet.getInt(4));
					List<String> name1 = getEmployeeName(serviceSet.getInt(4));
					emp.setStrEmpFirstName(name1.get(0));
					emp.setStrEmpLastName(name1.get(1));
					
					String status1 = serviceSet.getString(5);
				
					ServiceWalkIn service = new ServiceWalkIn(id1, service1, emp, status1);
					
					serviceList.add(service);
				}
				serviceSet.close();
				packageStmt.setInt(1, id);
				ResultSet packageSet = packageStmt.executeQuery();
				
				while(packageSet.next()){
					
					List<ServiceWalkIn> packageServices = new ArrayList<ServiceWalkIn>();
					
					int id1 = packageSet.getInt(1);
					Package pack = Package.createNullPackage(packageSet.getInt(3));
					int assigmentID = packageSet.getInt(4);
					
					PreparedStatement servPackStmt = con.prepareStatement(packageServiceQuery);
					servPackStmt.setInt(1, assigmentID);
					ResultSet servPackSet = servPackStmt.executeQuery();
					
					while(servPackSet.next()){
						int intId = servPackSet.getInt(1);
						Service service = Service.createNullService(servPackSet.getInt(4));
						String serviceName = getServiceName(servPackSet.getInt(4));
						service.setStrServiceCategory(serviceName);
						Employee emp = Employee.createNullEmployee(servPackSet.getInt(3));
						List<String> empName = getEmployeeName(servPackSet.getInt(3));
						emp.setStrEmpFirstName(empName.get(0));
						emp.setStrEmpLastName(empName.get(1));
						String status1 = servPackSet.getString(5);
						
						ServiceWalkIn servWalkin = new ServiceWalkIn(intId, service, emp, status1);
						packageServices.add(servWalkin);
					}
					servPackStmt.close();
					servPackSet.close();
					
					PackageWalkIn packWalkIn = new PackageWalkIn(id1, pack, packageServices);
					packageList.add(packWalkIn);
				}
				
				walkins.add(walkin);
			
			}
			
			productStmt.close();
			
			
			serviceStmt.close();
			
			
			walkinStmt.close();
			walkinRes.close();
			
			con.close();
			return walkins;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Invoice getInvoice(int id){
		
		Connection con = jdbc.getConnection();
		
		String query = "CALL getInvoice(?);";
		String discount = "CALL getInvoiceDiscounts(?);";
		String extraCharge = "CALL getInvoiceExtraCharge(?);";
		
		int invoiceID = 0;
		Invoice invoice = null;
		List<Discount> discountList = new ArrayList<Discount>();
		List<ExtraCharge> extraChages = new ArrayList<ExtraCharge>();
		List<Payment> paymentList = new ArrayList<Payment>();
		
		try{
			PreparedStatement invoiceStmt = con.prepareStatement(query);
			PreparedStatement discountStmt = con.prepareStatement(discount);
			PreparedStatement extraChargeStmt = con.prepareStatement(extraCharge);
			invoiceStmt.setInt(1, id);
			
			ResultSet invoiceRes = invoiceStmt.executeQuery();
			
			while(invoiceRes.next()){
				invoiceID = invoiceRes.getInt(1);
				Date date = invoiceRes.getDate(2);
				double dblTotal = invoiceRes.getDouble(3);
				double remaining = invoiceRes.getDouble(3);
				String paymentType = invoiceRes.getString(4);
				String paymentStatus = Payment.toStringStatus(invoiceRes.getInt(5));
				String receipt = invoiceRes.getString(6);
				
				if(paymentStatus.equals("PAID"))
					remaining = 0;
				
				invoice = new Invoice(invoiceID, date, discountList, extraChages, dblTotal, remaining, paymentType, paymentList, paymentStatus, receipt);
			}
			
			invoiceStmt.close();
			invoiceRes.close();
			
			discountStmt.setInt(1, invoiceID);
			ResultSet discountRes = discountStmt.executeQuery();
			
			while(discountRes.next()){
				discountList.add(Discount.createNullDiscount(discountRes.getInt(1)));
			}
			
			discountStmt.close();
			discountRes.close();
		
			extraChargeStmt.setInt(1, invoiceID);
			ResultSet extraChargeRes = extraChargeStmt.executeQuery();
			
			while(extraChargeRes.next()){
				discountList.add(Discount.createNullDiscount(extraChargeRes.getInt(1)));
			}
			
			extraChargeStmt.close();
			extraChargeRes.close();
			
			invoice.setDiscountList(discountList);
			invoice.setExtraChargeList(extraChages);
			
			con.close();
			
			return invoice;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<String> getEmployeeName(int empID){
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strEmpFirstName, strEmpLastName FROM tblEmployee WHERE intEmpID = ?;";
	
		List<String> name = new ArrayList<String>();
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, empID);
			
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				String firstname = set.getString(1);
				String lastName = set.getString(2);
				
				name.add(firstname);
				name.add(lastName);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return name;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	public static String getServiceName(int serviceID){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT strServiceName FROM tblService WHERE intServiceID = ?;";
		
		String name = "";
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, serviceID);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				name = set.getString(1);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return name;
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}

	public static List<Reservation> getAllReservation(int custID) {
		 
		Connection con 						= jdbc.getConnection();
		String getReservations 				= "CALL getCustomerReservation(?);";
		
		List<Reservation> reservationList = new ArrayList<Reservation>();
		try{
			
			PreparedStatement preReservaton = con.prepareStatement(getReservations);
			preReservaton.setInt(1, custID);
			ResultSet reservationResult = preReservaton.executeQuery();
			
			
			ReservationJDBCRepository repo = new ReservationJDBCRepository();
			
			while(reservationResult.next()){
				
				int intReservationID 		= reservationResult.getInt(1);
				int intReservationType 	= reservationResult.getInt(2);
				Date dateCreated 			= reservationResult.getDate(3);
				Date datFrom 				= reservationResult.getDate(4);
				Date datTo 					= reservationResult.getDate(5);
				Time timFrom				= reservationResult.getTime(6);
				Time timTo					= reservationResult.getTime(7);
				Customer customer 				= new Customer(intReservationID, reservationResult.getString(9), reservationResult.getString(10), reservationResult.getString(8), reservationResult.getString(11), reservationResult.getString(12), reservationResult.getString(13));
				int headCount				= reservationResult.getInt(14);
				Invoice invoice				= getInvoice(reservationResult.getInt(15));
				String strStatus				= reservationResult.getString(16);
				List<EmployeeAssigned> employeeAssigned		= repo.getAllAssignedEmployee(intReservationID);
				String strVenue				= reservationResult.getString(17);
				int intLocationID			= reservationResult.getInt(18);
				String strContract			= reservationResult.getString(19);
				
				ReservationInclusion includedItems = new ReservationInclusion(repo.getAllProductOrder(intReservationID), repo.getAllReservedService(intReservationID), repo.getAllReservedPackage(intReservationID), repo.getAllReservedPromo(intReservationID));
				
				Reservation reservation = new Reservation(intReservationID, customer, includedItems, intReservationType, dateCreated, datFrom,datTo, timFrom,timTo, strVenue, intLocationID, headCount, employeeAssigned, invoice, strStatus, strContract);	
				reservationList.add(reservation);	
			}
			
			return reservationList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}

	public static List<ProductSales> getAllProductSales() {
		Connection con = jdbc.getConnection();
		
		String getAllOrder 					= "SELECT * FROM tblOrder WHERE strOrderStatus <> 'CANCELLED' AND strOrderStatus <> 'DECLINED';";
		String getAllDet 					= "SELECT * FROM tblOrderDetails WHERE intOrderID = ?";
		
		try{
			ProductService service = new ProductServiceImpl();
			List<ProductSales> salesList 	= new ArrayList<ProductSales>();
			List<Product> productList 		= service.getAllProductsNoImage();
			
			PreparedStatement getAll 		= con.prepareStatement(getAllOrder);
			PreparedStatement getAllDetails	= con.prepareStatement(getAllDet);
			ResultSet orders				= getAll.executeQuery();
			ResultSet details;
			
			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				ProductSales salesList1;
				
				int intSalesID = orders.getInt(1);
				Date datCreated = orders.getDate(2);
				Date deliveryDate = orders.getDate(3);
				int intType = orders.getInt(4);
				String strName = orders.getString(5);
				String strAddress = orders.getString(6);
				int intLocationID = orders.getInt(7);
				String strContactNo = orders.getString(8);
				String strStatus = orders.getString(9);
				Invoice invoice = new ProductSalesJDBCRepository().getInvoice(orders.getInt(10));
				
				getAllDetails.setInt(1, intSalesID);
				details = getAllDetails.executeQuery();
				
				while(details.next()){
					
					int intID = details.getInt(1);
					int id = details.getInt(3);
					int intQuantity = details.getInt(4);
					int intStatus = details.getInt(5);
					
					for(int i = 0; i < productList.size(); i++){
						if(id == productList.get(i).getIntProductID()){
							Product product = productList.get(i);
							ProductOrder order = new ProductOrder(intID, product, intQuantity, intStatus);
							orderDetails.add(order);
						}
					}
				}
				details.close();
				
				salesList1 = new ProductSales(intSalesID, datCreated, deliveryDate, intType, strName, strAddress, intLocationID, strContactNo, orderDetails, invoice, strStatus);
				salesList.add(salesList1);
			}
			
			getAll.close();
			getAllDetails.close();
			orders.close();
			
			con.close();
			return salesList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
