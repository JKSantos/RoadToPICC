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
import com.gss.model.PromoWalkIn;
import com.gss.model.Service;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;

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
		
		
		try{
			PreparedStatement walkinStmt = con.prepareStatement(walkinQuery);
			PreparedStatement productStmt = con.prepareStatement(productQuery);
			PreparedStatement serviceStmt = con.prepareStatement(serviceQuery);
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
}
