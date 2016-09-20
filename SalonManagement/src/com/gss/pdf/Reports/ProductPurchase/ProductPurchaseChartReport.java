package com.gss.pdf.Reports.ProductPurchase;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;
import org.jfree.chart.JFreeChart;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.ProductTagReport;
import com.gss.model.Reports.ProductPurchase;
import com.gss.model.Reports.ProductTagSum;
import com.gss.model.Reports.TagReport;
import com.gss.utilities.DateHelper;
import com.gss.utilities.NumberGenerator;
import com.gss.utilities.ReportsHelper;
import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;

public class ProductPurchaseChartReport {

	
    
    private String destination = "/Product_Purchase_" + NumberGenerator.localDateTime() + ".pdf";
	private List<ProductTagReport> report;
	private String dateFrom;
	private String dateTo;
	private final int WIDTH	 = 650;
	private final int HEIGHT = 350;
	private final int X = 65;
	private final int Y = -50;
	
	private PdfWriter writer;
	
	public String generateReport(ProductPurchase report) throws BadElementException, MalformedURLException, DocumentException, IOException{
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		String title = report.getType().toLowerCase() + " PRODUCT SALES AS OF YEAR " + cal.get(Calendar.YEAR);
		
		if(report.getType().equalsIgnoreCase("annual") || report.getType().equalsIgnoreCase("annuall")){
			title = report.getType().toLowerCase() + " PRODUCT SALES FROM " + report.getDetails().get(0).getClassification()
			+ "-" + report.getDetails().get(report.getDetails().size() - 1).getClassification();
		}
		
		ProductPurchasesStackedChart pro = new ProductPurchasesStackedChart(title.toUpperCase(), report);
		
		JFreeChart chart = pro.getChart();
		
		Document document = createDocument();
		
		document.open();
		
		document.add(getHeader());
//		document.add(getTitle());
//		document.add(getTagTable());
//		document.add(getTotal());
		
		
		
		PdfContentByte contentByte = writer.getDirectContent();
		PdfTemplate template = contentByte.createTemplate(WIDTH + 70, HEIGHT + 200);
			
		@SuppressWarnings("deprecation")
		Graphics2D graphics2d = template.createGraphics(WIDTH + 70, HEIGHT + 100,
				new DefaultFontMapper());
		java.awt.geom.Rectangle2D rectangle2d = new Rectangle2D.Double(X, 0, WIDTH,
				HEIGHT);

		chart.draw(graphics2d, rectangle2d);
		
		graphics2d.dispose();
		contentByte.addTemplate(template, 0, 0);
		
		document.close();
		
		return this.destination;
	}
	
	public Paragraph getTotal(){
		ProductTagSum sum = ProductTagSum.getProductTagSum(dateFrom, dateTo);
		
		Paragraph paragraph = new Paragraph();
		
		Phrase totalDef = new Phrase("Total Defective     :    " + sum.getTotalDefective() + "\n", getBoldFont(15));
		Phrase totalLos = new Phrase("Total Lost             :    " + sum.getTotalLost() + "\n", getBoldFont(15));
		Phrase totalExp = new Phrase("Total Expired       :    " + sum.getTotalExpired() + "\n", getBoldFont(15));
		Phrase totalCon = new Phrase("Total Consumed   :    " + sum.getTotalConsumed() + "\n", getBoldFont(15));
		
		paragraph.add(totalDef);
		paragraph.add(totalLos);
		paragraph.add(totalExp);
		paragraph.add(totalCon);
		
		paragraph.setAlignment(Element.ALIGN_LEFT);
		paragraph.setSpacingBefore(15);
		
		return paragraph;
	}
	
