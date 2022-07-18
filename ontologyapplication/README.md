### Multi-agent Simulation with Smart Contracts (Smart Agent - SA)

This project is going to be related to multi-agent simulation with regards to smart contract programming. 
Event if the multi-agent system has been constructed on theory base, we are going to implement a simulation environment
that will register the track of multi-agent application into the blockchain network.


### Maven-specific Information 

In order to compile the codebase: 

```bash 
mvn clean compile
```

In order to install libraries into the local repository. (Without testing) 

```bash
mvn clean install -DskipTests=true
```


### Bank Ontology Application

License belongs to Montreal University

[Source of the Repository](https://www.iro.umontreal.ca/~vaucher/Agents/Jade/Ontologies.htm)


### Run the application

Running application is easy with the following command. You need to put it into Program Arguments. 
Moreover, you need to select main class as **jade.boot** and Include System Libraries when searching the main class 

```bash
-gui jade.Boot TestAgent:de.tudresden.agentontology.simpleagent.SimpleAgent
```

or templated way is the following way: 

```bash
-gui jade.Boot [agent_nickname1:java_package1.agent_class1 agent_nickname2:java_package2.agent_class2 …]
```


### Information regarding smart contracts 

You can compile a particular smart with the following command: 

```bash
solc --abi --bin --optimize Greeter.sol -o build --overwrite
```

### Information with regards to generate Java mapping file from a smart contract through Solc compile

```bash
web3j generate solidity --abiFile smartcontracts/build/Greeter.abi --binFile smartcontracts/build/Greeter.bin --package de.tudresden.agentontology.greeterapp --outputDir src/main/java/
```
### Information with regards to generate Java wrapper class with a smart contract through Truffle Suite

```bash
web3j generate truffle --truffle-json smartcontracts/build/contracts/ClassContract.json --outputDir src/main/java/ --package de.tudresden.agentontology.classcontract
```

###Generate Source Code from Solidity to Java with Maven Plugin
You can use the following command in the terminal window.

```bash
mvn web3j:generate-sources
```
 

###Some errors

If you have an error like "Exceeds the gas limit in the transaction". You need to equalize both ganache-cli and java application gas limits
Default java application gas limit: 

```bash
public static final BigInteger GAS_LIMIT = BigInteger.valueOf(9_000_000);
public static final BigInteger GAS_PRICE = BigInteger.valueOf(4_100_000_000L);
```

So you can start ganache-cli with the following command: 

```bash
ganache-cli -l 9000000
```

Mnemonic can be used for generating deterministic private keys. There is no way to get all private keys with a script 
and Ganache-Cli or GUI changes private key at each opening from scratch. Use the following code snippet

```bash
ganache-cli -l 9000000 -m "world reopen cute forward vintage okay drink margin piano buffalo autumn awful"
```

### LightJason Applications

Run the agent application (Correct way) 

```bash 
mvn package -DskipTests=true
```

And then

```bash 
java -jar target/myagentapp-1.0-SNAPSHOT.jar agent_trigger.asl 500 1000
```
Run with Maven command

```bash 
mvn clean compile exec:java -Dexec.mainClass="de.tudresden.agentontology.lightjasonintegration.AgentApp" -Dexec.args="agentasl/agent_trigger.asl 500 1000"
```