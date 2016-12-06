package com.gss.Receipts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.StrutsStatics;

import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductSales;
import com.gss.model.ProductWalkIn;
import com.gss.model.Promo;
import com.gss.model.PromoWalkIn;
import com.gss.model.Reservation;
import com.gss.model.Service;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;
import com.gss.utilities.NumberGenerator;
import com.gss.utilities.SearchPackage;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchPromo;
import com.gss.utilities.SearchService;
import com.gss.model.Package;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionContext;

public class WalkInReceipt {

	private String cashier;
	private WalkIn walkin;
	private Payment payment;
	private String orNum;
	private String destination;
	private double discounted;
	
	public String createProductSalesReceipt(WalkIn walkin, String cashier, String date, Payment payment, String path) throws IOException, NullPointerException, DocumentException{
		
    	this.cashier = cashier;
    	this.payment = payment;
    	this.walkin = walkin;
    	
    	this.orNum = NumberGenerator.localDateTime();
    	this.destination = path;
    	Document document = createDocument();
        document.open();

        document.add(getCompanyHeader());
        
        Paragraph invoiceHeader = new Paragraph("O F F I C I A L  R E C E I P T", getFont());
        invoiceHeader.setAlignment(Element.ALIGN_CENTER);
        invoiceHeader.setSpacingBefore(15);
        invoiceHeader.setSpacingAfter(25);
        
        document.add(invoiceHeader);
        document.add(getDetails());
        document.add(getDetailHeader());
        document.add(getProductSalesReceiptDetails());
        document.add(getReceiptFooter());
        document.add(getTaxList());
        
        document.add(new Phrase("Customer Name:"+ walkin.getStrName() + "\n", getFont()));
        document.add(new Phrase("Address:" + "N/A" + "\n", getFont()));
        document.add(new Phrase("Contact No:" + walkin.getStrContactNo() + "\n\n\n", getFont()));
        
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        Phrase phrase = new Phrase("*THIS SERVES AS OFFICIAL RECEIPT*", getBiggerFont(12));
        paragraph.add(phrase);
        
        document.add(paragraph);
        document.close();
        
        System.out.print(this.destination);
		return this.destination;
	}
	
	public String createReservationReceipt(){
		return null;
	}
	
