package de.tudresden.agentontology.lightjasonintegration;

import org.lightjason.agentspeak.language.CLiteral;
import org.lightjason.agentspeak.language.CRawTerm;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.CTrigger;
import org.lightjason.agentspeak.language.instantiable.plan.trigger.ITrigger;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TriggerAgentApp {

	static {
		LogManager.getLogManager().reset();
	}

	private TriggerAgentApp() {

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			throw new RuntimeException("arguments are not set: ASL Script, number of agents");
		}

		final Set<MyAgent> agentSample;
		try (final FileInputStream stream = new FileInputStream(args[0])) {
			agentSample = Collections.unmodifiableSet(new MyAgentGenerator(stream)
					.generatemultiple(Integer.parseInt(args[1])).collect(Collectors.toSet()));
			// runtime call (with parallel execution)
			IntStream.range(0, args.length < 3 ? Integer.MAX_VALUE : Integer.parseInt(args[2]))
					.forEach(j -> agentSample.parallelStream().forEach(i -> {
						try {
							i.call();

							// call the add-goal trigger with 50% chance
							// and put a random value [0, 100] into the literal
							if (Math.random() < 0.5)
								i.trigger(CTrigger.from(

										// trigger-type
										ITrigger.EType.ADDGOAL,

										// goal-literal with value
										CLiteral.from("special-goal", CRawTerm.from(100 * Math.random()))));
						} catch (final Exception l_exception) {
							l_exception.printStackTrace();
						}
					}));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
