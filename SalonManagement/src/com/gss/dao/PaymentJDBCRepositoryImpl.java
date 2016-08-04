package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.WalkIn;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class PaymentJDBCRepositoryImpl implements PaymentRepository{
	
	private JDBCConnection jdbc = new JDBCConnection();
	
	private List<ProductOrder> productList = new ArrayList<ProductOrder>();
	private List<ReservedService> serviceList = new ArrayList<ReservedService>();
	private List<ReservedPackage> packageList = new ArrayList<ReservedPackage>();
	private List<ReservedPromo> promoList = new ArrayList<ReservedPromo>();
	
	@Override
	public boolean createProductSalesPayment(Payment payment) throws SQLException{
		
		Connection con 							= jdbc.getConnection();
		con.setAutoCommit(false);
		
		String insertPayment 					= "CALL createOrderPayment(?, ?, ?)";
		String updateStock						= "CALL updateStock(?, ?);";
				
		ProductSales sales = ProductSales.search(payment.getIntInvoiceID(), ProductSales.getAllProductSales());
		
		try{
			PreparedStatement createPayment		= con.prepareStatement(insertPayment);
			PreparedStatement updateProducts	= con.prepareStatement(updateStock);
			
			createPayment.setInt(1, payment.getIntInvoiceID());
			createPayment.setString(2, payment.getStrPaymentType());
			createPayment.setDouble(3, payment.getDblPaymentAmount());
			
			createPayment.execute();
			
			for(int index = 0; index < sales.getProductList().size(); index++){
				
				ProductOrder product = sales.getProductList().get(index);
				
				updateProducts.setInt(1, product.getProduct().getIntProductID());
				updateProducts.setInt(2, product.getIntQuantity());
				updateProducts.addBatch();
			}
			
			updateProducts.executeBatch();
			updateProducts.close();
			
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
	public boolean refundPayment() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Reservation> getAllUnpaidReservation() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WalkIn> getAllUnpaidWalkIn() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductSales> getAllUnpaidOrder() throws SQLException {
		
		Connection con 									= jdbc.getConnection();
		String getAllUnpaidOrder 						= "CALL getAllUnpaidTransaction(?);";
		
		List<ProductSales> productSalesList 			= new ArrayList<ProductSales>();

		try{
			PreparedStatement getAllProductSales = con.prepareStatement(getAllUnpaidOrder);
			ResultSet orders;
			
			getAllProductSales.setInt(1, 3);
			orders = getAllProductSales.executeQuery();

			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				ProductSales salesList;
				
				int intSalesID = orders.getInt(1);
				Date datCreated = orders.getDate(2);
				Date deliveryDate = orders.getDate(3);
				int intType = orders.getInt(4);
				String strName = orders.getString(5);
				String strAddress = orders.getString(6);
				int intLocationID = orders.getInt(7);
				String strContactNo = orders.getString(8);
				String strStatus = orders.getString(9);
				Invoice invoice = getInvoice(orders.getInt(10));
				
				salesList = new ProductSales(intSalesID, datCreated, deliveryDate, intType, strName, strAddress, intLocationID, strContactNo, orderDetails, invoice, strStatus);
				productSalesList.add(salesList);
			}
			
			getAllProductSales.close();
			orders.close();
			con.close();
			
			return productSalesList;
		}
		catch(Exception e){
			e.printStackTrace();
			con.close();
			return null;
		}
	}

	@Override
	public boolean createReservationPayment(Payment payment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createWalkInPayment(Payment payment) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
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
		
		try{
			
			PreparedStatement preInvoice 		= con.prepareStatement(getInvoice);
			PreparedStatement preDiscount		= con.prepareStatement(getDiscount);
			PreparedStatement preExtraCharge	= con.prepareStatement(getExtraCharge);
			PreparedStatement prePayment			= con.prepareStatement(getPayment);
			
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
				payment = invoiceSet.getInt(3);
				
				
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
					int intPaymentType 		= paymentSet.getInt(3);
					double paymentAmount	= paymentSet.getDouble(4);
					Date dateOfPayment		= paymentSet.getDate(5);
			
					Payment extra = new Payment(intID, invoice, Payment.convertToString(intPaymentType), paymentAmount, dateOfPayment);
					
					paymentList.add(extra);
				}
			}
			
			double totalAmount = Invoice.computeTotalAmount(productList, serviceList, packageList, promoList, extraChargeList);
			double remainingBalance = Invoice.getRemainingBalance(totalAmount, paymentList);
			
			
			Invoice invoice = new Invoice(intInvoiceID, date, discountList, extraChargeList, totalAmount, remainingBalance, paymentList, Invoice.convertToString(payment));
			
			return invoice;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
