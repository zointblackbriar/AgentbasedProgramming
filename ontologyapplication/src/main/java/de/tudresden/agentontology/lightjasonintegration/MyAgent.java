package de.tudresden.agentontology.lightjasonintegration;

import org.lightjason.agentspeak.agent.IBaseAgent;
import org.lightjason.agentspeak.configuration.IAgentConfiguration;
import javax.annotation.Nonnull;
final class MyAgent extends IBaseAgent<MyAgent>
{
    
    private static final long serialVersionUID = -2111543876806742109L;
    
    MyAgent( @Nonnull final IAgentConfiguration<MyAgent> p_configuration )
    {
        super( p_configuration );
    }
}