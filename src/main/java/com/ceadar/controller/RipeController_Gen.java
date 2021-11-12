package com.ceadar.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceadar.ripefabric.TestChannelHandler;
import com.ceadar.util.TransientMapHandler;


@RestController
@RequestMapping("ripe_gen")
public class RipeController_Gen {
	private static Map<String,byte[]> transientMap;
	static TestChannelHandler testChannelHandler;
	
	@PostMapping(value="assetCreate",produces="text/plain")
	public String invokeTransaction(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException {
		
		JSONObject requestJSON = new JSONObject(requestBody);
		
		transientMap = new HashMap<>();
		if(requestJSON.has("transientMap"))
			transientMap = TransientMapHandler.generateTransientMap(requestJSON.getJSONObject("transientMap"));
		 
		testChannelHandler = new TestChannelHandler();
		byte[] result = testChannelHandler.invokeTransaction(requestJSON.getString("chaincode"),
					requestJSON.getString("contract"),
					requestJSON.getString("transaction"),
					requestJSON.getString("clientID"),
					requestJSON.getJSONArray("arguements"),
					transientMap);

		return (new String(result) );
		
	}
	
	@PostMapping(value="assetRead",produces="text/plain")
	public String assetRead(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);

		
		transientMap = new HashMap<>();
		if(requestJSON.has("transientMap"))
			transientMap = TransientMapHandler.generateTransientMap(requestJSON.getJSONObject("transientMap"));

		testChannelHandler = new TestChannelHandler();
	    byte[] result = testChannelHandler.queryTransaction(requestJSON.getString("chaincode"),
	    							requestJSON.getString("contract"),
	    							requestJSON.getString("transaction"),
	    							requestJSON.getString("clientID"),
	    							requestJSON.getJSONArray("arguements"),
	    							transientMap);
	 
	    return (new String(result));
	}
	
	@PostMapping(value="assetUpdate",produces="text/plain")
	public String assetUpdate(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);
		transientMap = new HashMap<>();
		if(requestJSON.has("transientMap"))
			transientMap = TransientMapHandler.generateTransientMap(requestJSON.getJSONObject("transientMap"));
		 
		testChannelHandler = new TestChannelHandler();
		byte[] result = testChannelHandler.invokeTransaction(requestJSON.getString("chaincode"),
					requestJSON.getString("contract"),
					requestJSON.getString("transaction"),
					requestJSON.getString("clientID"),
					requestJSON.getJSONArray("arguements"),
					transientMap);
	    return (new String(result));
	}
	
	
	@PostMapping(value="readHistory",produces="text/plain")
	public String assetHistory(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);
		
		transientMap = new HashMap<>();
		if(requestJSON.has("transientMap"))
			transientMap = TransientMapHandler.generateTransientMap(requestJSON.getJSONObject("transientMap"));

		testChannelHandler = new TestChannelHandler();
	    byte[] result = testChannelHandler.queryTransaction(requestJSON.getString("chaincode"),
	    							requestJSON.getString("contract"),
	    							requestJSON.getString("transaction"),
	    							requestJSON.getString("clientID"),
	    							requestJSON.getJSONArray("arguements"),
	    							transientMap);
	 
	    return (new String(result));
	}
	
	@PostMapping(value="getTransactionHistory",produces="text/JSON")
	public String getTransactionHistory(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException, InvalidArgumentException, ProposalException, NoSuchAlgorithmException, JSONException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);
		testChannelHandler = new TestChannelHandler();
		return (testChannelHandler.queryBlock(requestJSON.getString("clientID"),requestJSON.getString("transactionID")).toString());
	}
}
