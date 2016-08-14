package com.gss.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gss.model.Contract;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.prism.paint.Color;

public class ContractGenerator {
	
	private final String DESTINATION = "C:/java/Contract.pdf";
	private Contract contract;

	public String createContract(Contract contract) throws BadElementException, MalformedURLException, DocumentException, IOException{
	
		this.contract = contract;
		
		Document document = createDocument();
		document.open();
		
		Paragraph contractParagraph = new Paragraph(new Phrase("C O N T R A C T", getBiggerFont(20)));
		contractParagraph.setAlignment(Element.ALIGN_CENTER);
		contractParagraph.setSpacingAfter(30);
		contractParagraph.setSpacingBefore(30);
		
		Paragraph intro1 = new Paragraph(getIntro1());
		intro1.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
		//Paragraph intro2 = new Paragraph(getIntro2());
//		Paragraph intro3 = new Paragraph(getIntro3());
		
		document.add(getHeader());
		document.add(contractParagraph);
		document.add(getIntro1());
		document.close();
		
		return null;
	}
	
	public Paragraph getIntro2(){
		return null;
	}

	public Paragraph getIntro1(){
		
		Paragraph paragraph = new Paragraph();
		
		Chunk firstSenctence = new Chunk("This is a contract entered into by ", getFont());
		Chunk providerName = new Chunk(String.valueOf(contract.getProviderName()), getBiggerFont(11));
		Chunk and = new Chunk(" and ", getFont());
		Chunk receiverName = new Chunk(String.valueOf(contract.getReceiverName()), getBiggerFont(11));
		Chunk secondSentence = new Chunk(" on this date ", getFont());
		Chunk date = new Chunk(String.valueOf(contract.getDate()), getBiggerFont(11));
		
		paragraph.add(firstSenctence);
		paragraph.add(providerName);
		paragraph.add(and);
		paragraph.add(receiverName);
		paragraph.add(secondSentence);
		paragraph.add(date);
		
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
		
		Image COMPANY_LOGO = Image.getInstance("resource/Company Profile/Company_Logo.jpg");
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
	
	public Font getBoldFont(){
	    	FontFactory.register("resource/Available Fonts/DejaVuSans-Oblique.ttf", "DEJAVU_SANS");
	        FontFactory.register("resource/Available Fonts/fake receipt.ttf", "RECEIPT");
	        FontFactory.register("resource/Available Fonts/dotty.ttf", "DOTTY");
	        FontFactory.register("resource/Available Fonts/Merchant Copy Wide.ttf", "8PIN");
	        Font font = FontFactory.getFont(FontFactory.TIMES, 11, Font.BOLD);
	        
	        return font;
	}
	
	public Font getFont(){
		Font font = FontFactory.getFont(FontFactory.TIMES, 11, Font.NORMAL);
        
        return font;
	}

}
