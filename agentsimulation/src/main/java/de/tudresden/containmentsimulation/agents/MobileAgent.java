package de.tudresden.containmentsimulation.agents;

import java.util.logging.Logger;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MobileAgent extends Agent{
	private static final Logger logger = Logger.getLogger(MobileAgent.class.getName());
	public boolean virusExistingCheck(String name, ContainerController container) {
		try {
			AgentController virusAgent = container.getAgent(name);
			logger.info("virusAgent: " + virusAgent.getName());
		} catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	protected void setup() {
		logger.info("this is the installation of the agent" + this.getAID().getName());
		final Agent aux = this; 
		
		//add a behavior
		addBehaviour(new OneShotBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				try {
					logger.info("we are waiting at this point");
				} catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
	}
}
