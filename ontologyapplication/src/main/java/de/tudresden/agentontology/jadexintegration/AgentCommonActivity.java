package de.tudresden.agentontology.jadexintegration;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.runtime.BDIAgent;
import jadex.bridge.IInternalAccess;
import jadex.commons.Boolean3;
import jadex.micro.annotation.Configuration;
import jadex.micro.annotation.Configurations;
import jadex.micro.annotation.Agent;

import java.util.logging.Logger;

import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanPrecondition;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.bridge.service.annotation.OnStart;

@Configurations({ @Configuration(name = "sunny"), @Configuration(name = "rainy") })
@Agent(type = BDIAgentFactory.TYPE, keepalive = Boolean3.FALSE)
public class AgentCommonActivity {

	private static final Logger logger = Logger.getLogger(AgentCommonActivity.class.getName());
	// BDI Agent is here
	@Agent
	protected IInternalAccess agent;

	@Belief
	protected boolean raining = agent.getConfiguration().equals("rainy");

	// The top-level goal of the Uni Plan Library
	@Goal
	protected class GotoSwimming {

	}

//	@Goal
	protected static class GoalHandOver {
		public enum Type {
			TRAIN, BUS
		};

		protected Type type;

		public GoalHandOver(Type type) {
			this.type = type;
		}

		public Type getType() {
			return type;
		}
	}

	/**
	 * The agent body is executed on startup. It creates and dispatches a come to
	 * university goal.
	 */

	@OnStart
	public void body() {
		logger.info("rainy:" + raining);

		try {
			agent.getFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(new GotoSwimming()).get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * The walk plan for the come to university goal. Walk only if its not raining
	 * and not as first choice
	 */
	@Plan(trigger = @Trigger(goals = GotoSwimming.class), priority = -1)
	protected class WalkPlan {
		@PlanPrecondition
		protected boolean checkWeather() {
			return !raining;

		}

		@PlanBody
		protected void walk() {
			System.out.println("Walked for Swimming");
		}
	}

	// Take the train if it is raining
	@Plan(trigger = @Trigger(goals = GotoSwimming.class))
	protected class GoByTrain 
	{
		@PlanPrecondition
		protected boolean checkWeather() {
			return raining;
		}

		@PlanBody
		protected void takeTrain(IPlan plan) {
			System.out.println("Trying to take train to Swimming");
			plan.dispatchSubgoal(new GoalHandOver(GoalHandOver.Type.TRAIN)).get();
			System.out.println("Took train to Uni");
		}
	}
	
	//Bus plan
	@Plan(trigger=@Trigger(goals = GotoSwimming.class))
	protected void GoByBus(IPlan plan) 
	{
		System.out.println("Trying to take a bus");
		plan.dispatchSubgoal(new GoalHandOver(GoalHandOver.Type.BUS)).get(); 
		System.out.println("We took the Bus");
	}
}
