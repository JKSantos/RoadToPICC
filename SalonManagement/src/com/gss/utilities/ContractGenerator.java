package com.gss.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gss.model.Contract;
import com.gss.model.ProductOrder;
import com.gss.model.ReservedPackage;
import com.gss.model.ReservedPromo;
import com.gss.model.ReservedService;
import com.gss.testers.CreateFile;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.prism.paint.Color;

public class ContractGenerator {
	
	private String DESTINATION;
	private Contract contract;

	public String createContract(Contract contract) throws BadElementException, MalformedURLException, DocumentException, IOException{
	
		this.contract = contract;
		this.DESTINATION = "C:/Java/Contracts/"+contract.getReceiverName().replaceAll(" ", "_") + "_"+NumberGenerator.localDateTime() + ".pdf";
		
		Document document = createDocument();
		document.open();
		
		Paragraph contractParagraph = new Paragraph(new Phrase("C O N T R A C T", getBiggerFont(20)));
		contractParagraph.setAlignment(Element.ALIGN_CENTER);
		contractParagraph.setSpacingAfter(30);
		contractParagraph.setSpacingBefore(30);
		
		Paragraph intro1 = new Paragraph(getIntro1());
		intro1.setAlignment(Element.ALIGN_JUSTIFIED);
		intro1.setSpacingAfter(10);
		
		document.add(getHeader());
		document.add(contractParagraph);
		document.add(intro1);
		document.add(getIntro2());
		
		Paragraph scopeAndManner = new Paragraph(new Phrase("Scope and Manner of Reservation", getBiggerFont(13)));
		scopeAndManner.setAlignment(Element.ALIGN_CENTER);
		scopeAndManner.setSpacingBefore(15);
		scopeAndManner.setSpacingAfter(10);
		
		document.add(scopeAndManner);
		
		if(contract.getReservation().getIncludedItems().getProductList().size() > 0){
			document.add(getProductTable());
		}
		if(contract.getReservation().getIncludedItems().getServiceList().size() > 0){
			document.add(getServiceTable());
		}
		
		if(contract.getReservation().getIncludedItems().getPackageList().size() > 0){
			document.add(getPackageTable());
		}
		if(contract.getReservation().getIncludedItems().getPromoList().size() > 0){
			document.add(getPromoTable());
		}
		
		Paragraph payment = new Paragraph(new Phrase("Payment for Services/Products Rendered", getBiggerFont(13)));
		payment.setAlignment(Element.ALIGN_CENTER);
		payment.setSpacingAfter(10);
		
		document.add(payment);
		document.add(getPaymentDetails());
		Paragraph terms = new Paragraph(new Phrase("Reservation Terms and Conditions", getBiggerFont(13)));
		terms.setAlignment(Element.ALIGN_CENTER);
		terms.setSpacingAfter(10);
		
		document.add(terms);
		document.add(getFirstTerm());
		document.add(getSecondTerm());
		document.add(getThirdTerm());
		document.add(getFourthTerm());
		
		Paragraph signature = new Paragraph(new Phrase("Signatures", getBiggerFont(13)));
		signature.setAlignment(Element.ALIGN_CENTER);
		signature.setSpacingBefore(30);
		signature.setSpacingAfter(30);
		
		document.add(signature);
		document.add(getSignatureField());
		document.close();
		
		return null;
	}
	
	public Paragraph getIntro2(){
		
		Paragraph paragraph = new Paragraph();
		
		Phrase phrase = new Phrase("The Client hereby engages the Provider to provide services and products described herein under “Scope and Manner of Reservation”, and agrees to terms and agreements of the reservation stated herein under “Reservation Terms and Conditions”. The Provider hereby agrees to provide the Client with such services and products in exchange for consideration described herein under “Payment for Reservation”.", getFont());

		paragraph.add(phrase);
		return paragraph;
	}

	public Paragraph getIntro1(){
		
		Paragraph paragraph = new Paragraph();
		
		Chunk firstSenctence = new Chunk("This is a contract entered into by ", getFont());
		Chunk providerName = new Chunk(String.valueOf(contract.getProviderName()), getBiggerFont(11));
		Chunk and = new Chunk(" and ", getFont());
		Chunk receiverName = new Chunk(String.valueOf(contract.getReceiverName()), getBiggerFont(11));
		Chunk secondSentence = new Chunk(" on this date ", getFont());
		Chunk date = new Chunk(String.valueOf(contract.getDate()) + ".", getBiggerFont(11));
		Chunk thirdSentence = new Chunk(" The Provider’s place of business is ", getFont());
		Chunk providerAddress = new Chunk(String.valueOf(contract.getProviderAddess()), getBiggerFont(11));
		Chunk and2 = new Chunk(" and the Client’s place of business is ", getFont());
		Chunk receiverAddress = new Chunk(String.valueOf(contract.getReceiverAddress()) + ".", getBiggerFont(11));
		
		paragraph.add(firstSenctence);
		paragraph.add(providerName);
		paragraph.add(and);
		paragraph.add(receiverName);
		paragraph.add(secondSentence);
		paragraph.add(date);
		paragraph.add(thirdSentence);
		paragraph.add(providerAddress);
		paragraph.add(and2);
		paragraph.add(receiverAddress);
		
		return paragraph;
		
	}

