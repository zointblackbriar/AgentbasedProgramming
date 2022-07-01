package de.tudresden.agentontology.containertest;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgentReceiver extends Agent{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -421770212415275887L;

	@Override
    public void setup()
    {
        System.out.println("The agent name is: " +getAID().getName());
        ACLMessage msg = null; 
        msg = blockingReceive(); 
        System.out.println(msg.getContent());
    }
}       
