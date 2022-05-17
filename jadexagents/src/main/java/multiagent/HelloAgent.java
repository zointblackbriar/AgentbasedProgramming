package multiagent;

import jadex.base.IPlatformConfiguration;
import jadex.base.PlatformConfigurationHandler;
import jadex.base.Starter;
import jadex.bridge.IInternalAccess;
import jadex.bridge.service.annotation.OnStart;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;

@Agent
public class HelloAgent {
	@OnStart
	void hello(IInternalAccess me)
	{
		me.repeatStep(0, 5000, dummy -> {
			System.out.println("Hello Jadex!");
			return IFuture.DONE;
		});
	}
	
    public static void helloAgentRun( )
    {
        System.out.println( "Hello Jadex Agents!" );
        IPlatformConfiguration config = PlatformConfigurationHandler.getMinimal();
        config.addComponent(App.class);
        Starter.createPlatform(config).get();
    }


}
