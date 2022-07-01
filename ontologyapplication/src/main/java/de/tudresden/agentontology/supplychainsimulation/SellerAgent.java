package de.tudresden.agentontology.supplychainsimulation;

import java.util.Hashtable;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;


public class SellerAgent extends Agent{
	
	private Hashtable inventory; 
	private SaleGUI mygui; 
	
	protected void setup() {
		System.out.println("Seller Agent: " + getAID().getName() + "was created");
		inventory = new Hashtable();
		
		mygui = new SaleGUI(this); 
		mygui.showGUI();
        // Register the book-selling service in the yellow pages
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("product-sale");
        sd.setName("Investment portfolio JADE");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

	}
}
