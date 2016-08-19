package com.gss.testers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CreateFile {
	
	public static void main(String[] args) throws FileNotFoundException{
		
		File file = new File("resource/blah.txt");
		
		FileOutputStream stream = new FileOutputStream(file);
	}

}
