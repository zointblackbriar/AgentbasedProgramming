package de.tudresden.agentontology.lightjasonintegration;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AgentApp {

	static {
		LogManager.getLogManager().reset();
	}

	private AgentApp() {

	}

	public static void main(String[] args) 
	{
		if (args.length > 2) {
			throw new RuntimeException("arguments are not set: ASL script, number of agents");
		}
			Set<MyAgent> lAgents = null;

			try {
				final FileInputStream lStream = new FileInputStream(args[0]);

				lAgents = Collections.unmodifiableSet(new MyAgentGenerator(lStream, null)
						.generatemultiple(Integer.parseInt(args[1])).collect(Collectors.toSet()));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
//		    IntStream
//		    .range(
//		        0,
//		        args.length < 3
//		        ? Integer.MAX_VALUE
//		        : Integer.parseInt( args[2] )
//		    )
//		    .forEach( j -> lAgents.parallelStream()
//		                           .forEach( i ->
//		                           {
//		                               try
//		                               {
//		                                   i.call();
//		                               }
//		                               catch ( final Exception l_exception )
//		                               {
//		                                   l_exception.printStackTrace();
//		                                   throw new RuntimeException();
//		                               }
//		                           } ) );
		}

	}

