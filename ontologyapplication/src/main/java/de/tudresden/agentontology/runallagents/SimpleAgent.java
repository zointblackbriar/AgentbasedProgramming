package de.tudresden.agentontology.runallagents;

import jade.core.Agent; 
import jade.core.behaviours.*;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.DFService;

import java.io.IOException;
import java.util.logging.Logger;

import de.tudresden.agentontology.blockchainconnect.BlockchainConnection;



public class SimpleAgent extends Agent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8155950590015567440L;
	private static final Logger LOGGER = java.util.logging.Logger.getLogger(SimpleAgent.class.getName()); 
	private SimpleAgent agent = this;
    private static final int REQUEST_TIME = 30000;
	
	protected void setup() 
	{

		//addBehaviour(new TestAgent(this)); 
		addBehaviour(new TickerBehaviour(this.agent, REQUEST_TIME)  {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6478777024685790254L;

			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				LOGGER.info("Ticking behavior");
                DFAgentDescription agentDesc = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType("blockchain-contact");
                agentDesc.addServices(sd);
                try {
                    DFAgentDescription[] result = DFService.search(myAgent, agentDesc);
                    LOGGER.info("result of DFAgentDescription: " + result);
                    BlockchainConnection blockchainConnection = new BlockchainConnection();
                    blockchainConnection.runBlockchainConnector(); 
                } catch(FIPAException ex)
                {
                	ex.printStackTrace();
                } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
}

