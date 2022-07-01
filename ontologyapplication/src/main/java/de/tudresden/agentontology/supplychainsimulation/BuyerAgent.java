package de.tudresden.agentontology.supplychainsimulation;

import java.util.logging.Logger;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class BuyerAgent extends Agent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2642614801877441899L;
	private static final Logger LOGGER = Logger.getLogger(BuyerAgent.class.getName());
	private String productForBuying; 
	
	private AID[] sellerAgents; 
	
	private static final int REQUEST_TIME = 30000; 
	
	protected void setup() {
		LOGGER.info("Buyer Agent: " + getAID().getName() + "was created"); 
		
		Object[] args = getArguments(); 
		
		if(args != null && args.length > 0) {
			productForBuying = (String) args[0]; 
			System.out.println("P rroduct wanted: " + productForBuying);
		
			addBehaviour(new TickerBehaviour(this, REQUEST_TIME) {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void onTick() {
					// TODO Auto-generated method stub
					System.out.println("We are buying: " + productForBuying);
					DFAgentDescription agentDescription = new DFAgentDescription(); 
					ServiceDescription sd = new ServiceDescription(); 
					sd.setType("product-sale");
					agentDescription.addServices(sd);
					try {
						DFAgentDescription[] result = DFService.search(myAgent, agentDescription); 
						LOGGER.info("Product found");
						sellerAgents = new AID[result.length];
						for(int i = 0; i < result.length; ++i) {
							sellerAgents[i] = result[i].getName(); 
							System.out.println(sellerAgents[i].getName());
						}
					} catch(Exception ex)
					{
						ex.printStackTrace();
					}
					
					//perform the request
					myAgent.addBehaviour(new PurchaseRequest());
				}
			});
		}
		
	}
	
	private class PurchaseRequest extends Behaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1030966029278595630L;
		
		private AID bestseller; 
		private int bestPrice; 
		private int repliesCounter = 0; 
		private MessageTemplate messageTemplate; 
		private int step = 0; 

		@Override
		public void action() {
			// TODO Auto-generated method stub
			switch(step) {
			case 0: LOGGER.info("case 0"); 
				ACLMessage sampleMessage = new ACLMessage(ACLMessage.CFP); 
				for(int i = 0; i < sellerAgents.length; i++) {
					sampleMessage.addReceiver(sellerAgents[i]);
				}
				sampleMessage.setContent(productForBuying);
				sampleMessage.setConversationId("Product-Commerce"); 
				sampleMessage.setReplyWith("sample message:" + System.currentTimeMillis());
				myAgent.send(sampleMessage); 
				messageTemplate = MessageTemplate.and(MessageTemplate.MatchConversationId("Product-Commerce"), MessageTemplate.MatchInReplyTo(sampleMessage.getReplyWith())); 
				step = 1; 
				break; 
			case 1: LOGGER.info("case 1"); break; 
			case 2: LOGGER.info("case 2"); break; 
			case 3: LOGGER.info("here we have case3"); break; 
			}
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			if(step == 1) 
				return true; 
			return false;
		}

	}

}