	public Font getBiggerFont(int size){

	        Font font = FontFactory.getFont(FontFactory.TIMES, size, Font.BOLD);
	        
	        return font;
	}
	
	public Document createDocument(){
		
		Document document = new Document(PageSize.LETTER, 70, 70, 30, 70);
        try {
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DESTINATION));
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	public Paragraph getHeader() throws BadElementException, MalformedURLException, IOException{
//		
//		Image COMPANY_LOGO = CreateFile.getImage();
//		COMPANY_LOGO.scaleAbsolute(80f, 80f);
//		
		Paragraph paragraph = new Paragraph();
		Paragraph headerParagraph = new Paragraph();
		
		float[] width = {(float) 1.15, (float) 1.35, (float) 3.5, (float) 2.5};
		
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell logoCell = new PdfPCell();
		logoCell.addElement(new Phrase(""));
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
	
	public Font getBoldFont(){
	  
	        Font font = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
	        
	        return font;
	}
	
	public Font getFont(){
		Font font = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
        
        return font;
	}

	public Paragraph getProductTable(){
		
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		
		float[] width = {4, 1};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell headCell = new PdfPCell(new Phrase("Product(s)", getBiggerFont(13)));
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		headCell.setBackgroundColor(new GrayColor(0.75f));
		
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setColspan(2);
		
		table.addCell(headCell);
		
		List<ProductOrder> order = contract.getReservation().getIncludedItems().getProductList();
		
		PdfPCell productName = new PdfPCell(new Phrase("Name", getBiggerFont(13)));
		productName.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell productQty = new PdfPCell(new Phrase("Quantity", getBiggerFont(13)));
		productQty.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(productName);
		table.addCell(productQty);
		
		for(int index = 0; index < order.size(); index++){
			PdfPCell productName1 = new PdfPCell(new Phrase(order.get(index).getProduct().getStrProductName(), getFont()));
			productName1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell productQty1 = new PdfPCell(new Phrase(String.valueOf(order.get(index).getIntQuantity()), getFont()));
			productQty1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(productName1);
			table.addCell(productQty1);
		}
		
		paragraph.add(new Phrase("Services/Products to be rendered by Provider:", getFont()));
		paragraph.add(table);
		
		return paragraph;
	}

	public Paragraph getServiceTable(){
		
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		
		float[] width = {4, 1};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell headCell = new PdfPCell(new Phrase("Services(s)", getBiggerFont(13)));
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		headCell.setBackgroundColor(new GrayColor(0.75f));
		
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setColspan(2);
		
		table.addCell(headCell);
		
		List<ReservedService> order = contract.getReservation().getIncludedItems().getServiceList();
		
		PdfPCell productName = new PdfPCell(new Phrase("Name", getBiggerFont(13)));
		productName.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell productQty = new PdfPCell(new Phrase("Quantity", getBiggerFont(13)));
		productQty.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(productName);
		table.addCell(productQty);
		
		for(int index = 0; index < order.size(); index++){
			PdfPCell productName1 = new PdfPCell(new Phrase(order.get(index).getService().getStrServiceName(), getFont()));
			productName1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell productQty1 = new PdfPCell(new Phrase(String.valueOf(order.get(index).getIntQuantity()), getFont()));
			productQty1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(productName1);
			table.addCell(productQty1);
		}
		
		paragraph.add(table);
		
		return paragraph;
	}

	public Paragraph getPackageTable(){
		
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		
		float[] width = {4, 1};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell headCell = new PdfPCell(new Phrase("Package(s)", getBiggerFont(13)));
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		headCell.setBackgroundColor(new GrayColor(0.75f));
		
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setColspan(2);
		
		table.addCell(headCell);
		
		List<ReservedPackage> order = contract.getReservation().getIncludedItems().getPackageList();
		
		PdfPCell productName = new PdfPCell(new Phrase("Name", getBiggerFont(13)));
		productName.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell productQty = new PdfPCell(new Phrase("Quantity", getBiggerFont(13)));
		productQty.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(productName);
		table.addCell(productQty);
		
		for(int index = 0; index < order.size(); index++){
			PdfPCell productName1 = new PdfPCell(new Phrase(order.get(index).getPackages().getStrPackageName(), getFont()));
			productName1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell productQty1 = new PdfPCell(new Phrase(String.valueOf(order.get(index).getIntQuantity()), getFont()));
			productQty1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(productName1);
			table.addCell(productQty1);
		}
		
		paragraph.add(table);
		
		return paragraph;
	}

	public Paragraph getPromoTable(){

		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		
		float[] width = {4, 1};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell headCell = new PdfPCell(new Phrase("Promo(s)", getBiggerFont(13)));
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);
		headCell.setBackgroundColor(new GrayColor(0.75f));
		
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		headCell.setColspan(2);
		
		table.addCell(headCell);
		
		List<ReservedPromo> order = contract.getReservation().getIncludedItems().getPromoList();
		
		PdfPCell productName = new PdfPCell(new Phrase("Name", getBiggerFont(13)));
		productName.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell productQty = new PdfPCell(new Phrase("Quantity", getBiggerFont(13)));
		productQty.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		table.addCell(productName);
		table.addCell(productQty);
		
		for(int index = 0; index < order.size(); index++){
			PdfPCell productName1 = new PdfPCell(new Phrase(order.get(index).getPromo().getStrPromoName(), getFont()));
			productName1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell productQty1 = new PdfPCell(new Phrase(String.valueOf(order.get(index).getIntQuantity()), getFont()));
			productQty1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(productName1);
			table.addCell(productQty1);
		}
		
		paragraph.add(table);
		
		return paragraph;
	}

