//package de.tudresden.agentontology.ontologyapplication;
//
//import java.awt.EventQueue;
//
//import jade.core.Profile;
//import jade.core.ProfileImpl;
//import jade.core.Runtime;
//import jade.wrapper.AgentController;
//import jade.wrapper.ContainerController;
//import jade.wrapper.StaleProxyException;
//
//import java.util.logging.Logger;
//
//public class App {
//	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
//
//	public static void main(String args[]) throws StaleProxyException {
//		// Initialize log
//		LOGGER.info("Starting platform...");
//		Runtime runtime = Runtime.instance();
//
//		Profile p = new ProfileImpl();
//		p.setParameter(Profile.MAIN_HOST, "localhost");
//		p.setParameter(Profile.MAIN_PORT, "3250");
//		p.setParameter(Profile.GUI, "true");
//
//		Runtime r = Runtime.instance();
//		AgentController agentBankClientController, agentBankServerController;
//		final ContainerController containerController = runtime.createMainContainer(p);
//		agentBankClientController = containerController.createNewAgent("BankClient",
//				"de.tudresden.agentontology.ontologyapplication.BankClient", null);
//		agentBankServerController = containerController.createNewAgent("BankServer",
//				"de.tudresden.agentontology.ontologyapplication.BankServer", null);
//
//		LOGGER.info("Creating Platfrom Manager UI Starter thread...");
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LOGGER.info("Starting Platform Manager UI...");
////		          PlatformManager frame = new PlatformManager(cc);
////		          frame.setVisible(true);
//					agentBankClientController.start();
//					agentBankServerController.start();
//				} catch (Exception e) {
//					LOGGER.severe("Couldn't start Platform Manage UI!");
//				}
//			}
//		});
//	}
//
//}
