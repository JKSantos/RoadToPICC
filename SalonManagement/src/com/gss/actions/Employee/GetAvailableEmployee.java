package com.gss.actions.Employee;

import java.util.List;

import com.gss.dao.Employee.EmployeeDao;
import com.gss.model.Employee;

public class GetAvailableEmployee {
	
	private List<Employee> empList;
	
	private String date;
	private String time;
	
	//if for delivery
	private String locationID;
	
	//if event
	private String timeTo;
	private String type;
	
	public String execute(){
		
		EmployeeDao dao = new EmployeeDao();
		
		System.out.println("tangina");
		
		switch(type.toLowerCase()){
			case "walkin":
				this.empList = dao.walkIn();
				break;
			case "appointment":
				this.empList = dao.appointment(date, time);
				break;
			case "home service":
				this.empList = dao.homeService(date, time);
				break;
			case "event":
				this.empList = dao.event(date, time, timeTo);
				break;
			case "delivery":
				this.empList = dao.delivery(date, "00:00:00", Integer.parseInt(locationID));
				break;
		}
		
		System.out.println(this.empList.size());
		
		return "success";
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
