package com.gss.service;

import java.util.List;

import com.gss.model.ExtraCharge;

public interface ExtraChargeService {
	
	public boolean createExtraCharge(ExtraCharge extra);
	
	public List<ExtraCharge> getAllExtraCharges();
	
	public boolean updateExtraCharge(ExtraCharge extra);
	
	public boolean deactivateExtraCharge(int intExtraChargeID);

	public List<ExtraCharge> queryAllOtherCharge();

}
