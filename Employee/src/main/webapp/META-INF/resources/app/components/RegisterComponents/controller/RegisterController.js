angular.module("registerModule")
	.controller("RegisterController", function($scope, $state, EmployeeFactory) {

		$scope.registerData = {
			firstName: "",
			lastName: "",
			address: "",
			email: "",
			username: "",
			phoneNo: "",
			password: ""
		};

		$scope.register = function () {
		    console.log("Register data:", $scope.registerData);

		    EmployeeFactory.register($scope.registerData)
		        .then(function(response) {
		            console.log("Response from backend:", response);

		    
		            if (response.data && response.data.id) {
		                $scope.message = "Registration Successful!";
		               
		             
		                setTimeout(function() {
		                    $state.go("login");
		                    $scope.$apply(); 
		                }, 500);
		            } else {
		                $scope.message = response.data.message;
		            }
		        })
		        .catch(function(error){
		            console.error("Registration error:", error);
		            $scope.message = "Registration Failed!";
		        });
		};

	});