package de.tudresden.agentontology.ontologyapplication;

import jade.content.*;

public class Information implements AgentAction {
// ----------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -6077413476929206563L;
	private int type;
	private String accountId;

	public int getType() {
		return type;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}