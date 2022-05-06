package com.seshu.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonHelper {

	public String getJson(HashMap<String, String> map) {		
		String json = "{\n";		
		int firstLine = 0;
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	    	if(firstLine != 0)
	    		json = json+",\n";
	        Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
	        String value = pair.getValue() == null?"":pair.getValue();
	        json = json+"\""+pair.getKey()+"\": \""+value+"\"";
	        firstLine++;
	    }
	    json = json+"\n}";
		
	    return json;
	}
}