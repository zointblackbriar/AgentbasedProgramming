const ClassContract = artifacts.require("ClassContract");


module.exports = function(_deployer) {
  // Use deployer to state migration tasks.
  _deployer.deploy(ClassContract); 
};
