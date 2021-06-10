package com.nagarro.javaTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.javaTest.helper.BadRequestException;

public class Utils {
	
	public static boolean toBoolean(Object o) {
		if(o == null) return false;
		if(o instanceof Boolean)
			return ((Boolean)o).booleanValue();
		if(o instanceof Character)
			return 'y' == (Character) o || 'Y' == (Character) o;
		if(o instanceof String ) {
			return !((String)o).isEmpty() && "y".equalsIgnoreCase((String)o);
		}
		if( o instanceof Integer ) return 1 == (Integer)o;
		return false;
	}
	
	public static boolean isNotEmpty(Object o) {
		return ! isEmpty(o);
	}
	
	public static boolean isEmpty(Object o) {
		if(o == null) return true;
		if(o instanceof String)
			return ((String) o).isEmpty();
		if(o instanceof Integer)
			return ((Integer)o).intValue() <= 0;
		if(o instanceof Long)
			return ((Long)o).longValue() <= 0;
		if(o instanceof HashMap<?, ?>)
			return ((HashMap<?, ?>)o).entrySet().size() <= 0;
		if(o instanceof Collection<?>)
			return ((Collection<?>)o).isEmpty();
		return o.toString().trim().isEmpty();
	}
	
	public static String getStackTrace(Throwable ex) {
		StringWriter stringWriter = null;
		PrintWriter printWriter = null;
		try {
			if(ex instanceof BadRequestException)
				return ex.getMessage();
			stringWriter = new StringWriter();
			printWriter = new PrintWriter(stringWriter);
			ex.printStackTrace(printWriter);
			printWriter.close();
			stringWriter.close();
			return stringWriter.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return ex.getMessage() + "Exception occurred while getting Stack Trace of actual Exception";
		} 
		
	}

	public static Object jsonToObject(String json, Class<?> clazz) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json.getBytes(), clazz);
	}
	

	
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public static String toString(Object o) {
		if(isEmpty(o))
			return "";
		if(o instanceof Number)
			return ((Number)o).toString();
		if(o instanceof int[])
			return Arrays.toString((int[]) o);
		if(o instanceof long[])
			return Arrays.toString((int[])o);
		if(o instanceof Object[])
			return Arrays.toString( (Object[])o );
		return o.toString();
	}
}
