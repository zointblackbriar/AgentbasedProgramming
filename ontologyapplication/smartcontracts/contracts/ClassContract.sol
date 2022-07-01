pragma solidity ^0.5.2;

import "openzeppelin-solidity/contracts/access/Roles.sol";
import "openzeppelin-solidity/contracts/ownership/Ownable.sol";


contract ClassContract is Ownable {
    using Roles for Roles.Role;

    mapping(address => bool) transactionFees;
    mapping(address=>bool) transactionCheck;

    Roles.Role private suppliers;
    Roles.Role private retailers;

    function addSupplierRoles(address[] memory _suppliers) public onlyOwner {
        for(uint i=0; i < _suppliers.length; i++) {
            suppliers.add(_suppliers[i]);
        }
    }

    function addRetailerRoles(address[] memory _retailers) public onlyOwner {
        for(uint i = 0; i < _retailers.length; i++) {
            retailers.add(_retailers[i]);
        }
    }

    function feeCheck(uint amount) external pure returns(bool) {
        return amount >=4 ether;
    }

    function payFees() public payable {
        require(retailers.has(msg.sender), "DOES NOT HAVE RETAILER ROLE");
        if(this.feeCheck(msg.value))
        {
            address(uint160(owner())).transfer(msg.value); // Cast to address(uint160) to make address payable
            transactionFees[msg.sender] = true;
        }
    }

    function transactionGivingCheck(address retailer, bool transactionCompleted) public
    {
        require(suppliers.has(msg.sender), "We do not have a supplier role");
        require(retailers.has(msg.sender), "We do not have a retailer role");
        transactionCheck[retailer] = transactionCompleted;
    }
}
