package com.gss.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReceiptThread {
	
	public static void main(String[] args) throws IOException{
		File f = new File("resource/Receipts/jeff.txt");
		
		FileOutputStream out = new FileOutputStream(f);
	}

}
