package de.tudresden.agentontology.ontologyapplication;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.tudresden.agentontology.blockchainconnect.BlockchainConnection;

public class TestBlockchainConnection {

	@Test public void testBlockchainConnector() throws IOException {
		BlockchainConnection blockchainConnection = new BlockchainConnection();
		Assertions.assertTrue(blockchainConnection.runBlockchainConnector());
	}
}
