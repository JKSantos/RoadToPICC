package com.gss.actions;

import com.gss.model.ProductTag;
import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class UpdateStockAction {
	
	private int intProductID;
	private int intQuantity;
	private int intType;
	
	public String execute(){
		
		boolean recorded = false;
		
		if(intType == 1){
			ProductTags addStock = new ProductTagImpl();
			recorded = addStock.addStock(new ProductTag(intProductID, intQuantity, intType));
		}
		else{
			ProductTags addStock = new ProductTagImpl();
			recorded = addStock.subtractStock(new ProductTag(intProductID, intQuantity, intType));
		}
		
		if(recorded == true){
			return "success";
		}
		else{
			return "failed";
		}
	}

	public int getIntProductID() {
		return intProductID;
	}

	public void setIntProductID(int intProductID) {
		this.intProductID = intProductID;
	}

	public int getIntQuantity() {
		return intQuantity;
	}

	public void setIntQuantity(int intQuantity) {
		this.intQuantity = intQuantity;
	}

	public int getIntType() {
		return intType;
	}

	public void setIntType(int intType) {
		this.intType = intType;
	}

	
}
