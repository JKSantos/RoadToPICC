package com.gss.actions.Reports.ProductPurchase;

import java.io.IOException;

import com.gss.dao.ProductTagJDBCRepository;
import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.pdf.Reports.ProductPurchase.ProductPurchaseChartReport;
import com.gss.pdf.Reports.ProductTags.ProductTagChartReport;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.text.DocumentException;
import com.opensymphony.xwork2.ActionSupport;

public class PrintProductPurchaseChart extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String type; //monthly, quarterly, annually
	private String pdfPath;
	
	//if annually
	private int yearFrom;
	private int yearTo;
	
	public String execute(){
		
		ProductPurchaseChartReport report = new ProductPurchaseChartReport();
		
		try {
			
			if(type.equalsIgnoreCase("annually"))
				this.pdfPath = report.generateReport(ProductPurchasesRepository.getProductPurchases(ReportsHelper.annualReport(yearFrom, yearTo), type));
			else if(type.equalsIgnoreCase("monthly"))
				this.pdfPath = report.generateReport(ProductPurchasesRepository.getProductPurchases(ReportsHelper.monthlyReport(), type));
			else
				this.pdfPath = report.generateReport(ProductPurchasesRepository.getProductPurchases(ReportsHelper.quarterlyReport(), type));
			
			this.pdfPath = "/Reports/ProductPurchase/" + this.pdfPath;
			
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
