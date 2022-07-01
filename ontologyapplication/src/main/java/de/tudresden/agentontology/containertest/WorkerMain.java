//package de.tudresden.agentontology.containertest;
//
//import jade.core.Runtime; 
//import jade.core.Profile;
//import jade.core.ProfileImpl;
//import jade.wrapper.AgentController;
//import jade.wrapper.ContainerController;
//import jade.wrapper.StaleProxyException;
//
//public class WorkerMain {
//	public static void main(String[] args) throws StaleProxyException {
//		Runtime runtime = Runtime.instance(); 
//		
//		Profile profile = new ProfileImpl();
//		profile.setParameter(Profile.MAIN_HOST, "localhost");
//		profile.setParameter(Profile.GUI, "true");
//        ContainerController containerController = runtime.createMainContainer(profile);
//        AgentController agentReceiverController, agentSenderController;
//        agentReceiverController = containerController.createNewAgent("AgentReceiver",
//							"de.tudresden.agentontology.containertest.AgentReceiver", null); 
//        agentSenderController = containerController.createNewAgent("AgentSender",
//							"de.tudresden.agentontology.containertest.AgentSender", null); 
//        agentReceiverController.start();
//        agentSenderController.start();
//	}
//}
