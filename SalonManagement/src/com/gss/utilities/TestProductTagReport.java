package com.gss.utilities;

import java.io.IOException;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.pdf.Reports.ProductTagChartReport;
import com.itextpdf.text.DocumentException;

public class TestProductTagReport {
	
	
	public static void main(String[] args){
		ProductTagChartReport report = new ProductTagChartReport();
		
		try {
			report.generateReport(ProductTagJDBCRepository.getTagReport(ReportsHelper.quarterlyReport(), "quarterly"));
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}
