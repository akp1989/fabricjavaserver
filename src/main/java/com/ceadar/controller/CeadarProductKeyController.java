package com.ceadar.controller;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.concurrent.TimeoutException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceadar.ceadarfabric.TestChannelHandler;
import com.ceadar.util.TransientMapHandler;
import com.ceadar.util.RSAEncryption;

@RestController
@RequestMapping("/ceadar/productkey")
public class CeadarProductKeyController {
	private static Map<String,byte[]> transientMap;
	static TestChannelHandler testChannelHandler;
	
	private static Encoder encoder = Base64.getEncoder();
	private static Decoder decoder = Base64.getDecoder();
	
	@PostMapping(value="addProductKey",produces="text/plain")
	public String addProductKey(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException {
		
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

		return ("You have hit the productkey contract and the response is :" + new String(result) );
		
	}
	
	@PostMapping(value="addAccessToUser",produces="text/plain")
	public String addAccessToUser(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException {
		
		JSONObject requestJSON = new JSONObject(requestBody);
		
		transientMap = new HashMap<>();
		if(requestJSON.has("transientMap"))
			transientMap = TransientMapHandler.generateTransientMap(requestJSON.getJSONObject("transientMap"));
		 
		testChannelHandler = new TestChannelHandler();
		System.out.println("The arguements used are :" + requestJSON.getJSONArray("arguements").toString());
		byte[] result = testChannelHandler.invokeTransaction(requestJSON.getString("chaincode"),
					requestJSON.getString("contract"),
					requestJSON.getString("transaction"),
					requestJSON.getString("clientID"),
					requestJSON.getJSONArray("arguements"),
					transientMap);

		return ("You have hit the productkey contract and the response is :" + new String(result) );
		
	}
	
	@PostMapping(value="removeAccessToUser",produces="text/plain")
	public String removeAccessToUser(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException {
		
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

		return ("You have hit the productkey contract and the response is :" + new String(result) );
		
	}
	
	@PostMapping(value="readProductKey",produces="text/plain")
	public String readProductKey(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException {
		
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
	
	@PostMapping(value="readProductKeyPrivate",produces="text/JSON")
	public String readProductKeyPrivate(@RequestBody String requestBody) throws ContractException, JSONException, TimeoutException, InterruptedException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
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
		 
		
//		String encodedKey = requestJSON.getString("key");
//		RSAEncryption rsaEncryption = new RSAEncryption();
//		PrivateKey privateKey = rsaEncryption.getPrivateKeyFromByte(decoder.decode(encodedKey));
//		Set<String> resultJSONKeys = jsonObject.keySet();
//		
//		for(String key : resultJSONKeys) {
//			System.out.println(key);
//			String temp = new String(rsaEncryption.decryptionRSA(privateKey, decoder.decode(jsonObject.get(key).toString())),UTF_8).trim();
//			jsonObject.put(key+"_decrypted", temp);
//			System.out.println(temp);
//		}
	
		return (new String(result));
		
	}
	
	@PostMapping(value="getKeyHistory",produces="text/JSON")
	public String getKeyHistory(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException, InvalidArgumentException, ProposalException, NoSuchAlgorithmException, JSONException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);
		
		testChannelHandler = new TestChannelHandler();
		byte[] result = testChannelHandler.invokeTransaction(requestJSON.getString("chaincode"),
					requestJSON.getString("contract"),
					requestJSON.getString("transaction"),
					requestJSON.getString("clientID"),
					requestJSON.getJSONArray("arguements"),
					transientMap);
		JSONObject jsonObject = new JSONObject(new String(result));
		return (jsonObject.toString());
	}
	
	@PostMapping(value="getTransactionHistory",produces="text/JSON")
	public String getTransactionHistory(@RequestBody String requestBody) throws ContractException, TimeoutException, InterruptedException, IOException, InvalidArgumentException, ProposalException, NoSuchAlgorithmException, JSONException {
 		
		JSONObject requestJSON = new JSONObject(requestBody);
		testChannelHandler = new TestChannelHandler();
		return (testChannelHandler.queryBlock(requestJSON.getString("clientID"),requestJSON.getString("transactionID")).toString());
	}
}
