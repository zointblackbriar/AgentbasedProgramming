package de.tudresden.agentplatform;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class SimpleContainer {
	
	public static void main(String[] args)
	{
		try {
			Runtime runtime = Runtime.instance(); 
			Profile profile = new ProfileImpl(false);
			profile.setParameter(Profile.MAIN_HOST, "localhost");
			AgentContainer agentContainer = runtime.createAgentContainer(profile);
			
			//Get container name()
			String agentName = "agent" + agentContainer.getContainerName(); 
			
			AgentController simpleAgent = agentContainer.createNewAgent(agentName, "agents.SimpleAgent", new Object[] {"isContaminated", true});
			simpleAgent.start(); 
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
