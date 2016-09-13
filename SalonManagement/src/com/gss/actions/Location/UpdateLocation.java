package com.gss.actions.Location;

import com.gss.dao.LocationJDBCRepository;
import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class UpdateLocation {
	
	private int intLocationID;
	private String strBrgy;
	private String strCity;
	private String price;
	
	private String result;
	
	public String execute() throws Exception{
		
		double dblLocationPrice = PriceFormatHelper.convertToDouble(price, "Php ");
		
		LocationService service = new LocationServiceImpl();
		Location location = new Location(this.intLocationID, this.strBrgy, this.strCity, dblLocationPrice, 1);
		

		if(LocationJDBCRepository.checkLocationName(strBrgy, strCity, intLocationID).equalsIgnoreCase("valid")){
			if(service.updateLocation(location) == true){
				result = "success";
				return result;
			}
			else{
				result = "failed";
				return result;
			}
		}else{
			result = "existing";
			return result;
		}
		
	}

	public void setIntLocationID(int intLocationID) {
		this.intLocationID = intLocationID;
	}

	public void setStrBrgy(String strBrgy) {
		this.strBrgy = strBrgy;
	}

	public void setStrCity(String strCity) {
		this.strCity = strCity;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getResult() {
		return result;
	}
}
