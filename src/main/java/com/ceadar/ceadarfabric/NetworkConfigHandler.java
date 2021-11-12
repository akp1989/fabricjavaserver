package com.ceadar.ceadarfabric;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetworkConfigHandler {
	
	private static Path networkConfigPath;
	
	public static Path getNetworkConfigPath() {
		networkConfigPath = Paths.get("ceadar", "fabric-network", "connection.yaml");
		return networkConfigPath;
	}
}
