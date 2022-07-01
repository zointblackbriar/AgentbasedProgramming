package de.tudresden.agentontology.ontologyapplication;

import jade.content.Concept;

public class Problem implements Concept {
// -------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1778390375166225358L;
	private int num;
	private String msg;

	public int getNum() {
		return this.num;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}