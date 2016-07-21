package com.gss.actions;

import com.gss.model.ProductTag;
import com.gss.service.ProductTagImpl;
import com.gss.service.ProductTags;

public class CreateTagAction {
	
	private int intProductID;
	private int intQuantity;
	private int intTagType;
	
	public String execute(){
		
		boolean recorded = false;
		
		ProductTags createTag = new ProductTagImpl();
		recorded = createTag.defectiveTag(new ProductTag(intProductID, intQuantity, intTagType));
		
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

	public int getIntTagType() {
		return intTagType;
	}

	public void setIntTagType(int intTagType) {
		this.intTagType = intTagType;
	}
	
	

}
