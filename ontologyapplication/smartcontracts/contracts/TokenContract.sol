pragma solidity ^0.5.2; 

contract TokenContract {
    mapping (address => uint) balanceOfTheContract;

    event Transfer(address indexed fromAddress, address indexed toAddress, uint256 value);

    constructor() public {
        balanceOfTheContract[msg.sender] = 100000;
    }

    function sendToken(address receiver, uint amount) public returns(bool){
        require(balanceOfTheContract[msg.sender] < amount);
        balanceOfTheContract[msg.sender] -= amount;
        balanceOfTheContract[receiver] += amount;
        emit Transfer(msg.sender, receiver, amount);
        return true;
    }

    function getBalance(address addressparam) public view returns(uint) {
        return balanceOfTheContract[addressparam];
    }
}
