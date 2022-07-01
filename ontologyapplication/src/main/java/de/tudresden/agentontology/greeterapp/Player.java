package de.tudresden.agentontology.greeterapp;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.8.7.
 */
@SuppressWarnings("rawtypes")
public class Player extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610212806100206000396000f3fe60806040526004361061003f5760003560e01c80633fa4f245146100445780634e70b1dc1461006b57806358b05a401461008057806367e404ce146100ae575b600080fd5b34801561005057600080fd5b506100596100df565b60408051918252519081900360200190f35b34801561007757600080fd5b506100596100e5565b6100ac6004803603604081101561009657600080fd5b506001600160a01b0381351690602001356100eb565b005b3480156100ba57600080fd5b506100c36101cd565b604080516001600160a01b039092168252519081900360200190f35b60025481565b60005481565b60408051602480820184905282518083039091018152604490910182526020810180516001600160e01b0316635a444a0d60e11b1781529151815160009384936001600160a01b038816939092909182918083835b6020831061015f5780518252601f199092019160209182019101610140565b6001836020036101000a038019825116818451168082178552505050505050905001915050600060405180830381855af49150503d80600081146101bf576040519150601f19603f3d011682016040523d82523d6000602084013e6101c4565b606091505b50505050505050565b6001546001600160a01b03168156fea2646970667358221220072d072890a42f3bf82d875ea436d2ba0c8b06cbf15cf84d508f92f1449bee4364736f6c63430007060033";

    public static final String FUNC_NUM = "num";

    public static final String FUNC_SENDER = "sender";

    public static final String FUNC_SETVARIABLES = "setVariables";

    public static final String FUNC_VALUE = "value";

    @Deprecated
    protected Player(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Player(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Player(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Player(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> num() {
        final Function function = new Function(FUNC_NUM, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> sender() {
        final Function function = new Function(FUNC_SENDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setVariables(String _contract, BigInteger _num) {
        final Function function = new Function(
                FUNC_SETVARIABLES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _contract), 
                new org.web3j.abi.datatypes.generated.Uint256(_num)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> value() {
        final Function function = new Function(FUNC_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static Player load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Player(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Player load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Player(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Player load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Player(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Player load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Player(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Player> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Player.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Player> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Player.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Player> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Player.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Player> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Player.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
