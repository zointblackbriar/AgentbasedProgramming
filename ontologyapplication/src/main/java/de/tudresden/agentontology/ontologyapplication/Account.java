package de.tudresden.agentontology.ontologyapplication;

import jade.content.*;

public class Account implements Concept {
// --------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = -3647315390635313594L;
	private String id;
	private String name;
	private float balance = 0;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public float getBalance() {
		return balance;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String toString() {
		return name + "  # " + id + "  --> balance = " + balance;
	}
}