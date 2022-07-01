package de.tudresden.agent.finitistatemachine;

import jade.core.behaviours.OneShotBehaviour;

public class StateBehavior extends OneShotBehaviour{
	
	
	private int exitValue; 
	private int upperBoundValue; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6159768973565651610L;

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}
	
	public StateBehavior(int maxValue) {
		super(); 
		this.upperBoundValue = maxValue; 
		this.exitValue = maxValue;
	}
	
}
