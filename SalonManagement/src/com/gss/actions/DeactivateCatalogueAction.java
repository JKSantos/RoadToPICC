package com.gss.actions;

import com.gss.service.CatalogueService;
import com.gss.service.CatalogueServiceImpl;

public class DeactivateCatalogueAction {
	
	private int intCatalogueID;
	
	public String execute(){
		
		CatalogueService service = new CatalogueServiceImpl();
		
		if(service.deactivateCatalogue(intCatalogueID) == false){
			return "success";
		}
		else{
			return "failed";
		}
		
	}

	public int getIntCatalogueID() {
		return intCatalogueID;
	}

	public void setIntCatalogueID(int intCatalogueID) {
		this.intCatalogueID = intCatalogueID;
	}

}
