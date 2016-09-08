package com.gss.actions.Reports;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.model.Reports.SalesReport;
import com.gss.pdf.Reports.SalesChartReport;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class PrintSalesChart {

	private String type; //monthly, annually, quarterly
	//if anually
	private int yearFrom;
	private int yearTo;
	
	private String pdfPath;
	
	public String execute() throws BadElementException, MalformedURLException, DocumentException, IOException{
		if(type.equalsIgnoreCase("monthly"))
			this.pdfPath = new SalesChartReport().generateReport(SalesReportRepository.getSalesReport(ReportsHelper.monthlyReport(), "monthly"));
		else if(type.equalsIgnoreCase("annual"))
			this.pdfPath = new SalesChartReport().generateReport(SalesReportRepository.getSalesReport(ReportsHelper.annualReport(yearFrom, yearTo), "annual"));
		else
			this.pdfPath = new SalesChartReport().generateReport(SalesReportRepository.getSalesReport(ReportsHelper.quarterlyReport(), "quarterly"));
		
		return "success";
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
	
	public String getPdfPath(){
		return this.pdfPath;
	}
}
