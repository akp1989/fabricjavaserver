package com.ceadar.util;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class TransientMapHandler {
	
	private static Map<String,byte[]>transientMap;
	
	public static Map<String,byte[]> generateTransientMap(JSONObject transientJSON){
		transientMap = new HashMap<>();
		
		transientJSON.keySet().forEach(
				(k)->transientMap.put(k,transientJSON.getString(k).getBytes()));
		
		return transientMap;
	}

}
