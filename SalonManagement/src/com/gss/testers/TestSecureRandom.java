package com.gss.testers;

import java.security.SecureRandom;
import java.math.BigInteger;

public class TestSecureRandom {
	
	

	  private static SecureRandom random = new SecureRandom();

	  public static void main(String[] args) {
		String s =  new BigInteger(130, random).toString(32);
				String upToNCharacters = s.substring(0, Math.min(s.length(), 6));
		
	    System.out.println(upToNCharacters);
	  }
	}


