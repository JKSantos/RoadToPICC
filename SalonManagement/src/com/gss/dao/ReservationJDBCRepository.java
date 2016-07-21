package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Time;

import com.gss.connection.JDBCConnection;
import com.gss.model.EmployeeAssigned;
import com.gss.model.Invoice;
import com.gss.model.Package;
import com.gss.model.Payment;
import com.gss.model.ProductOrder;
import com.gss.model.Promo;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;

public class ReservationJDBCRepository implements ReservationRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	private int intReservationID;
	private Date datFrom;
	private Date datTo;
	private String timFrom;
	private String timTo;
	private String strName;
	private String strAddress;
	private String strContactNo;
	private String strEmail;
	private int headCount;
	private List<EmployeeAssigned> employeeAssigned;
	private List<ProductOrder> productList;
	private List<Service> serviceList;
	private List<Package> packageList;
	private List<Promo> promoList;
	private Invoice invoice;
	private List<Payment> paymentList;
	private String strStatus;
	
	private Date date = new Date();
	private int intInvoiceID;
	
	@Override
	public List<Reservation> getAllReservation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createReservation(Reservation reservation) throws SQLException {
		
		Connection con = jdbc.getConnection();
		con.setAutoCommit(false);
		
		String createReservation				= "CALL createReservation(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String createProduct					= "CALL createProductReservation(?, ?, ?);";
		String createService					= "CALL createServiceReservation(?, ?, ?);";
		String createPackage					= "CALL createPackageReservation(?, ?, ?);";
		String createPromo						= "CALL createPromoReservation(?, ?, ?);";
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
			preReservation.setString(7, reservation.getCustomer().getStrAddress());
			preReservation.setString(8, reservation.getCustomer().getStrContactNo());
			preReservation.setString(9, reservation.getCustomer().getStrEmail());
			preReservation.setInt(10, reservation.getHeadCount());
			preReservation.setString(11, reservation.getStrVenue());
			preReservation.setString(12, reservation.getStrStatus());
			
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
				preProduct.addBatch();
			}
						
			//services
			
			for(int i = 0; i < reservation.getIncludedItems().getServiceList().size(); i++){
				preService.setInt(1, intReservationID);
				preService.setInt(2, reservation.getIncludedItems().getServiceList().get(i).getService().getIntServiceID());
				preService.setInt(3, reservation.getIncludedItems().getServiceList().get(i).getIntQuantity());
				preService.addBatch();
			}
			
			//packages
			
			for(int i = 0; i < reservation.getIncludedItems().getPackageList().size(); i++){
				prePackage.setInt(1, intReservationID);
				prePackage.setInt(2, reservation.getIncludedItems().getPackageList().get(i).getPackages().getIntPackageID());
				prePackage.setInt(3, reservation.getIncludedItems().getPackageList().get(i).getIntQuantity());
				prePackage.addBatch();
			}
			
			//promos
			
			for(int i = 0; i < reservation.getIncludedItems().getPromoList().size(); i++){
				prePromo.setInt(1, intReservationID);
				prePromo.setInt(2, reservation.getIncludedItems().getPromoList().get(i).getPromo().getIntPromoID());
				prePromo.setInt(3, reservation.getIncludedItems().getPromoList().get(i).getIntQuantity());
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
			reservationResult.close();
			
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
	public boolean updateReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelReservation(Reservation reservation) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeeAssigned> getAllAssignedEmployee(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductOrder> getAllProductOrder(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedService> getAllReservedService(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedPackage> getAllReservedPackage(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReservedPromo> getAllReservedPromo(int ReservationID) {
		// TODO Auto-generated method stub
		return null;
	}
}
