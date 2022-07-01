package de.tudresden.agentontology.jadexintegration;

import java.util.logging.Logger;

import jadex.base.PlatformConfigurationHandler;
import jadex.base.Starter;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.types.cms.CreationInfo;

/**
 *  This class allows starting the agent.
 */
public class Main
{
	/**
	 *  Start the platform and also the agent.
	 */
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	public static void main(String[] args)
	{
		logger.info("Platform has been created");
//		Starter.createPlatform(new String[]
//		{
//			"-component", "de.tudresden.agentontology.jadexintegration.QuickstartBDI.class"
//		});
//		IFuture<IExternalAccess> fut = Starter.createPlatform();
//		IExternalAccess platform = fut.get();	
		
		IExternalAccess platform = Starter.createPlatform(PlatformConfigurationHandler.getDefaultNoGui()).get();
		//CreationInfo ci = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/JokeAgent.class");
//		CreationInfo newci = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/HelloAgent.class");
//		CreationInfo helloworldci = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/HelloWorld.class");
//		CreationInfo agentcommonactivity = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/AgentCommonActivity.class");
		CreationInfo agentPainterContainer = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/Scenario.component.xml");
		CreationInfo pureAgentContainer = new CreationInfo().setFilename("de/tudresden/agentontology/jadexintegration/HelloPureAgent.class"); 
		CreationInfo helloPureAgentContainer = new CreationInfo().setFilenameClass(HelloPureGoalAgent.class); 

//		platform.createComponent(newci).get();
//		platform.createComponent(helloworldci).get();
//		platform.createComponent(agentcommonactivity).get();
		platform.createComponent(agentPainterContainer).get();
		platform.createComponent(pureAgentContainer).get();
		platform.createComponent(helloPureAgentContainer).get();
		logger.info("Platform is over");

	}
}
