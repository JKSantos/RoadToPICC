package com.gss.actions.Location;

import com.gss.dao.LocationJDBCRepository;
import com.gss.model.Location;
import com.gss.service.LocationService;
import com.gss.service.LocationServiceImpl;
import com.gss.utilities.PriceFormatHelper;

public class CreateLocation {
	
	private String strBrgy;
	private String strCity;
	private String price;
	
	private String result;
	
	public String execute() throws Exception{
		
		double dblLocationPrice = PriceFormatHelper.convertToDouble(price, "Php ");
		LocationService service = new LocationServiceImpl();
		Location location = new Location(1, this.strBrgy, this.strCity, dblLocationPrice, 1);
		
		if(LocationJDBCRepository.checkLocationName(strBrgy, strCity).equalsIgnoreCase("valid")){
			if(service.createLocation(location) == true){
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

	public String getResult() {
		return result;
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

	
}
