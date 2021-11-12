package com.ceadar.ripefabric;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Network;
import org.hyperledger.fabric.gateway.Transaction;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.json.JSONArray;

import com.ceadar.ripefabric.GatewayHandler;
import com.ceadar.util.BlockchainUtil;
import com.google.gson.JsonObject;
public class TestChannelHandler {
	
	private static final String channelName = "testchannel";
	
	private static Gateway gateway;
	private static Network network;
	private static Contract contract;
 
	private Transaction invokeTransaction;
	private Transaction queryTransaction;
	private byte[] responseByte = null;
 
	static {
		System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "false");
	}

	public byte[] invokeTransaction(String chaincodeID, String contractID,String transactionName, String clientID,JSONArray arguements,Map<String,byte[]> transientMap) throws ContractException, TimeoutException, InterruptedException, IOException {
		GatewayHandler.setClientID(clientID);
		gateway = GatewayHandler.buildGateway();
		network = gateway.getNetwork(channelName);
		contract = network.getContract(chaincodeID,contractID);
		invokeTransaction = contract.createTransaction(transactionName);
		
		if(!transientMap.isEmpty())
			invokeTransaction.setTransient(transientMap);
		
		String[] arguement = new String[arguements.length()];
		for(int argCount=0;argCount<arguements.length();argCount++) {
			arguement[argCount] = arguements.get(argCount).toString();
		}
  
		responseByte = invokeTransaction.submit(arguement);
		return responseByte;
	}
	
	public byte[] queryTransaction(String chaincodeID, String contractID,String transactionName, String clientID,JSONArray arguements,Map<String,byte[]> transientMap) throws ContractException, TimeoutException, InterruptedException, IOException {
		GatewayHandler.setClientID(clientID);
		gateway = GatewayHandler.buildGateway();
		network = gateway.getNetwork(channelName);
		contract = network.getContract(chaincodeID,contractID);
		
		queryTransaction  = contract.createTransaction(transactionName);
		if(!transientMap.isEmpty())
			queryTransaction.setTransient(transientMap);
		
 		String[] arguement = new String[arguements.length()];
		for(int argCount=0;argCount<arguements.length();argCount++) {
			arguement[argCount] = arguements.get(argCount).toString();
		}
 
		responseByte = queryTransaction.submit(arguement);
		return responseByte;
	}
	
	public JsonObject queryBlock(String clientID,String transactionId) throws IOException, InvalidArgumentException, ProposalException, NoSuchAlgorithmException {
		GatewayHandler.setClientID(clientID);
		gateway = GatewayHandler.buildGateway();
		network = gateway.getNetwork(channelName);
		
		Channel channel = network.getChannel();
		BlockInfo blockInfo = channel.queryBlockByTransactionID(transactionId);
		BlockchainUtil blockchainUtil = new BlockchainUtil();
		return blockchainUtil.processBlock(blockInfo);
	}
}
