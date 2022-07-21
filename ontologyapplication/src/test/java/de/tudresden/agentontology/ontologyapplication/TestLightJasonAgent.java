package de.tudresden.agentontology.ontologyapplication;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import de.tudresden.agentontology.lightjasonintegration.AgentApp;

public class TestLightJasonAgent {

	@Test
	public void testLightJasonMain() throws Exception {
		System.out.println("main method for Light Jason"); 
		String[] testArgs = {"agentasl/agent_trigger.asl", "10", "10"};
		Assertions.assertEquals(AgentApp.main(testArgs), 1);
	}
}
