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

import com.gss.model.Package;
import com.gss.model.PackageWalkIn;
import com.gss.model.Payment;
import com.gss.model.Product;
import com.gss.model.ProductOrder;
import com.gss.model.ProductWalkIn;
import com.gss.model.Promo;
import com.gss.model.PromoWalkIn;
import com.gss.model.Reservation;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.model.Service;
import com.gss.model.ServiceWalkIn;
import com.gss.model.WalkIn;
import com.gss.utilities.NumberGenerator;
import com.gss.utilities.SearchPackage;
import com.gss.utilities.SearchProduct;
import com.gss.utilities.SearchPromo;
import com.gss.utilities.SearchService;
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

public class EventComplementary {
	
	private String cashier;
	private Reservation walkin;
	private Payment payment;
	private String orNum;
	private String destination;
	private double discounted;
	
	public String createProductSalesReceipt(Reservation walkin, String cashier, String date, Payment payment, String path) throws IOException, NullPointerException, DocumentException{
		
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
        
        document.add(new Phrase("Customer Name:"+ walkin.getCustomer().getStrName() + "\n", getFont()));
        document.add(new Phrase("Address:" + walkin.getCustomer().getStrAddress() + "\n", getFont()));
        document.add(new Phrase("Contact No:" + walkin.getCustomer().getStrContactNo() + "\n\n\n", getFont()));
        
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
        
        String[] serviceID = new String[walkin.getIncludedItems().getServiceList().size()];
        String[] productID = new String[walkin.getIncludedItems().getProductList().size()];
        String[] packageID = new String[walkin.getIncludedItems().getPackageList().size()];
        String[] promoID = new String[walkin.getIncludedItems().getPromoList().size()];
        
        for(int i = 0; i < walkin.getIncludedItems().getServiceList().size(); i++){
        	serviceID[i] = String.valueOf(walkin.getIncludedItems().getServiceList().get(i).getService().getIntServiceID());
        }
        
        for(int i = 0; i < walkin.getIncludedItems().getProductList().size(); i++){
        	productID[i] = String.valueOf(walkin.getIncludedItems().getProductList().get(i).getProduct().getIntProductID());
        }
        
        for(int i = 0; i < walkin.getIncludedItems().getPackageList().size(); i++){
        	packageID[i] = String.valueOf(walkin.getIncludedItems().getPackageList().get(i).getPackages().getIntPackageID());
        }
        
        for(int i = 0; i < walkin.getIncludedItems().getPromoList().size(); i++) {
        	promoID[i] = String.valueOf(walkin.getIncludedItems().getPromoList().get(i).getPromo().getIntPromoID());
        }
        
        List<Product> productList = new SearchProduct().searchList(productID, Product.getAllProduct());
        List<Service> serviceList = new SearchService().searchList(serviceID, Service.getAllService());
        List<Package> packageList = new SearchPackage().searchList(packageID, Package.getAllPackageNoDetails());
        List<Promo> promoList = new SearchPromo().searchList(promoID, Promo.getAllPromoNoDetails());
        
        for (int counter = 0; counter < productList.size(); counter++) {
        	Product product = productList.get(counter);
        	
        	ProductOrder order = walkin.getIncludedItems().getProductList().get(counter);
        	
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
        	
        	ReservedService order = walkin.getIncludedItems().getServiceList().get(counter);
        	
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
        	
        	ReservedPackage order = walkin.getIncludedItems().getPackageList().get(counter);
        	
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
        	
        	ReservedPromo order = walkin.getIncludedItems().getPromoList().get(counter);
        	
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
    	
    	for(int i = 0; i < this.walkin.getIncludedItems().getProductList().size(); i++){
    		count += this.walkin.getIncludedItems().getProductList().get(i).getIntQuantity();
    	}
    	
    	PdfPCell cellHeader = new PdfPCell(new Phrase("-----------------------"+ count +" ITEMS---------------------", getFont()));
    	PdfPCell totalLabel2 = null;
    	PdfPCell totalLabel1 = null;
    	PdfPCell totalValue1 = null;
    	PdfPCell totalValue2 = null;
    	
    	double finalPrice = totalAmount;
    	double remainingBalances = 0;
    	
    	/*if(walkin.getInvoice().getDiscountList().size() < 1){
	    	totalLabel1 = new PdfPCell(new Phrase("TOTAL", getBiggerFont(11)));
	    	totalValue1 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount), getBiggerFont(11)));
    	} else {*/
    		totalLabel1 = new PdfPCell(new Phrase("TOTAL w/o Discount", getBiggerFont(11)));
	    	totalValue1 = new PdfPCell(new Phrase(String.format("%.2f", discounted), getBiggerFont(11)));
	    	totalLabel2 = new PdfPCell(new Phrase("TOTAL w/ Discount", getBiggerFont(11)));
	    	totalValue2 = new PdfPCell(new Phrase(String.format("%.2f", totalAmount), getBiggerFont(11)));
	    	PdfPCell paymentType = new PdfPCell(new Phrase("Payment Type: ", getBiggerFont(11)));
	    	PdfPCell paymentTypeValue = new PdfPCell(new Phrase(payment.getPaymentType().toUpperCase(), getBiggerFont(11)));
	    	PdfPCell toBePaid = new PdfPCell(new Phrase("Amount to be Paid:", getBiggerFont(11)));
	    	PdfPCell remainingBalance = new PdfPCell(new Phrase("Remaining Balance:", getBiggerFont(11)));
	    	
	    	
	    	if(payment.getPaymentType().equalsIgnoreCase("HALF PAYMENT")) {
	    		finalPrice /= 2;
	    		change = paymentAmount - (totalAmount / 2);	
	    		remainingBalances = finalPrice;
	    			
	    	} else if(payment.getPaymentType().equalsIgnoreCase("COMPLEMENTARY PAYMENT")) {
	    		finalPrice /= 2;
	    	}
	    	PdfPCell remainingBalanceValue = new PdfPCell(new Phrase(String.format("%.2f", remainingBalances), getBiggerFont(11)));
	    	PdfPCell toBePaidValue = new PdfPCell(new Phrase(String.valueOf(String.format("%.2f", finalPrice))));
    	
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
    	
    	paymentType.setBorder(0);
    	paymentTypeValue.setBorder(0);
    	paymentType.setHorizontalAlignment(Element.ALIGN_LEFT);
    	paymentTypeValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	toBePaid.setBorder(0);
    	toBePaidValue.setBorder(0);
    	toBePaid.setHorizontalAlignment(Element.ALIGN_LEFT);
    	toBePaidValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	
    	remainingBalance.setBorder(0);
    	remainingBalanceValue.setBorder(0);
    	remainingBalance.setHorizontalAlignment(Element.ALIGN_LEFT);
    	remainingBalanceValue.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	
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
            details.addCell(paymentType);
            details.addCell(paymentTypeValue);
            details.addCell(toBePaid);
            details.addCell(toBePaidValue);
       // }
        details.addCell(cashLabel);
        details.addCell(cashValue);
        details.addCell(changeLabel);
        details.addCell(changeValue);
        details.addCell(remainingBalance);
        details.addCell(remainingBalanceValue);
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
