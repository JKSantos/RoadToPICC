package com.gss.actions;

import java.util.List;

import com.gss.model.ExtraCharge;
import com.gss.service.ExtraChargeService;
import com.gss.service.ExtraChargeServiceImpl;

public class ExtraChargeMaintenanceAction {
	
	private List<ExtraCharge> ecList;
	
	public String execute(){
		
		ExtraChargeService service = new ExtraChargeServiceImpl();
		this.setEcList(service.getAllExtraCharges());
		
		return "success";
	}

	public List<ExtraCharge> getEcList() {
		return ecList;
	}

	public void setEcList(List<ExtraCharge> ecList) {
		this.ecList = ecList;
	}

}
