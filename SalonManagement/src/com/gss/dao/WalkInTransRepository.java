package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.gss.model.Employee;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductWalkIn;
import com.gss.model.Promo;
import com.gss.model.PromoWalkIn;
import com.gss.model.Service;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;
import com.gss.model.Package;

public class WalkInTransRepository {
	
	private static JDBCConnection jdbc = new JDBCConnection();

	public static WalkIn getWalkInByID(int walkinID){
		
		Connection con = jdbc.getConnection();
		
		WalkIn walkin = null;
		List<ProductWalkIn> productList 	= new ArrayList<ProductWalkIn>();
		List<ServiceWalkIn> serviceList 	= new ArrayList<ServiceWalkIn>();
		List<PackageWalkIn> packageList 	= new ArrayList<PackageWalkIn>();
		List<PromoWalkIn> promoList 		= new ArrayList<PromoWalkIn>();
		
		String walkinQuery = "SELECT * FROM tblWalkIn WHERE intWalkInID = ?;";
		String productQuery = "SELECT * FROM tblProductPurchase WHERE intWalkInID = ?;";
		String serviceQuery = "SELECT * FROM tblServiceWalkIn WHERE intWalkInID = ?;";
		String packageQuery = "SELECT * FROM tblPackageWalkIn WHERE intWalkInID = ?;";
		String packageServiceQuery = "SELECT * FROM tblAssignmentDetail WHERE intAssignmentID = ?;";
		String promoQuery = "SELECT * FROM tblPromoWalkIn WHERE intWalkInID = ?;";
		String promoServiceQuery = "SELECT * FROM tblwalkinpromoservice WHERE intPromoWalkInID = ?;";
		String promoPackageQuery = "SELECT * FROM tblpromopackagewalkin WHERE intPromoWalkInID = ?;";
		String promoPackageServiceQuery = "SELECT * FROM tblAssignmentDetail WHERE intAssignmentID = ?;";
		
		try{
			PreparedStatement walkinStmt = con.prepareStatement(walkinQuery);
			PreparedStatement productStmt = con.prepareStatement(productQuery);
			PreparedStatement serviceStmt = con.prepareStatement(serviceQuery);
			PreparedStatement packageStmt = con.prepareStatement(packageQuery);
			PreparedStatement promoStmt = con.prepareStatement(promoQuery);
			
			walkinStmt.setInt(1, walkinID);
			
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
				
				walkin = new WalkIn(id, type, name, contact1, date, serviceList, productList, packageList, promoList, invoice, null, status, invoice.getPaymentStatus());;
				walkin.setAppointmentDate(appDate);
				walkin.setAppointmentTime(appTime);
			}
			
			walkinStmt.close();
			walkinRes.close();
			
			productStmt.setInt(1, walkinID);
			ResultSet productSet = productStmt.executeQuery();
			
			while(productSet.next()){
				int id = productSet.getInt(1);
				Product product = Product.createNullProduct(productSet.getInt(3));
				int quantity = productSet.getInt(4);
				
				ProductWalkIn productW = new ProductWalkIn(id, product, quantity);
				productList.add(productW);
			}
			
			productStmt.close();
			productSet.close();
			
			serviceStmt.setInt(1, walkinID);
			ResultSet serviceSet = serviceStmt.executeQuery();
			
			while(serviceSet.next()){
				int id = serviceSet.getInt(1);
				Service service1 = Service.createNullService(serviceSet.getInt(3));
				String servicename = getServiceName(serviceSet.getInt(3));
				service1.setStrServiceName(servicename);
				Employee emp = Employee.createNullEmployee(serviceSet.getInt(4));
				List<String> name = getEmployeeName(serviceSet.getInt(4));
				emp.setStrEmpFirstName(name.get(0));
				emp.setStrEmpLastName(name.get(1));
				
				String status = serviceSet.getString(5);
			
				ServiceWalkIn service = new ServiceWalkIn(id, service1, emp, status);
				
				serviceList.add(service);
			}
			
			serviceStmt.close();
			serviceSet.close();
			
			packageStmt.setInt(1, walkinID);
			ResultSet packageSet = packageStmt.executeQuery();
			
			while(packageSet.next()){
				
				List<ServiceWalkIn> packageServices = new ArrayList<ServiceWalkIn>();
				
				int id = packageSet.getInt(1);
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
					String status = servPackSet.getString(5);
					
					ServiceWalkIn servWalkin = new ServiceWalkIn(intId, service, emp, status);
					packageServices.add(servWalkin);
				}
				
				servPackStmt.close();
				servPackSet.close();
				
				PackageWalkIn packWalkIn = new PackageWalkIn(id, pack, packageServices);
				packageList.add(packWalkIn);
			}
			
			
			packageStmt.close();
			packageSet.close();
			
