package de.tudresden.agentplatform;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;


public class VirusContainer {

	public static void main(String[] args) {
		try {
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);

            // attachons l'agent VIRUS au container VirusedContainer
            AgentController virusedAgent = agentContainer.createNewAgent("VIRUS", "agents.Virus", new Object[]{});

            String agentName = "agent" + agentContainer.getContainerName();

            AgentController simpleAgent = agentContainer.createNewAgent(agentName, "agents.SimpleAgent", new Object[]{"isContaminated", true}) ;
            simpleAgent.start();
            virusedAgent.start();			
			
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
