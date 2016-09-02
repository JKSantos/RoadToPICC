package com.gss.utilities;

import java.io.File;

public class DefaultImage {

	public static String employee(){
		File file = new File("image/fb.jpg");
		
		System.out.println(file.getAbsolutePath());
		
		return file.getAbsolutePath();
	}
}
