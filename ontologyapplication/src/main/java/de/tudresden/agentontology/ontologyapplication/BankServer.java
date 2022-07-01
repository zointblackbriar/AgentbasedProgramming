package de.tudresden.agentontology.ontologyapplication;


import jade.core.*;
import jade.core.behaviours.*;
import jade.domain.*;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.*;
import jade.content.*;
import jade.content.lang.*;
import jade.content.lang.sl.*;
import jade.content.onto.*;
import jade.content.onto.basic.*;
import jade.util.leap.*;



public class BankServer extends Agent implements BankVocabulary {
// -------------------------------------------------------------------

   /**
	 * 
	 */
	private static final long serialVersionUID = 6292514514311707016L;
private Map accounts = new HashMap();
   private Map operations = new HashMap();
   private int idCnt = 0;
   private Codec codec = new SLCodec();
   private Ontology ontology = BankOntology.getInstance();

   protected void setup() {
// ------------------------

      // Register language and ontology
      getContentManager().registerLanguage(codec);
      getContentManager().registerOntology(ontology);

      // Set this agent main behaviour
      SequentialBehaviour sb = new SequentialBehaviour();
      sb.addSubBehaviour(new RegisterInDF(this));
      sb.addSubBehaviour(new ReceiveMessages(this));
      addBehaviour(sb);
   }

   class RegisterInDF extends OneShotBehaviour {
/**
	 * 
	 */
	private static final long serialVersionUID = -493776462668620754L;

	// ---------------------------------------------  Register in the DF for the client agent
//                                                be able to retrieve its AID
      RegisterInDF(Agent a) {
         super(a);
      }

      public void action() {

         ServiceDescription sd = new ServiceDescription();
         sd.setType(SERVER_AGENT);
         sd.setName(getName());
         sd.setOwnership("Prof6802");
         DFAgentDescription dfd = new DFAgentDescription();
         dfd.setName(getAID());
         dfd.addServices(sd);
         try {
            DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0 ) {
               DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            System.out.println(getLocalName() + " is ready.");
         }
         catch (Exception ex) {
            System.out.println("Failed registering with DF! Shutting down...");
            ex.printStackTrace();
            doDelete();
         }
      }
   }

   class ReceiveMessages extends CyclicBehaviour {
// -----------------------------------------------  Receive requests and queries from client
//                                                  agent and launch appropriate handlers

      /**
	 * 
	 */
	private static final long serialVersionUID = -9118880816597644329L;

	public ReceiveMessages(Agent a) {

         super(a);
      }

