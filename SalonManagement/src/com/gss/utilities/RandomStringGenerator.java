package com.gss.utilities;

import java.security.SecureRandom;
import java.math.BigInteger;

public class RandomStringGenerator {
	
private static SecureRandom random = new SecureRandom();

	public static String generateRandomString(int size) {
		String s =  new BigInteger(130, random).toString(32);
		String string = s.substring(0, Math.min(s.length(), size));
		
		return string.toUpperCase();
	}
	
}
