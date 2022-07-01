package de.tudresden.agentontology.jadexintegration;

import jadex.micro.annotation.Agent;

import java.util.logging.Logger;

import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.runtime.BDIAgent;
import jadex.bridge.service.annotation.OnStart;
import jadex.bdiv3.annotation.Trigger;


@Agent(type=BDIAgentFactory.TYPE)
public class HelloPureAgent extends BDIAgent{
	private static final Logger logger = Logger.getLogger(HelloPureAgent.class.getName());
	
	
	@Belief
	private String sayhello;
	
	@OnStart
	public void body() {
		setBeliefValue("sayhello", "Hello BDI pure agent v3.");
		logger.info("body end: " + getClass().getName());
	}
	
	@Plan(trigger=@Trigger(factchanged="sayhello")) 
	protected void printHelloFromAgent() {
		logger.info("plan activated: " + sayhello);
	}
	
	
}