			promoStmt.setInt(1, walkinID);
			ResultSet promoSet = promoStmt.executeQuery();
			
			while(promoSet.next()){
				
				int intPromoWalkInID = promoSet.getInt(1);
				int intPromoID = promoSet.getInt(3);
				
				List<ServiceWalkIn> promoServices = new ArrayList<ServiceWalkIn>();
				List<PackageWalkIn> packages = new ArrayList<PackageWalkIn>();
				
				PreparedStatement promoServiceStmt = con.prepareStatement(promoServiceQuery);
				promoServiceStmt.setInt(1, intPromoWalkInID);
				ResultSet promoServiceSet = promoServiceStmt.executeQuery();
				
				while(promoServiceSet.next()){
					int intServiceWalkInID = promoServiceSet.getInt(1);
					Service service = Service.createNullService(promoServiceSet.getInt(3));
					String serviceName = getServiceName(service.getIntServiceID());
					service.setStrServiceName(serviceName);
					Employee employeeAssigned = Employee.createNullEmployee(promoServiceSet.getInt(4));
					List<String> employeeName = getEmployeeName(employeeAssigned.getIntEmpID());
					employeeAssigned.setStrEmpFirstName(employeeName.get(0));
					employeeAssigned.setStrEmpLastName(employeeName.get(1));
					String strServiceStatus = promoServiceSet.getString(5);
					
					ServiceWalkIn serviceWalkIn = new ServiceWalkIn(intServiceWalkInID, service, employeeAssigned, strServiceStatus);
					promoServices.add(serviceWalkIn);
				}
				
				promoServiceStmt.close();
				promoServiceSet.close();
				
				PreparedStatement promoPackageStmt = con.prepareStatement(promoPackageQuery);
				promoPackageStmt.setInt(1, intPromoWalkInID);
				ResultSet promoPackageSet = promoPackageStmt.executeQuery();
				
				while(promoPackageSet.next()){
					
					List<ServiceWalkIn> packageServices = new ArrayList<ServiceWalkIn>();
					
					int id = promoPackageSet.getInt(1);
					Package pack = Package.createNullPackage(promoPackageSet.getInt(6));
					int assigmentID = promoPackageSet.getInt(4);
					
					PreparedStatement servPackStmt = con.prepareStatement(promoPackageServiceQuery);
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
						String status = servPackSet.getString(5);
						
						ServiceWalkIn servWalkin = new ServiceWalkIn(intId, service, emp, status);
						packageServices.add(servWalkin);
					}
					
					servPackStmt.close();
					servPackSet.close();
					
					PackageWalkIn packWalkIn = new PackageWalkIn(id, pack, packageServices);
					packages.add(packWalkIn);
				}
				
				promoPackageStmt.close();
				promoPackageSet.close();
				
				Promo promo = Promo.createNullPromo(intPromoID);
				PromoWalkIn promoWalkin = new PromoWalkIn(intPromoWalkInID, promo, packages, promoServices);
				promoList.add(promoWalkin);
			}
			
			promoStmt.close();
			promoSet.close();
			
			con.close();
			return walkin;
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
	
	public static int getWalkInID(int invoiceID){
		
		Connection con = jdbc.getConnection();
		
		String query = "SELECT intWalkInID FROM tblWalkIn WHERE intInvoiceID = ?;";
		
		int id = 0;
		
		try{
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, invoiceID);
			ResultSet set = statement.executeQuery();
			
			while(set.next()){
				id = set.getInt(1);
			}
			
			statement.close();
			set.close();
			con.close();
			
			return id;
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
}