	public Paragraph getPaymentDetails(){
		
		Paragraph paragraph = new Paragraph(new Phrase("The Client shall pay the Provider for services/products rendered provided according to the Payment Schedule attached, within the date stated on any invoice for the reservation. ", getFont()));
		
		paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
		paragraph.setSpacingAfter(15);
		
		return paragraph;
	}

	public Paragraph getFirstTerm(){
		
		Paragraph paragraph = new Paragraph();
		
		Chunk first = new Chunk("1. The ", getFont());
		Chunk totalPayment = new Chunk("TOTAL PAYMENT", getBoldFont());
		Chunk second = new Chunk(" for the event service will be ", getFont());
		Chunk twice = new Chunk("TWICE", getBoldFont());
		Chunk third = new Chunk(" the ", getFont());
		Chunk totalPrice = new Chunk("TOTAL PRICE", getBoldFont());
		Chunk fourth = new Chunk(" of the ", getFont());
		Chunk serviceProduct = new Chunk("SERVICES/PRODUCTS", getBoldFont());
		Chunk fifth = new Chunk(" included in the reservation.", getFont());

		paragraph.add(first);
		paragraph.add(totalPayment);
		paragraph.add(second);
		paragraph.add(twice);
		paragraph.add(third);
		paragraph.add(totalPrice);
		paragraph.add(fourth);
		paragraph.add(serviceProduct);
		paragraph.add(fifth);
		
		return paragraph;
	}

	public Paragraph getSecondTerm(){

		Paragraph paragraph = new Paragraph();
		
		Chunk first = new Chunk("2. The ", getFont());
		Chunk totalPayment = new Chunk("DOWN PAYMENT", getBoldFont());
		Chunk second = new Chunk(" for the reservation, which is ", getFont());
		Chunk twice = new Chunk("50%", getBoldFont());
		Chunk third = new Chunk(" of the total price, should be paid upon creation of reservation. The other ", getFont());
		Chunk totalPrice = new Chunk("50%", getBoldFont());
		Chunk fourth = new Chunk(" should be paid on the ", getFont());
		Chunk serviceProduct = new Chunk("EVENT DATE", getBoldFont());

		paragraph.add(first);
		paragraph.add(totalPayment);
		paragraph.add(second);
		paragraph.add(twice);
		paragraph.add(third);
		paragraph.add(totalPrice);
		paragraph.add(fourth);
		paragraph.add(serviceProduct);
		
		return paragraph;
	}

	public Paragraph getThirdTerm(){

		Paragraph paragraph = new Paragraph();
		
		Chunk first = new Chunk("3.	Revisions of the reservation are only allowed until ", getFont());
		Chunk totalPayment = new Chunk("3 DAYS/72 HOURS", getBoldFont());
		Chunk second = new Chunk(" before the actual date/time of the event.", getFont());

		paragraph.add(first);
		paragraph.add(totalPayment);
		paragraph.add(second);
		
		return paragraph;
	}

	public Paragraph getFourthTerm(){
		
		Paragraph paragraph = new Paragraph();
		
		Chunk first = new Chunk("4. Receipts are only given once the transaction is ", getFont());
		Chunk totalPayment = new Chunk("FULLY PAID", getBoldFont());

		paragraph.add(first);
		paragraph.add(totalPayment);
		
		return paragraph;
	}

	public Paragraph getSignatureField(){
		
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingAfter(15);
		paragraph.setSpacingBefore(15); 
		
		float[] width = {1, 1};
		PdfPTable table = new PdfPTable(width);
		table.setWidthPercentage(100);
		
		PdfPCell cell1 = new PdfPCell(new Phrase(String.valueOf(contract.getManagerName()) + ""
				+ "\nAgent, " + contract.getProviderName(), getFont()));
		
		cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		PdfPCell cell2 = new PdfPCell(new Phrase(contract.getReceiverName() + ""
				+ "\nClient", getFont()));
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	
		cell1.setBorder(0);
		cell2.setBorder(0);
		
		table.addCell(cell1);
		table.addCell(cell2);
		
		paragraph.add(table);
		
		return paragraph;
		
	}
}
