package com.ceadar.ripefabric;
import java.io.IOException;
import java.nio.file.Paths;

import org.hyperledger.fabric.gateway.Wallet;

public class WalletHandler {

	private static Wallet wallet;
	
	public static Wallet getWallet() throws IOException {
		wallet = Wallet.createFileSystemWallet(Paths.get("ripe","gatewayWalletTest"));
		return wallet;
	}
}
