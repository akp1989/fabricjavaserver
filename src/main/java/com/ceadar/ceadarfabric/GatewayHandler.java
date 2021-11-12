package com.ceadar.ceadarfabric;

import java.io.IOException;
import java.nio.file.Path;

import org.hyperledger.fabric.gateway.Gateway;
import org.hyperledger.fabric.gateway.Wallet; 

public class GatewayHandler {
	
	private static Gateway.Builder gatewayBuilder;
	private static Wallet wallet;
	private static Path networkConfigPath;
	private static String clientID;
	
	public static String getClientID() {
		return clientID;
	}

	public static void setClientID(String clientID) {
		GatewayHandler.clientID = clientID;
	}

	public static Gateway buildGateway() throws IOException {
		wallet = WalletHandler.getWallet();
		networkConfigPath = NetworkConfigHandler.getNetworkConfigPath();
		gatewayBuilder = Gateway.createBuilder();
		gatewayBuilder.identity(wallet, getClientID()).networkConfig(networkConfigPath).discovery(false);
		try (Gateway gateway = gatewayBuilder.connect()) {
			return gateway;

		}
	}
	

	 
}
