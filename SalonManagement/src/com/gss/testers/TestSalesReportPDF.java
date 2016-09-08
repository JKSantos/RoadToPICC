package com.gss.testers;

import java.io.IOException;

import com.gss.dao.Reports.SalesReportRepository;
import com.gss.pdf.Reports.SalesChartReport;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.text.DocumentException;

public class TestSalesReportPDF {
	
	public static void main(String[] args){
		SalesChartReport report = new SalesChartReport(); 
		
		try {
			report.generateReport(SalesReportRepository.getSalesReport(ReportsHelper.quarterlyReport(), "quarterly"));
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
