package com.gss.service;

import java.sql.SQLException;
import java.util.List;

import com.gss.model.WalkIn;

public interface WalkInService {
	
	public boolean createWalkIn(WalkIn walkin) throws SQLException;
	
	public boolean updateWalkIn(WalkIn walkin);
	
	public boolean cancelWalkIn(int intWalkInID);
	
	public boolean payWalkIn(int intWalkInID, double dblPaymentAmount);
	
	public List<WalkIn> getAllWalkIn();

}
