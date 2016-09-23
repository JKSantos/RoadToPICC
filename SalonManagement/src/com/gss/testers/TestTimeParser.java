package com.gss.testers;

import com.gss.utilities.JavaSqlDateTimeHelper;

public class TestTimeParser {
	
	public static void main(String[] args){
		java.sql.Time timee = JavaSqlDateTimeHelper.stringToTime("4:30:00");
		
		System.out.println(timee);
	}

}
