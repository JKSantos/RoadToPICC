package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.gss.connection.JDBCConnection;
import com.gss.model.Discount;
import com.gss.model.ExtraCharge;
import com.gss.model.Invoice;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.service.DiscountService;
import com.gss.service.DiscountServiceImpl;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;


public class ProductSalesJDBCRepository implements ProductSalesRepository{
	
	private JDBCConnection jdbc = new JDBCConnection();
	
	private int intSalesID;
	private Date datCreated;
	private Date deliveryDate;
	private int intType;
	private String strName;
	private String strAddress;
	private int intLocationID;
	private String strContactNo;
	private Invoice invoice;
	private String strStatus;
	private int intID;
	private int intQuantity;
	private int intStatus;

	private List<ProductOrder> productList = new ArrayList<ProductOrder>();
	private List<ReservedService> serviceList = new ArrayList<ReservedService>();
	private List<ReservedPackage> packageList = new ArrayList<ReservedPackage>();
	private List<ReservedPromo> promoList = new ArrayList<ReservedPromo>();
	
	@Override
	public int createProductSales(ProductSales sales) throws SQLException {
		
		Connection con 								= jdbc.getConnection();
		String createProductSales 					= "CALL createProductSales( ?, ?, ?, ?, ?, ?)";
		String createDetails 						= "CALL createDetail(?, ?, ?)";
		int intID;
		try{
			con.setAutoCommit(false);
			
			PreparedStatement insertProductSales 	= con.prepareStatement(createProductSales);
			PreparedStatement insertDetails 		= con.prepareStatement(createDetails);
			ResultSet salesID;
			intID = 0;
			
			insertProductSales.setInt(1, sales.getIntType());
			insertProductSales.setString(2, sales.getStrName());
			insertProductSales.setString(3, sales.getStrAddress());
			insertProductSales.setInt(4, sales.getIntLocationID());
			insertProductSales.setString(5, sales.getStrContactNo());
			insertProductSales.setDouble(6, sales.getInvoice().getDblTotalPrice());
			salesID = insertProductSales.executeQuery();
			
			
			while(salesID.next()){
				intID = salesID.getInt(1);
			}
			for(int intCtr = 0; intCtr < sales.getProductList().size(); intCtr++){
				insertDetails.setInt(1, intID);
				insertDetails.setInt(2, sales.getProductList().get(intCtr).getProduct().getIntProductID());
				insertDetails.setInt(3, sales.getProductList().get(intCtr).getIntQuantity());
				insertDetails.addBatch();
			}
			
			insertDetails.executeBatch();
			insertProductSales.close();
			salesID.close();
			insertDetails.close();
			con.commit();
			con.close();
			return intID;
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return 0;
		}
	}
//undone
	@Override
	public boolean updateProductSales(ProductSales Sales) throws SQLException {
		Connection con 								= jdbc.getConnection();
		String updateProductSales 					= "CALL updateProductSales(?, ?, ?, ?)";
		String deleteProductDetails					= "CALL deleteDetails(?)";
		String createDetails 						= "CALL createDetail(?, ?, ?)";
		
		try{
			con.setAutoCommit(false);
			
			PreparedStatement updateSales 			= con.prepareStatement(updateProductSales);
			PreparedStatement deleteDetails			= con.prepareStatement(deleteProductDetails);
			PreparedStatement insertDetails 		= con.prepareStatement(createDetails);

			int intID = 0;
			
			updateSales.setInt(1, Sales.getIntSalesID());
			updateSales.setDate(2, (java.sql.Date) Sales.getDeliveryDate());
			updateSales.setString(3, Sales.getStrAddress());
			updateSales.setInt(4, Sales.getIntLocationID());
			updateSales.execute();
			
			deleteDetails.setInt(1, Sales.getIntSalesID());
			deleteDetails.execute();
			
			for(int intCtr = 0; intCtr < Sales.getProductList().size(); intCtr++){
				insertDetails.setInt(1, intID);
				insertDetails.setInt(2, Sales.getProductList().get(intCtr).getProduct().getIntProductID());
				insertDetails.setInt(3, Sales.getProductList().get(intCtr).getIntQuantity());
				insertDetails.addBatch();
			}
			
			insertDetails.executeBatch();
			updateSales.close();
			deleteDetails.close();
			insertDetails.close();
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			e.printStackTrace();
			con.rollback();
			con.close();
			
			return false;
		}
	}

