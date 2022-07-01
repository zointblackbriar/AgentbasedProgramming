package de.tudresden.agentontology.jadexintegration;

import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.Publish;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.impl.PlanFailureException;



@Agent(type=BDIAgentFactory.TYPE)
public class PainterAgent {
	
	@Agent
	protected IInternalAccess agent; 
	
	@Belief
	protected Object painter; 
	
	@Goal(publish=@Publish(type=IPaintMoneyService.class))
	public class GetYourEuro 
	{
		public GetYourEuro(String from) {
			
		}
	}
	
	@Plan(trigger=@Trigger(goals=GetYourEuro.class))
	public class PaintPlan 
	{
		@PlanBody
		public String paint(IPlan plan, GetYourEuro goal) {
			if(painter != null) {
				throw new PlanFailureException();
			}
			
			painter = this; 
			
			System.out.println("painting start: " + agent.getId() + " " + goal);
			
			plan.waitFor(2000).get();
			
			System.out.println("painting end: " + agent.getId());
			painter = null;
			
			return agent.getId().getName();
			
		}
		
		@PlanAborted
		@PlanFailed
		public void fail(Exception ex)
		{
			System.out.println("failed" + ex);
		}
		
		@PlanPrecondition
		public boolean checkSecond() 
		{
			return painter == null; 
		}
	}
}
