package de.tudresden.containmentsimulation.misc;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.wrapper.ControllerException;
import java.util.List; 
import java.util.ArrayList; 

public class PathRouter {

	public static void sampleMovementAgent(Agent agent) throws ControllerException {
		ContainerID destination = new ContainerID();
		List containerAttributes = new ArrayList(); 
		
		if(agent.getContainerController().getContainerName().equals("Container-1")) {
			String[] sampleString = {"Main-Container", "Container-3", "Container-4"}; 
			destination.setName("random string of destination");
			
			
		}
		else if(agent.getContainerController().getContainerName().equals("Main-Container")) {
			String[] sampleString = {"Container-1", "Container-2"};
			destination.setName(sampleString[(int)Math.random()]);
		}
		else if(agent.getContainerController().getContainerName().equals("Container-2")) {
			destination.setName("Main-Container");
		}
		else if(agent.getContainerController().getContainerName().equals("Container-3")) {
			destination.setName("Container-1");
		}
		else if(agent.getContainerController().getContainerName().equals("Container-4")) {
			destination.setName("Container-1");
		}
		
		agent.doMove(destination);
	}
}
