package de.tudresden.agentplatform;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class MainContainer {
	public static void main(String[] args) {
		try {
			Runtime runtime = Runtime.instance(); 
			Properties properties = new ExtendedProperties(); 
			properties.setProperty(Profile.GUI, "true"); 
			Profile profile = new ProfileImpl(properties); 
			AgentContainer mainContainer = runtime.createMainContainer(profile); 
			
			AgentController fixAgent = mainContainer.createNewAgent("fixAgent", "agents.FixAgent", new Object[] {});
			AgentController mobileAgent = mainContainer.createNewAgent(null, null, new Object[] {}); 
			
			fixAgent.start(); 
			mobileAgent.start(); 
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