	@Override
	public List<ProductSales> getAllProductSales() {
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
				
				this.intSalesID = orders.getInt(1);
				this.datCreated = orders.getDate(2);
				this.deliveryDate = orders.getDate(3);
				this.intType = orders.getInt(4);
				this.strName = orders.getString(5);
				this.strAddress = orders.getString(6);
				this.intLocationID = orders.getInt(7);
				this.strContactNo = orders.getString(8);
				this.strStatus = orders.getString(9);
				this.invoice = getInvoice(orders.getInt(10));
				
				getAllDetails.setInt(1, intSalesID);
				details = getAllDetails.executeQuery();
				
				while(details.next()){
					
					this.intID = details.getInt(1);
					int id = details.getInt(3);
					this.intQuantity = details.getInt(4);
					this.intStatus = details.getInt(5);
					
					for(int i = 0; i < productList.size(); i++){
						if(id == productList.get(i).getIntProductID()){
							Product product = productList.get(i);
							ProductOrder order = new ProductOrder(this.intID, product, this.intQuantity, this.intStatus);
							orderDetails.add(order);
						}
					}
				}
				details.close();
				
				salesList1 = new ProductSales(this.intSalesID, this.datCreated, this.deliveryDate, this.intType, this.strName, this.strAddress, this.intLocationID, this.strContactNo, orderDetails, this.invoice, this.strStatus);
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

	@Override
	public boolean deactivateProductSales(int intID) throws SQLException{
		
		Connection con = jdbc.getConnection();
		String deactivateSales = "UPDATE tblOrder SET strOrderStatus = 'CANCELLED' WHERE intOrderID = ?";
		
		try{
			con.setAutoCommit(false);
			PreparedStatement deactivate = con.prepareStatement(deactivateSales);
			deactivate.setInt(1, intID);
			deactivate.execute();
			deactivate.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			con.rollback();
			con.close();
			return false;
		}
	}
	
	@Override
	public boolean acceptProductSales(int intID, Date datDeliveryDate) throws SQLException {
		
		Connection con = jdbc.getConnection();
		String deactivateSales = "UPDATE tblOrder SET strOrderStatus = 'PENDING', dateOrderDate = ? WHERE intOrderID = ?";
		
		try{
			con.setAutoCommit(false);
			PreparedStatement deactivate = con.prepareStatement(deactivateSales);
			deactivate.setDate(1, new java.sql.Date(datDeliveryDate.getTime()));
			deactivate.setInt(2, intID);
			deactivate.execute();
			deactivate.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			con.rollback();
			con.close();
			return false;
		}
	}
	@Override
	public boolean declineProductSales(int intID) throws SQLException {
		
		Connection con = jdbc.getConnection();
		String deactivateSales = "UPDATE tblOrder SET strOrderStatus = 'DECLINED' WHERE intOrderID = ?";
		
		try{
			con.setAutoCommit(false);
			PreparedStatement deactivate = con.prepareStatement(deactivateSales);
			deactivate.setInt(1, intID);
			deactivate.execute();
			deactivate.close();
			
			con.commit();
			con.close();
			return true;
		}
		catch(Exception e){
			
			con.rollback();
			con.close();
			return false;
		}
	}
	
	public Invoice getInvoice(int intInvoiceID) {
		
		Connection con = jdbc.getConnection();
		String getInvoice 					= "SELECT * FROM tblInvoice WHERE intInvoiceID = ?;";
		String getDiscount 					= "SELECT * FROM tblInvoiceDiscount WHERE intInvoiceID = ?;";
		String getExtraCharge 				= "SELECT * FROM tblInvoiceExtraCharge WHERE intInvoiceID = ?;";
		String getPayment					= "SELECT * FROM tblPayment WHERE intInvoiceID = ?;";
		
		DiscountService discountService = new DiscountServiceImpl();
		ExtraChargeService extraService = new ExtraChargeServiceImpl();
		
		double totalAmount = 0;
		
		int payment = 0;
		String paymentType = null;
		String receipt = null;
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
			System.out.println(totalAmount + "   ....");
			while(invoiceSet.next()){
				
				date = invoiceSet.getDate(2);
				totalAmount = invoiceSet.getDouble(3);
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
					String strPaymentType 		= paymentSet.getString(3);
					double paymentAmount	= paymentSet.getDouble(4);
					Date dateOfPayment		= paymentSet.getDate(5);
			
					Payment extra = new Payment(intID, invoice, "order", paymentAmount, strPaymentType, dateOfPayment);
					
					paymentList.add(extra);
				}
			}
			
			double remainingBalance = Invoice.getRemainingBalance(totalAmount, paymentList);
			
			
			Invoice invoice = new Invoice(intInvoiceID, date, discountList, extraChargeList, totalAmount, remainingBalance, paymentType, paymentList, Invoice.convertToString(payment), receipt);
			
			return invoice;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ProductSales> getAllProductRequest() {
		
		Connection con = jdbc.getConnection();
		
		String getAllOrder 					= "SELECT * FROM tblOrder WHERE strOrderStatus = 'REQUEST';";
		
		try{

			List<ProductSales> salesList 	= new ArrayList<ProductSales>();
			
			PreparedStatement getAll 		= con.prepareStatement(getAllOrder);
			ResultSet orders				= getAll.executeQuery();
			
			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				ProductSales salesList1;
				
				this.intSalesID = orders.getInt(1);
				this.datCreated = orders.getDate(2);
				this.deliveryDate = orders.getDate(3);
				this.intType = orders.getInt(4);
				this.strName = orders.getString(5);
				this.strAddress = orders.getString(6);
				this.intLocationID = orders.getInt(7);
				this.strContactNo = orders.getString(8);
				this.strStatus = orders.getString(9);
				this.invoice = getInvoice(orders.getInt(10));
				
				salesList1 = new ProductSales(this.intSalesID, this.datCreated, this.deliveryDate, this.intType, this.strName, this.strAddress, this.intLocationID, this.strContactNo, orderDetails, this.invoice, this.strStatus);
				salesList.add(salesList1);
			}
			
			getAll.close();
			orders.close();
			
			con.close();
			System.out.println(salesList.size());
			return salesList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<ProductSales> getAllProductSalesNoDetails() {
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
				
				this.intSalesID = orders.getInt(1);
				this.datCreated = orders.getDate(2);
				this.deliveryDate = orders.getDate(3);
				this.intType = orders.getInt(4);
				this.strName = orders.getString(5);
				this.strAddress = orders.getString(6);
				this.intLocationID = orders.getInt(7);
				this.strContactNo = orders.getString(8);
				this.strStatus = orders.getString(9);
				this.invoice = getInvoice(orders.getInt(10));
				

				
				salesList1 = new ProductSales(this.intSalesID, this.datCreated, this.deliveryDate, this.intType, this.strName, this.strAddress, this.intLocationID, this.strContactNo, orderDetails, this.invoice, this.strStatus);
				salesList.add(salesList1);
			}
			
			getAll.close();
			getAllDetails.close();
			orders.close();
			
			con.close();
			System.out.println(salesList.size());
			return salesList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ProductSales getProductBySalesID(int intOrderID) throws Exception{
		
		Connection con = jdbc.getConnection();
		
		String getAllOrder 					= "SELECT * FROM tblOrder WHERE intOrderID = ? AND strOrderStatus <> 'CANCELLED' AND strOrderStatus <> 'DECLINED';";
		String getAllDet 					= "SELECT * FROM tblOrderDetails WHERE intOrderID = ?";
		
		ProductSales salesList1 = null;
		
		try{
			ProductService service = new ProductServiceImpl();
			List<ProductSales> salesList 	= new ArrayList<ProductSales>();
			List<Product> productList 		= service.getAllProductsNoImage();
			
			PreparedStatement getAll 		= con.prepareStatement(getAllOrder);
			PreparedStatement getAllDetails	= con.prepareStatement(getAllDet);
			getAll.setInt(1, intOrderID);
			ResultSet orders				= getAll.executeQuery();
			ResultSet details;
			
			while(orders.next()){
				
				List<ProductOrder> orderDetails = new ArrayList<ProductOrder>();
				
				this.intSalesID = orders.getInt(1);
				this.datCreated = orders.getDate(2);
				this.deliveryDate = orders.getDate(3);
				this.intType = orders.getInt(4);
				this.strName = orders.getString(5);
				this.strAddress = orders.getString(6);
				this.intLocationID = orders.getInt(7);
				this.strContactNo = orders.getString(8);
				this.strStatus = orders.getString(9);
				this.invoice = getInvoice(orders.getInt(10));
				
				getAllDetails.setInt(1, intSalesID);
				details = getAllDetails.executeQuery();
				
				while(details.next()){
					
					this.intID = details.getInt(1);
					int id = details.getInt(3);
					this.intQuantity = details.getInt(4);
					this.intStatus = details.getInt(5);
					
					for(int i = 0; i < productList.size(); i++){
						if(id == productList.get(i).getIntProductID()){
							Product product = productList.get(i);
							ProductOrder order = new ProductOrder(this.intID, product, this.intQuantity, this.intStatus);
							orderDetails.add(order);
						}
					}
				}
				details.close();
				
				salesList1 = new ProductSales(this.intSalesID, this.datCreated, this.deliveryDate, this.intType, this.strName, this.strAddress, this.intLocationID, this.strContactNo, orderDetails, this.invoice, this.strStatus);
				
			}
			
			getAll.close();
			getAllDetails.close();
			orders.close();
			
			con.close();
			
			return salesList1;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
