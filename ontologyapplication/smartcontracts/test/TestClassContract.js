const ClassContract = artifacts.require("ClassContract"); 

contract("ClassContract", (accounts) =>{
    let classContract;
    let students;
    let teachers;

    beforeEach("Set up contract for each test", async () =>{

        students = [ accounts[1],
                    accounts[2],
                    accounts[3],
                    accounts[4]
        ];

        teachers = [ accounts[5],
                    accounts[6] ];
        classContract = await ClassContract.new();
        try {
            await classContract.addStudentRoles(students,{from:accounts[0]});
            await classContract.addTeacherRoles(teachers, {from:accounts[0]});
        } catch (e) {
            console.log("Error " + e)
        }

    });

    it('ensure that owner is the first address', async  () => {
        const owner = await classContract.owner();
        assert.equal(owner, accounts[0]);

    });

});
