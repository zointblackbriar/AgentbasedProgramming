package de.tudresden.agentontology.ontologyapplication;

import jade.content.*;

public class MakeOperation implements AgentAction {
// ------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454654730066110233L;
	private int type;
	private float amount;
	private String accountId;

	public int getType() {
		return type;
	}

	public float getAmount() {
		return amount;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}