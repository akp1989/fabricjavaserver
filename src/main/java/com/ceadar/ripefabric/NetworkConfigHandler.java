package com.ceadar.ripefabric;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetworkConfigHandler {
	
	private static Path networkConfigPath;
	
	public static Path getNetworkConfigPath() {
		networkConfigPath = Paths.get("ripe", "fabric-network", "connection.yaml");
		return networkConfigPath;
	}
}