	public String createWalkInReceipt(){
		return null;
	}
    public Document createDocument(){
    	Document document = new Document(new Rectangle(350, 550), 10, 10, 10 ,10);
        try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(
	                ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
	                .getRealPath(this.destination)
	            )));
			
			this.destination = ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)) 
            .getRealPath(this.destination);
        
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
    }
    
    public Paragraph getDetails(){
    	
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(this.payment.getDatDateOfPayment());
    	
    	int month = cal.get(Calendar.MONTH);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	int year = cal.get(Calendar.YEAR);
    	
    	PdfPCell dateCell = new PdfPCell(new Phrase("Date:" + (month + 1) + "-" + day + "-" + year + "\n", getFont()));
    	PdfPCell ORCell = new PdfPCell(new Phrase("Trans #:" + this.orNum, getFont()));
    	PdfPCell cashierCell = new PdfPCell(new Phrase("Cashier:" + this.cashier + "\n", getFont()));
    	
    
        dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        dateCell.setBorder(0);
        ORCell.setBorder(0);
        cashierCell.setBorder(0);
        cashierCell.setBorder(0);
        cashierCell.setColspan(2);
        
    	Paragraph paragraph = new Paragraph();
    	
    	float[] columnWidths = {3,2};

    	PdfPTable table2 = new PdfPTable(columnWidths);
    	PdfPTable table = new PdfPTable(columnWidths);
    	table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
    	
        table.addCell(ORCell);
    	table.addCell(dateCell);
    	table.addCell(cashierCell);
    	
    	
    	paragraph.add(table);
    	
    	return paragraph;
    }

    public Paragraph getDetailHeader(){
    	
    	Paragraph paragraph = new Paragraph();
    	
    	PdfPCell headLine = new PdfPCell(new Phrase("---------------------------------------------------\n", getFont()));
    	PdfPCell qty = new PdfPCell(new Phrase("Qty"));
    	PdfPCell desc = new PdfPCell(new Phrase("Description"));
    	PdfPCell unit = new PdfPCell(new Phrase("Unit Price"));
    	PdfPCell price = new PdfPCell(new Phrase("Amount"));
    	PdfPCell footLine = new PdfPCell(new Phrase("---------------------------------------------------\n", getFont()));
    	
    	headLine.setBorder(0);
    	qty.setBorder(0);
    	qty.setHorizontalAlignment(Element.ALIGN_CENTER);
    	desc.setBorder(0);
    	desc.setHorizontalAlignment(Element.ALIGN_CENTER);
    	unit.setBorder(0);
    	unit.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	price.setBorder(0);
    	price.setHorizontalAlignment(Element.ALIGN_RIGHT);
        footLine.setBorder(0);
        
        headLine.setColspan(4);
        footLine.setColspan(4);
    	
    	float[] columnWidths = {1, 5, 2, 2};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);

        table.addCell(headLine);
    	table.addCell(qty);
    	table.addCell(desc);
    	table.addCell(unit);
    	table.addCell(price);
    	table.addCell(footLine);
    	
    	paragraph.add(table);
    	
    	return paragraph;
    	
    }
    
    public Paragraph getProductSalesReceiptDetails(){
    	
    	Paragraph details = new Paragraph();
    	details.setFont(getFont());
    	details.setAlignment(Element.ALIGN_LEFT);
        
        float[] columnWidths = {1, 5, 2, 2};
        PdfPTable table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        
        String[] serviceID = new String[walkin.getServices().size()];
        String[] productID = new String[walkin.getProducts().size()];
        String[] packageID = new String[walkin.getPackages().size()];
        String[] promoID = new String[walkin.getPromo().size()];
        
        for(int i = 0; i < walkin.getServices().size(); i++){
        	serviceID[i] = String.valueOf(walkin.getServices().get(i).getService().getIntServiceID());
        }
        
        for(int i = 0; i < walkin.getProducts().size(); i++){
        	productID[i] = String.valueOf(walkin.getProducts().get(i).getProduct().getIntProductID());
        }
        
        for(int i = 0; i < walkin.getPackages().size(); i++){
        	packageID[i] = String.valueOf(walkin.getPackages().get(i).getPackages().getIntPackageID());
        }
        
        for(int i = 0; i < walkin.getPromo().size(); i++) {
        	promoID[i] = String.valueOf(walkin.getPromo().get(i).getPromo().getIntPromoID());
        }
        
        List<Product> productList = new SearchProduct().searchList(productID, Product.getAllProduct());
        List<Service> serviceList = new SearchService().searchList(serviceID, Service.getAllService());
        List<Package> packageList = new SearchPackage().searchList(packageID, Package.getAllPackageNoDetails());
        List<Promo> promoList = new SearchPromo().searchList(promoID, Promo.getAllPromoNoDetails());
        
        for (int counter = 0; counter < productList.size(); counter++) {
        	Product product = productList.get(counter);
        	
        	ProductWalkIn order = walkin.getProducts().get(counter);
        	
        	int quantity = order.getIntQuantity();
        	String productName = product.getStrProductName();
        	double unit = product.getDblProductPrice();
        	double price = unit * quantity;
        	
        	discounted += price;
        	
            PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(quantity), getFont()));
            qtyCell.setBorder(0);
            qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell descCell = new PdfPCell(new Phrase(productName, getFont()));
            descCell.setBorder(0);
            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(unit), getFont()));
            priceCell.setBorder(0);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(price), getFont()));
            amountCell.setBorder(0);	
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(qtyCell);
            table.addCell(descCell);
            table.addCell(priceCell);
            table.addCell(amountCell);
        }
        
        
        for (int counter = 0; counter < serviceList.size(); counter++) {
        	Service service = serviceList.get(counter);
        	
        	ServiceWalkIn order = walkin.getServices().get(counter);
        	
        	int quantity = 1;
        	String productName = service.getStrServiceName();

        	double unit = service.getDblServicePrice();
        	double price = unit * quantity;
        	
        	discounted += price;
        	
            PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(quantity), getFont()));
            qtyCell.setBorder(0);
            qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell descCell = new PdfPCell(new Phrase(productName, getFont()));
            descCell.setBorder(0);
            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(unit), getFont()));
            priceCell.setBorder(0);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(price), getFont()));
            amountCell.setBorder(0);	
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(qtyCell);
            table.addCell(descCell);
            table.addCell(priceCell);
            table.addCell(amountCell);
        }
        
        for (int counter = 0; counter < packageList.size(); counter++) {
        	Package packagee = packageList.get(counter);
        	
        	PackageWalkIn order = walkin.getPackages().get(counter);
        	
        	int quantity = 1;
        	String productName = packagee.getStrPackageName();
        	double unit = packagee.getDblPackagePrice();
        	double price = unit * quantity;
        	
        	discounted += price;
        	
            PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(quantity), getFont()));
            qtyCell.setBorder(0);
            qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell descCell = new PdfPCell(new Phrase(productName, getFont()));
            descCell.setBorder(0);
            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(unit), getFont()));
            priceCell.setBorder(0);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(price), getFont()));
            amountCell.setBorder(0);	
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(qtyCell);
            table.addCell(descCell);
            table.addCell(priceCell);
            table.addCell(amountCell);
        }	
        
        for (int counter = 0; counter < promoList.size(); counter++) {
        	Promo promo = promoList.get(counter);
        	
        	PromoWalkIn order = walkin.getPromo().get(counter);
        	
        	int quantity = 1;
        	String productName = promo.getStrPromoName();
        	double unit = promo.getDblPromoPrice();
        	double price = unit * quantity;
        	
        	discounted += price;
        	
            PdfPCell qtyCell = new PdfPCell(new Phrase(String.valueOf(quantity), getFont()));
            qtyCell.setBorder(0);
            qtyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell descCell = new PdfPCell(new Phrase(productName, getFont()));
            descCell.setBorder(0);
            PdfPCell priceCell = new PdfPCell(new Phrase(String.valueOf(unit), getFont()));
            priceCell.setBorder(0);
            priceCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell amountCell = new PdfPCell(new Phrase(String.valueOf(price), getFont()));
            amountCell.setBorder(0);	
            amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(qtyCell);
            table.addCell(descCell);
            table.addCell(priceCell);
            table.addCell(amountCell);
        }	
        
       
        details.add(table);
        
		return details;
    }

    public Image getImage() throws BadElementException, MalformedURLException, IOException{
    	
    	Image image2 = Image.getInstance("resource/Company/Company_Logo.jpg");
 	    image2.scaleAbsolute(50f, 50f);
 	    image2.setAbsolutePosition(10,(490 - image2.getScaledHeight()));
 	    
 	    return image2;
    }
    
    public Font getFont(){
    	
        FontFactory.register("Merchant Copy Wide.ttf", "8PIN");
        Font font = FontFactory.getFont("8PIN", 9);
        
        return font;
    }
    
    public Font getBiggerFont(int size){
    	FontFactory.register("Merchant Copy Wide.ttf", "8PIN");
        Font font = FontFactory.getFont("8PIN", size);
        
        return font;
    }
    
    public Paragraph getCompanyHeader(){
    	
    	Paragraph paragraph = new Paragraph();
    	paragraph.setFont(getFont());
    	paragraph.setAlignment(Element.ALIGN_CENTER);
    	paragraph.setSpacingBefore(10f);
    	
    	Phrase companyName = new Phrase("Salon Management System\n");
 	    Phrase comapanyStreet = new Phrase("189-Dr. Sixto Antonio Avenue\n");
 	    Phrase companyCity = new Phrase("Rosario, Pasig City\n");
 	    Phrase companyTelNo = new Phrase("Tel No: 6405021\n");
 	    
 	    paragraph.add(companyName);
 	    paragraph.add(comapanyStreet);
 	    paragraph.add(companyCity);
 	    paragraph.add(companyTelNo);
 	    
 	    return paragraph;
    }
    
    public Paragraph getReceiptFooter(){
    	
    	Paragraph paragraph = new Paragraph();
    	
    	float[] columnWidths = {1, 1};
    	PdfPTable details = new PdfPTable(columnWidths);
    	details.setWidthPercentage(100);
    	details.getDefaultCell().setUseAscender(true);
    	details.getDefaultCell().setUseDescender(true);
    	
    	double paymentAmount = this.payment.getDblPaymentAmount();
    	double totalAmount = this.walkin.getInvoice().getDblTotalPrice();
    	double change = paymentAmount - totalAmount;
    	
    	int count = 0;
    	
    	for(int i = 0; i < this.walkin.getProducts().size(); i++){
    		count += this.walkin.getProducts().get(i).getIntQuantity();
    	}
    	
    	PdfPCell cellHeader = new PdfPCell(new Phrase("-----------------------"+ count +" ITEMS---------------------", getFont()));
    	PdfPCell totalLabel2 = null;
    	PdfPCell totalLabel1 = null;
    	PdfPCell totalValue1 = null;
    	PdfPCell totalValue2 = null;
    	
    	/*if(walkin.getInvoice().getDiscountList().size() < 1){
	    	totalLabel1 = new PdfPCell(new Phrase("TOTAL", getBiggerFont(11)));
	    	totalValue1 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount), getBiggerFont(11)));
    	} else {*/
    		totalLabel1 = new PdfPCell(new Phrase("TOTAL W/O DISCOUNT", getBiggerFont(11)));
	    	totalValue1 = new PdfPCell(new Phrase(String.format("%.2f", discounted), getBiggerFont(11)));
	    	totalLabel2 = new PdfPCell(new Phrase("TOTAL W/ DISCOUNT", getBiggerFont(11)));
	    	totalValue2 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount), getBiggerFont(11)));
    	//}
    	
    	PdfPCell cashLabel = new PdfPCell(new Phrase("    Cash", getFont()));
    	PdfPCell cashValue = new PdfPCell(new Phrase(String.format("%.2f", paymentAmount), getFont()));
    	PdfPCell changeLabel = new PdfPCell(new Phrase("CHANGE", getBiggerFont(14)));
    	PdfPCell changeValue = new PdfPCell(new Phrase(String.format("%.2f", change), getBiggerFont(14)));
    	PdfPCell cellfooter = new PdfPCell(new Phrase("---------------------------------------------------", getFont()));
         
    	cellHeader.setBorder(0);
    	totalLabel1.setBorder(0);
    	totalValue1.setBorder(0);
    	totalValue1.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	totalLabel2.setBorder(0);
    	totalValue2.setBorder(0);
    	totalValue2.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	cashLabel.setBorder(0);
    	cashValue.setBorder(0);
    	cashValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	changeLabel.setBorder(0);
    	changeValue.setBorder(0);
    	changeValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	cellfooter.setBorder(0);
    	
    	cellHeader.setColspan(2);
    	cellfooter.setColspan(2);
    	
        details.addCell(cellHeader);
        /*if(walkin.getInvoice().getDiscountList().size() < 1) {
        	details.addCell(totalLabel1);
            details.addCell(totalValue1);
        } else {*/
        	details.addCell(totalLabel1);
            details.addCell(totalValue1);
            details.addCell(totalLabel2);
            details.addCell(totalValue2);
       // }
        details.addCell(cashLabel);
        details.addCell(cashValue);
        details.addCell(changeLabel);
        details.addCell(changeValue);
        details.addCell(cellfooter);
        
        paragraph.add(details);
        
        return paragraph;
    }

    public Paragraph getTaxList(){

    	Paragraph paragraph = new Paragraph();
    	
    	double paymentAmount = this.walkin.getInvoice().getDblTotalPrice();
    	double vatSale = paymentAmount - (paymentAmount * .12);
    	double vat = paymentAmount * .12;
    	
    	float[] columnWidths = {1, 1};
    	PdfPTable details = new PdfPTable(columnWidths);
    	details.setWidthPercentage(100);
    	details.getDefaultCell().setUseAscender(true);
    	details.getDefaultCell().setUseDescender(true);
    	
    	PdfPCell vatSaleLabel = new PdfPCell(new Phrase("VAT SALE", getFont()));
    	PdfPCell vatSaleValue = new PdfPCell(new Phrase(String.format("%.2f", vatSale), getFont()));
    	PdfPCell totalSaleLabel = new PdfPCell(new Phrase("TOTAL SALE", getFont()));
    	PdfPCell totalSaleValue = new PdfPCell(new Phrase(String.valueOf(paymentAmount), getFont()));
    	PdfPCell vatLabel = new PdfPCell(new Phrase("12% VAT", getFont()));
    	PdfPCell vatValue = new PdfPCell(new Phrase(String.format("%.2f", vat), getFont()));
    	PdfPCell cellfooter = new PdfPCell(new Phrase("---------------------------------------------------", getFont()));

		vatSaleLabel.setBorder(0);
		vatSaleValue.setBorder(0);
		vatSaleValue.setHorizontalAlignment(Element.ALIGN_RIGHT);;
		totalSaleLabel.setBorder(0);
		totalSaleValue.setBorder(0);
		totalSaleValue.setHorizontalAlignment(Element.ALIGN_RIGHT);;
		vatLabel.setBorder(0);
		vatValue.setBorder(0);
		vatValue.setHorizontalAlignment(Element.ALIGN_RIGHT);;
    	cellfooter.setBorder(0);
    	

    	cellfooter.setColspan(2);
    	

        details.addCell(vatSaleLabel);
        details.addCell(vatSaleValue);
        details.addCell(totalSaleLabel);
        details.addCell(totalSaleValue);
        details.addCell(vatLabel);
        details.addCell(vatValue);
        details.addCell(cellfooter);
        
        paragraph.add(details);
        paragraph.setSpacingAfter(15);
        
        return paragraph;
    }
}
