package de.tudresden.containmentsimulation.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class StationAgent extends Agent{

	@Override protected void setup() {
		System.out.println("I am stationed agent");
		addBehaviour(new CyclicBehaviour() {
			
			@Override
			public void action() {
				// TODO Auto-generated method stub
				ACLMessage message = receive();
				if(message != null) {
					System.out.println("receiving message: " + message.getContent() + "From" + message.getSender().getName());
				} else {
					block(); 
				}
			}
		});
	}
}
