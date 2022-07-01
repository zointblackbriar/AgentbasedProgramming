package de.tudresden.agentontology.jadexintegration;

import jadex.commons.future.IFuture;

public interface IPaintMoneyService {
	
	public IFuture<String> paintEuro(String name); 
	
	
}
