package de.tudresden.agentontology.ontologyapplication;

import jade.content.*;

public class CreateAccount implements AgentAction {
	/**
		 * 
		 */
	private static final long serialVersionUID = 2127648356418349027L;
// ------------------------------------------------

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}