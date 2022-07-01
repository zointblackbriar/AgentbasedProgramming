package de.tudresden.agentontology.jadexintegration;

import jadex.bridge.IInternalAccess;
import jadex.bridge.service.ServiceScope;
import jadex.bridge.service.annotation.OnStart;
import jadex.commons.future.Future;
import jadex.commons.future.IResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

import java.util.logging.Logger;

import jadex.bdiv3.BDIAgentFactory;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalTargetCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.ServicePlan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.IPlan;

@Agent(type=BDIAgentFactory.TYPE)
@RequiredServices(@RequiredService(name="getser", type=IPaintMoneyService.class, scope=ServiceScope.PLATFORM))
@Plans(@Plan(trigger=@Trigger(goals=PaymentAgent.GetYourEuro.class), body=@Body(service=@ServicePlan(name="getser"))))
public class PaymentAgent {
	
	private static final Logger logger = Logger.getLogger(PaymentAgent.class.getName());
	
	@Agent 
	protected IInternalAccess agent; 
	
	@Belief
	protected int money; 
	
	@Belief
	protected int target = 3; 
	
	
	@Goal
	public class GetYourEuro {
		@GoalParameter
		protected String agent; 
		
		public GetYourEuro(String agent) {
			this.agent = agent;
		}
	}
	
	//AgentBody
	@OnStart
	public void body() {
		agent.getFeature(IBDIAgentFeature.class).dispatchTopLevelGoal(new EarnMoney()).get(); 
		if(money==target) {
			logger.info("Now I am a rich agent and I got money: " + money );
		} else {
			logger.info("I am a poor agent");
		}
	}
	
	@Goal
	public class EarnMoney
	{
		@GoalTargetCondition
		public boolean checkTarget() {
			return money == target; 
		}
	}
	
	@Plan(trigger=@Trigger(goals=EarnMoney.class))
	public void distributeWork(IPlan plan) {
		final Future<Void> futureSample = new Future<Void>();
		final int maximumAmountMoney = target-money; //deficit money to reach your target
		//Callback interface for methods that should operate decoupled from caller thread
		IResultListener<Object> lisResultListener = new IResultListener<Object>() {
			
			int sampleCount = 0; 
			@Override
			public void resultAvailable(Object result) {
				// TODO Auto-generated method stub
				System.out.println("Goal success money making: " + result);
				money++; 
				proceed();
			}

			@Override
			public void exceptionOccurred(Exception exception) {
				// TODO Auto-generated method stub
				
			}
			
			protected void proceed() {
				System.out.println("cnt, max: " + sampleCount + " " + maximumAmountMoney);
				if(++sampleCount ==  maximumAmountMoney)
				{
					futureSample.setResult(null);
				}
			}
		};
		
		for(int i = 0;  i < maximumAmountMoney; i++) {
			GetYourEuro goal = new GetYourEuro(agent.getId().getLocalName()); 
			plan.dispatchSubgoal(goal).addResultListener(lisResultListener);
		}
		futureSample.get();
	}
	
	protected void incrementMoney() {
		money++;
	}
}
