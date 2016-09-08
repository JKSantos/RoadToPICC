package com.gss.testers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.ProductPurchase;
import com.gss.pdf.Reports.ProductPurchaseChartReport;
import com.gss.pdf.Reports.ProductPurchasesStackedChart;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;

public class TestSalesChartAction {

	public static void main(String[] args) throws BadElementException, MalformedURLException, DocumentException, IOException{
		ProductPurchase purchase = ProductPurchasesRepository.getProductPurchases(ReportsHelper.monthlyReport(), "monthly");
		
		ProductPurchaseChartReport repo = new ProductPurchaseChartReport();
		repo.generateReport(purchase);
	}
}
