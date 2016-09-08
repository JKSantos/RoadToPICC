package com.gss.actions.Reports;

import java.io.IOException;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.pdf.Reports.ProductTagChartReport;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.text.DocumentException;

public class PrintProductTagChart {
	
	private String type; //monthly, quarterly, annually
	private String pdfPath;
	
	//if annually
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		
		ProductTagChartReport report = new ProductTagChartReport();
		
		try {
			
			if(type.equalsIgnoreCase("annually"))
				this.pdfPath = report.generateReport(ProductTagJDBCRepository.getTagReport(ReportsHelper.annualReport(yearFrom, yearTo), type));
			else if(type.equalsIgnoreCase("monthly"))
				this.pdfPath = report.generateReport(ProductTagJDBCRepository.getTagReport(ReportsHelper.monthlyReport(), type));
			else
				this.pdfPath = report.generateReport(ProductTagJDBCRepository.getTagReport(ReportsHelper.quarterlyReport(), type));
			
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success";
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPdfPath() {
		return pdfPath;
	}
	
	public void setYearFrom(int yearFrom) {
		this.yearFrom = yearFrom;
	}

	public void setYearTo(int yearTo) {
		this.yearTo = yearTo;
	}
}
