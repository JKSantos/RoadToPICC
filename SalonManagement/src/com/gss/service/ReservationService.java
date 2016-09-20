package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.EmployeeAssigned;
import com.gss.model.ProductOrder;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;

public interface ReservationService {
	
public List<Reservation> getAllReservation();
	
	public int createReservation(Reservation reservation) throws SQLException;
	
	public boolean updateReservation(Reservation reservation) throws SQLException;
	
	public boolean cancelReservation(int id) throws SQLException;
	
	public List<EmployeeAssigned> getAllAssignedEmployee(int ReservationID);
	
	public List<ProductOrder> getAllProductOrder(int ReservationID);
	
	public List<ReservedService> getAllReservedService(int ReservationID);
	
	public List<ReservedPackage> getAllReservedPackage(int ReservationID);
	
	public List<ReservedPromo> getAllReservedPromo(int ReservationID);

	public List<Reservation> getAllReservationNoDetails();
}
