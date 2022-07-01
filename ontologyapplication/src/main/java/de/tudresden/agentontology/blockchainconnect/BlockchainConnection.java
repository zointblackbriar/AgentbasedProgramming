package de.tudresden.agentontology.blockchainconnect;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import de.tudresden.agent.annotations.RuntimeSampleAnn;
import de.tudresden.agentontology.classcontract.ClassContract;
import de.tudresden.agentontology.greeterapp.Greeter;
import de.tudresden.agentontology.util.PropertyUtil;


public class BlockchainConnection {
	
	private static final Logger LOGGER = Logger.getLogger(BlockchainConnection.class.getName());
	private static String AVAILABLE_ACCOUNT = PropertyUtil.getInstance().getProperty("AVAILABLE_ACCOUNT");
	private static String PRIVATE_KEY = PropertyUtil.getInstance().getProperty("PRIVATEKEY");
	
	public boolean runBlockchainConnector() throws IOException {
		try {
			Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545")); 
	        String account = web3j.ethAccounts().send().getAccounts().get(0);
			LOGGER.info("Connected to the Ethereum client: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());
			LOGGER.info("connected account: " + account);
			Credentials credentials = Credentials.create(PRIVATE_KEY);
			//If you have a wallet file, please install with the following command. 
//			Credentials credentials = WalletUtils.loadCredentials("[password]", "[path of the wallet]"); 
			LOGGER.info("Credentials loaded");
			
			//Testing for sending WEI
			LOGGER.info("Sending 1 Wei" + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString()); 
//			TransactionReceipt transferReceipt = Transfer.sendFunds(web3j, credentials, AVAILABLE_ACCOUNT, BigDecimal.ONE, Convert.Unit.WEI).send(); // 1 wei = 10^-18 Ether
			TransactionReceipt transferReceipt = Transfer.sendFunds(web3j, credentials, account, BigDecimal.ONE, Convert.Unit.WEI).send(); // 1 wei = 10^-18 Ether
			LOGGER.info("Transaction complete and get me the hash value of the transaction: " + transferReceipt.getTransactionHash()); 
			
			//Now lets deploy a smart contract 
			LOGGER.info("Deploying smart contracts"); 
		    ContractGasProvider gasProvider = new DefaultGasProvider();
		    
		    if(runGreeterContract(web3j, credentials, gasProvider) == false) {
		    	return false;
		    }
		    if(runClassContract(web3j, credentials, gasProvider) == false) {
		    	return false;
		    }
		} catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String testAvailableAccount(String methodName) throws NoSuchMethodException, SecurityException {
		Class<? extends BlockchainConnection> cls = this.getClass(); 
		Method mtd = cls.getMethod("runGreeterContract");
		if(mtd.isAnnotationPresent(RuntimeSampleAnn.class)) {
			System.out.println("The annotation is present");
		} else {
			System.out.println("The annotation is not present");
		}
		RuntimeSampleAnn newSampleAnnotation = mtd.getAnnotation(RuntimeSampleAnn.class);
		System.out.println("name is: " + newSampleAnnotation.name());
		System.out.println("desc is: " + newSampleAnnotation.desc());
		return newSampleAnnotation.name();
	}
	
//	@RuntimeSampleAnn(name="SampleAccount", desc = "isAvailableAccountMatched()")
	public static boolean runGreeterContract(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) throws Exception {
		try {
//			BlockchainConnection obj = new BlockchainConnection();
//			if(obj.testAvailableAccount("runGreeterContract").equalsIgnoreCase(AVAILABLE_ACCOUNT)) {
//				System.out.println("AVAILABLE_ACCOUNT is valid");
//			}else
//			{
//				System.out.println("AVAILABLE_ACCOUNT is not valid");
//			}
			Greeter contractTest = Greeter.deploy(web3j, credentials, gasProvider, AVAILABLE_ACCOUNT).send();
			String contractAddress = contractTest.getContractAddress();
			LOGGER.info("Smart contract deployed to address: " + contractAddress);
			LOGGER.info("Value stored in remote smart contract: " + contractTest.greet().send()); 
			
			//Lets modify the value in our smart contracts
			TransactionReceipt transactionReceipt1 = contractTest.newGreeting("Hello agent application").send(); 
			TransactionReceipt transactionReceipt2 = contractTest.newGreeting("Agent 1 sends an INFORM message from FIPAACL").send(); 

			LOGGER.info("new value stored in remote smart contracts: " + contractTest.greet().send());
			
	        // Events enable us to log specific events happening during the execution of our smart
	        // contract to the blockchain. Index events cannot be logged in their entirety.
	        // For Strings and arrays, the hash of values is provided, not the original value.
	        // For further information, refer to https://docs.web3j.io/filters.html#filters-and-events

			for(Greeter.ModifiedEventResponse event : contractTest.getModifiedEvents(transactionReceipt1)) {
				LOGGER.info("Modify event fired, previous value: " + event.oldGreeting + " new value: " + event.newGreeting); //Take real events from the blockchain
				LOGGER.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx) + "new Value: " + Numeric.toHexString(event.newGreetingIdx)); //Take IDs
				LOGGER.info("Get logs: " + event.log);
			}	
			for(Greeter.ModifiedEventResponse event : contractTest.getModifiedEvents(transactionReceipt2)) {
				LOGGER.info("Modify event fired, previous value: " + event.oldGreeting + " new value: " + event.newGreeting); //Take real events from the blockchain
				LOGGER.info("Indexed event previous value: " + Numeric.toHexString(event.oldGreetingIdx) + "new Value: " + Numeric.toHexString(event.newGreetingIdx)); //Take IDs
				LOGGER.info("Get logs: " + event.log);
			}	

		} catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;

	}
	
	public static boolean runClassContract(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) throws Exception {
		try {
			ClassContract contractTest = ClassContract.deploy(web3j, credentials, gasProvider).send();
			String contractAddress = contractTest.getContractAddress();
			BigInteger testValue = new BigInteger("8");
//			BigInteger weiTestValue = new BigInteger("1000000000000");
			List<String> newRoles = new ArrayList<String>();
			newRoles.add("1");
			newRoles.add("2");
			newRoles.add("3");
			newRoles.add("4");
			newRoles.add("5");
//			newRoles.add("5");
			LOGGER.info("Smart contract deployed to address: " + contractAddress);
			LOGGER.info("feeCheck function has been called: " + contractTest.addRetailerRoles(newRoles).send()); 
			LOGGER.info("feeCheck function has been called: " + contractTest.feeCheck(testValue).send()); 
//			LOGGER.info("feeCheck function has been called: " + contractTest.payFees(weiTestValue).send()); 

		} catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		return true;

	}
	
}
