package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.WalkIn;

public interface WalkInService {
	
	public int createWalkIn(WalkIn walkin) throws SQLException;
	
	public boolean updateWalkIn(WalkIn walkin)  throws SQLException ;
	
	public boolean cancelWalkIn(int intWalkInID);
	
	public boolean payWalkIn(int intWalkInID, double dblPaymentAmount);
	
	public List<WalkIn> getAllWalkIn();
	
	public List<WalkIn> getAllWalkInNoDetails();

}
