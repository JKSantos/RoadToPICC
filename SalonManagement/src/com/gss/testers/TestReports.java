package com.gss.testers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import com.gss.actions.Reports.ProductTagReportFactory;
import com.gss.model.ProductTagReport;
import com.gss.utilities.Receipt;
import com.itextpdf.text.DocumentException;

public class TestReports {

	
	public static void main(String[] args){
		testproductTags();
	}
	
	public static void testproductTags(){
		ProductTagReportFactory factory = new ProductTagReportFactory();
		
		try {
			factory.generateReport("August 19, 2016","September 19, 2016", ProductTagReport.getProductTagReport());
		} catch (DocumentException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
