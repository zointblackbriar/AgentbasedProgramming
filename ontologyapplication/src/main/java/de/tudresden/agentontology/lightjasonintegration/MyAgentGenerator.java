package de.tudresden.agentontology.lightjasonintegration;


import org.lightjason.agentspeak.common.CCommon;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.stream.Collectors;


/**
 * agent generator to create agents
 */
final class MyAgentGenerator extends IBaseAgentGenerator<MyAgent>
{
    /**
     * @param p_stream ASL code as any stream e.g. FileInputStream
     */
    MyAgentGenerator( @Nonnull final InputStream p_stream ) throws Exception
    {
        super(
            // input ASL stream
            p_stream,

            // we use all built-in actions of LightJason
            CCommon.actionsFromPackage().collect( Collectors.toSet() ),

            // variable builder for agent name
            new CVariableBuilder()
        );
    }

    /**
     * generator method of the agent
     *
     * @param p_data any data which can be put from outside to the generator method
     * @return returns an agent
     */
    @Nullable
    @Override
    public final MyAgent generatesingle( @Nullable final Object... p_data )
    {
        return new MyAgent( m_configuration );
    }
}
