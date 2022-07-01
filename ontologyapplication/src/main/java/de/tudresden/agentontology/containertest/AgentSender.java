package de.tudresden.agentontology.containertest;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgentSender extends Agent{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4867489767103545661L;

	@Override
    public void setup()
    {
        System.out.println("The agent name is: " +getAID().getName());
        ACLMessage message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("AgentReceiver", AID.ISLOCALNAME));
        message.setContent("Hello. AgentReceiver agent speaks here");
        send(message);
    }
}
