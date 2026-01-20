angular.module("helloModule").controller("HelloController", HelloController);

function HelloController($scope, HelloFactory) {
	
	$scope.name="Nilesh Chavan"
	$scope.fullName="";
    $scope.message = "Loading...";
	$scope.age="";
	$scope.status = "ACTIVE";

	
	$scope.employees=[
		
			{
				id:1,
				name:'Nilesh Chavan',
				address:'Karad',
				email:'nilesh@gmail.com',
				phoneNo:'7856345678',
				gender:'Male',
				dept:'IT',
				role:'Junoir Full Stack Developer',
				salary:240000
				
			},
			{
				id:2,
				name:'Suresh Pawar',
				address:'Shenoli',
				email:'suresh@gmail.com',
				phoneNo:'9856345678',
				gender:'Male',
				dept:'NON-IT',
				role:'clark',
				salary:290000
			},
			{
						id:3,
						name:'Pawan Chavan',
						address:'Satara',
						email:'pawan@gmail.com',
						phoneNo:'7756345678',
						gender:'Male',
						dept:'IT',
						role:'Full Stack Developer',
						salary:350000
			},
			{
						id:4,
						name:'Shripad Raccah',
						address:'Pune',
						email:'shri@gmail.com',
						phoneNo:'8856345678',
						gender:'Male',
						dept:'IT',
						role:'Junoir Full Stack Developer',
						salary:240000
			},
			{
						id:5,
						name:'Pratik Patil',
						address:'Karad',
						email:'pratik@gmail.com',
						phoneNo:'9956345678',
						gender:'Male',
						dept:'IT',
						role:'Java Developer',
						salary:320000
			}
		]
	
	$scope.employee = {
	    id: "",
	    name: "",
	    address: "",
	    email: "",
	    phoneNo: "",
	    gender: "",
	    dept: "",
	    role: "",
	    salary: ""
	};

	$scope.addEmployee = function () {
	       $scope.employees.push(angular.copy($scope.employee));

	       // Clear form
	       $scope.employee = {};
	   };
	
	
	
	
	$scope.methodrun=function(){
		alert("Event binding Worked...!");
	}
	HelloFactory.getHello()
	       .then(function(response) {
	          $scope.message = response.data;
	       })
	       .catch(function() {
	           console.log("Error loading employees");
	       });
};
