package com.gss.actions.Reports;

public class PrintProductTagReport {
	
/*	public String dateFilterFrom;
	public String dateFilterTo;
	public String employeeFilter;
	public int tagTypeFilter;
*/
	public String filePath;
	
	public String execute(){
		
		this.filePath = null;
		
		return "success";
		
	}

	public String getFilePath() {
		return filePath;
	}
}
