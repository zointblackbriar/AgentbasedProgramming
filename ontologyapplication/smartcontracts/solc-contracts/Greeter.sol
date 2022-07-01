// SPDX-License-Identifier: MIT
 pragma solidity >=0.7.0 <0.8.0;
 
// Modified Greeter contract. Based on example at https://www.ethereum.org/greeter.

contract Mortal {
    /* Define variable owner of the type address*/
    address owner;

    /* this function is executed at initialization and sets the owner of the contract */
    constructor () { owner = msg.sender; }

    /* Function to recover the funds on the contract */
    //function kill() public payable { if (msg.sender == owner) selfdestruct(owner); }
}

contract Greeter is Mortal {
    /* define variable greeting of the type string */
    string greeting;

    /* this runs when the contract is executed */
    constructor (string memory _greeting) {
        greeting = _greeting;
    }

    function newGreeting(string memory _greeting) public {
        emit Modified(greeting, _greeting, greeting, _greeting);
        greeting = _greeting;
    }

    /* main function */
    function greet() public view returns (string memory)  {
        return greeting;
    }

    /* we include indexed events to demonstrate the difference that can be
    captured versus non-indexed */
    event Modified(
            string indexed oldGreetingIdx, string indexed newGreetingIdx,
            string oldGreeting, string newGreeting);
}