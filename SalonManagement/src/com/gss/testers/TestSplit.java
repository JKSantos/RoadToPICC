package com.gss.testers;

public class TestSplit {
	
	public static void main(String[] args){
		
		String str = "5,2:5-3:4-4:5-5:1,2:3-2:4&jjjjj";
		String[] val = str.split("&");
		
		
		System.out.println(val[0] + "-" + val[1]);
	}

}