	public Paragraph getTagTable(){
	
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		
		float[] width = {1, 4, 2, 2, (float) 1.2, 4};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		
		PdfPCell tagID = new PdfPCell(new Phrase("Tag ID", getBoldFont(13)));
		tagID.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell productName = new PdfPCell(new Phrase("ProductName", getBoldFont(13)));
		productName.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell dateTagged = new PdfPCell(new Phrase("Tag Date\n(MM-DD-YYYY)", getBoldFont(13)));
		dateTagged.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell tagType = new PdfPCell(new Phrase("Tag Type", getBoldFont(13)));
		tagType.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell tagQuantity = new PdfPCell(new Phrase("Quantity", getBoldFont(13)));
		tagQuantity.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell employee = new PdfPCell(new Phrase("Tagged By", getBoldFont(13)));
		employee.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(tagID);
		table.addCell(productName);
		table.addCell(dateTagged);
		table.addCell(tagType);
		table.addCell(tagQuantity);
		table.addCell(employee);
		
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
		
		for(int index = 0; index < this.report.size(); index++){
			
			ProductTagReport tag = this.report.get(index);
			
			PdfPCell id = new PdfPCell(new Phrase(String.valueOf(tag.getIntTagID()), getNormalFont(12)));
			id.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell name = new PdfPCell(new Phrase(tag.getStrProductName(), getNormalFont(12)));
			name.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell date = new PdfPCell(new Phrase(DateHelper.stringDate(tag.getDatDateTagged()), getNormalFont(12)));
			date.setHorizontalAlignment(Element.ALIGN_CENTER);	
			PdfPCell type = new PdfPCell(new Phrase(tag.getTagType(), getNormalFont(12)));
			type.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell quantity = new PdfPCell(new Phrase(String.valueOf(tag.getIntQuantity()), getNormalFont(12)));
			quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell emp = new PdfPCell(new Phrase(tag.getStrEmployee(), getNormalFont(12)));
			emp.setHorizontalAlignment(Element.ALIGN_CENTER);
		
			table.addCell(id);
			table.addCell(name);
			table.addCell(date);
			table.addCell(type);
			table.addCell(quantity);
			table.addCell(emp);
		}
		
		paragraph.add(table);
		
		return paragraph;
	}
	
	public Paragraph getTitle(){
		Paragraph contractParagraph = new Paragraph(new Phrase("PRODUCT TAG REPORT\n", getBoldFont(20)));
		
		contractParagraph.add(new Phrase("From "+ this.dateFrom +" to "+ this.dateTo, getBoldFont(13)));
		contractParagraph.setAlignment(Element.ALIGN_CENTER);
		contractParagraph.setSpacingAfter(30);
		contractParagraph.setSpacingBefore(30);
		
		return contractParagraph;
	}
	
	public Document createDocument(){
		Document document = new Document(PageSize.LETTER.rotate(), 30, 30, 30, 30);
        try {
			this.writer = PdfWriter.getInstance(document, new FileOutputStream(((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
	                .getRealPath("WEB-INF/Reports/ProductPurchases")
	                +
	                this.destination));
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
    }
	
	public Font getBoldFont(int size){
    	
        Font font = FontFactory.getFont(FontFactory.TIMES, size, Font.BOLD);
        
        return font;
}

	public Font getNormalFont(int size){
	
		Font font = FontFactory.getFont(FontFactory.TIMES, size, Font.NORMAL);
	    
	    return font;
	}

	public Paragraph getHeader() throws BadElementException, MalformedURLException, IOException{
		
		Image COMPANY_LOGO = Image.getInstance(((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
                .getRealPath("WEB-INF/Company/Company_Logo.jpg"));
		COMPANY_LOGO.scaleAbsolute(80f, 80f);
		
		Paragraph paragraph = new Paragraph();
		Paragraph headerParagraph = new Paragraph();
		
		float[] width = {(float) 1.15, (float) 1.35, (float) 3.5, (float) 2.5};
		
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell logoCell = new PdfPCell();
		logoCell.addElement(COMPANY_LOGO);
		logoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		logoCell.setBorder(0);
		
		PdfPCell headerCell = new PdfPCell();
		
		headerParagraph.add(new Phrase("SALON MANAGEMENT SYSTEM\n"));
		headerParagraph.add(new Phrase("189-Dr. Sixto Antonio Avenue\n"));
		headerParagraph.add(new Phrase("Rosario, Pasig City\n"));
		headerParagraph.add(new Phrase("Tel No: +639361144842\n"));
		headerParagraph.setAlignment(Element.ALIGN_CENTER);
		
		headerCell.addElement(headerParagraph);
		headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headerCell.setBorder(0);
		
		PdfPCell nullCell = new PdfPCell();
		nullCell.setBorder(0);
		
		table.addCell(nullCell);
		table.addCell(logoCell);
		table.addCell(headerCell);
		table.addCell(nullCell);
		
		paragraph.add(table);
		
		return paragraph;
	}
}
