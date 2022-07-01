//SPDX-Licence-Modifier: MIT
pragma solidity >=0.7.0 <0.8.0;

contract Callee {
    uint public num; 
    address public sender; 
    uint public value; 

    function setVariables(uint _num) public payable {
        num = _num; 
        sender = msg.sender; 
        value = msg.value;
    }

}

contract Player {
    uint public num; 
    address public sender; 
    uint public value; 

    function setVariables(address _contract, uint _num) public payable {
        //Callee's storage is not modified, Player's storage is set
        (bool success, bytes memory data) = _contract.delegatecall(abi.encodeWithSignature("setVariables(uint256)", _num));
    }
}