      public void action() {

         ACLMessage msg = receive();
         if (msg == null) { block(); return; }
         try {
            ContentElement content = getContentManager().extractContent(msg);
            Concept action = ((Action)content).getAction();

            switch (msg.getPerformative()) {

               case (ACLMessage.REQUEST):

                  System.out.println("Request from " + msg.getSender().getLocalName());

                  if (action instanceof CreateAccount)
                     addBehaviour(new HandleCreateAccount(myAgent, msg));
                  else if (action instanceof MakeOperation)
                     addBehaviour(new HandleOperation(myAgent, msg));
                  else replyNotUnderstood(msg);
                  break;

               case (ACLMessage.QUERY_REF):

                  System.out.println("Query from " + msg.getSender().getLocalName());

                  if (action instanceof Information)
                     addBehaviour(new HandleInformation(myAgent, msg));
                  else replyNotUnderstood(msg);
                  break;

               default: replyNotUnderstood(msg);
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleCreateAccount extends OneShotBehaviour {
// ----------------------------------------------------  Handler for a CreateAccount request

      /**
	 * 
	 */
	private static final long serialVersionUID = -8622646879906293608L;
	private ACLMessage request;

      HandleCreateAccount(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            CreateAccount ca = (CreateAccount)((Action)content).getAction();
            Account acc = new Account();
            String id = generateId();
            acc.setId(id);
            acc.setName(ca.getName());
            Result result = new Result((Action)content, acc);
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            getContentManager().fillContent(reply, result);
            send(reply);
            accounts.put(id, acc);
            operations.put(id, new ArrayList());
            System.out.println("Account [" + acc.getName() + " # " +
                               acc.getId() + "] created!");
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleOperation extends OneShotBehaviour {
// ------------------------------------------------  Handler for an Operation request

      /**
	 * 
	 */
	private static final long serialVersionUID = -6479773547514994207L;
	private ACLMessage request;

      HandleOperation(Agent a, ACLMessage request) {

         super(a);
         this.request = request;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(request);
            MakeOperation mo = (MakeOperation)((Action)content).getAction();
            ACLMessage reply = request.createReply();
            Object obj = processOperation(mo);
            if (obj == null) replyNotUnderstood(request);
            else {
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, obj);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("Operation processed.");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   class HandleInformation extends OneShotBehaviour {
// --------------------------------------------------  Handler for an Information query

      /**
	 * 
	 */
	private static final long serialVersionUID = 5348861319680169134L;
	private ACLMessage query;

      HandleInformation(Agent a, ACLMessage query) {

         super(a);
         this.query = query;
      }

      public void action() {

         try {
            ContentElement content = getContentManager().extractContent(query);
            Information info = (Information)((Action)content).getAction();
            Object obj = processInformation(info);
            if (obj == null) replyNotUnderstood(query);
            else {
               ACLMessage reply = query.createReply();
               reply.setPerformative(ACLMessage.INFORM);
               Result result = new Result((Action)content, obj);
               getContentManager().fillContent(reply, result);
               send(reply);
               System.out.println("Information processed.");
            }
         }
         catch(Exception ex) { ex.printStackTrace(); }
      }
   }

   void replyNotUnderstood(ACLMessage msg) {
// -----------------------------------------

      try {
         ContentElement content = getContentManager().extractContent(msg);
         ACLMessage reply = msg.createReply();
         reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
         getContentManager().fillContent(reply, content);
         send(reply);
         System.out.println("Not understood!");
      }
      catch(Exception ex) { ex.printStackTrace(); }
   }

   Object processOperation(MakeOperation mo) {
// -------------------------------------------

      Account acc = (Account)accounts.get(mo.getAccountId());
      if (acc == null) return newProblem(ACCOUNT_NOT_FOUND);
      if (mo.getAmount() <= 0) return newProblem(ILLEGAL_OPERATION);

      if (mo.getType() != DEPOSIT && mo.getType() != WITHDRAWAL)
         return null;
      if (mo.getType() == DEPOSIT)
         acc.setBalance(acc.getBalance() + mo.getAmount());
      else if (mo.getType() == WITHDRAWAL) {
         if (mo.getAmount() > acc.getBalance())
            return newProblem(NOT_ENOUGH_MONEY);
         acc.setBalance(acc.getBalance() - mo.getAmount());
      }
      Operation op = new Operation();
      op.setType(mo.getType());
      op.setAmount(mo.getAmount());
      op.setAccountId(acc.getId());
      op.setDate(new java.util.Date());
      List l = (List)operations.get(acc.getId());
      l.add(op);
      operations.put(acc.getId(), l);
      return acc;
   }

   Object processInformation(Information info) {
// -------------------------------------------

      Account acc = (Account)accounts.get(info.getAccountId());
      if (acc == null) return newProblem(ACCOUNT_NOT_FOUND);

      java.util.Date date = new java.util.Date();
      Operation op = new Operation();              // <-- Apply admin charge
      op.setType(ADMIN);
      op.setAmount(info.getType()==BALANCE ? BAL_CHARGE : OPER_CHARGE);
      acc.setBalance(acc.getBalance() - op.getAmount());
      op.setBalance(acc.getBalance());
      op.setAccountId(acc.getId());
      op.setDate(date);
      List l = (List)operations.get(acc.getId());
      l.add(op);
      operations.put(acc.getId(), l);

      if (info.getType() == BALANCE) return acc;
      if (info.getType() == OPERATIONS) return l;
      return null;
   }

//--------------------------- Utility methods ----------------------------//

   Problem newProblem(int num) {
// -----------------------------

      String msg = "";

      if (num == ACCOUNT_NOT_FOUND)
         msg = PB_ACCOUNT_NOT_FOUND;

      else if (num == NOT_ENOUGH_MONEY)
         msg = PB_NOT_ENOUGH_MONEY;

      else if (num == ILLEGAL_OPERATION)
         msg = PB_ILLEGAL_OPERATION;

      Problem prob = new Problem();
      prob.setNum(num);
      prob.setMsg(msg);
      return prob;
   }

   String generateId() {
// ----------------------------

      return hashCode() + "" + (idCnt++);
   }

}
