package de.tudresden.agentontology.jadexintegration;

import jadex.micro.annotation.Agent;
import jadex.bdiv3.runtime.BDIAgent;
import jadex.bdiv3.runtime.BDIGoal;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.bridge.service.annotation.OnStart;
import jadex.commons.future.IFuture;

import java.util.logging.Logger;

import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;



@Agent(type=BDIAgentFactory.TYPE)
public class HelloPureGoalAgent extends BDIAgent{
	
	private static final Logger logger = Logger.getLogger(HelloPureGoalAgent.class.getName());
	
	@Belief	
	private String sayhello; 
	
	
	@Goal 
	public class HelloGoal extends BDIGoal {
		@GoalParameter
		protected String text; 
		
		@GoalCreationCondition(beliefs="sayhello")
		public HelloGoal(String text)  {
			setText(text);
		}
		
		@GoalTargetCondition(parameters="text")
		public boolean checkTarget() {
			return "finished".equals(text);
		}
		
		public void setText(String value) {
			setParameterValue("text", value);	
		}
		
		public String getText() {
			return text;
		}
	}
	
	@OnStart
	public void body() {
		setBeliefValue("sayhello", "Hello BDI pure agent V3 Hello Pure Goal"); 
		logger.info("body end: " + getClass().getName());
	}
	
	@Plan(trigger=@Trigger(goals=HelloGoal.class))
	protected IFuture<Void> printHelloGoal(HelloGoal goal) {
		
		logger.info("get text: " + goal.getText());
		throw new PlanFailureException();
		
	}
	
	@Plan(trigger=@Trigger(goals=HelloGoal.class))
	protected void printHelloGoal2(HelloGoal goal) {
		goal.setText("finished");
		logger.info("hello goal 2" + goal.getText());
	}
	
	@Plan(trigger=@Trigger(goals=HelloGoal.class))
	protected IFuture<Void> printHelloGoal3(HelloGoal goal) {
		logger.info("next goal: " + goal.getText());
		return IFuture.DONE;
		
	}
}
