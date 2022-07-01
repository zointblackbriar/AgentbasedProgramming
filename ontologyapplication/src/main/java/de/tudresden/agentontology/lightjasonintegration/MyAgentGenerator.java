package de.tudresden.agentontology.lightjasonintegration;

import java.io.InputStream;
import java.util.Set;

import javax.annotation.Nullable;

import org.lightjason.agentspeak.action.IAction;
import org.lightjason.agentspeak.generator.IBaseAgentGenerator;
import java.util.stream.Collectors;
import org.lightjason.agentspeak.common.CCommon;

public class MyAgentGenerator extends IBaseAgentGenerator<MyAgent>{

	public MyAgentGenerator(InputStream p_stream, Set<IAction> p_actions) throws Exception {
		super(p_stream,
				
			  CCommon.actionsFromPackage().collect(Collectors.toSet()));
		// TODO Auto-generated constructor stub
	}

	@Override
	public MyAgent generatesingle(@Nullable final Object... p_data) {
		// TODO Auto-generated method stub
		return new MyAgent(m_configuration);
	}
	
}
