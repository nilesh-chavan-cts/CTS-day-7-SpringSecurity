
angular.module("loginModule")
.controller("LoginController", function($scope, EmployeeFactory, $state) {
	console.log("LoginController test");
	$scope.loginData = {
		username:'',
		password:''
	};

	    $scope.login = function() {

	        EmployeeFactory.login($scope.loginData)
	            .then(function(response) {
					console.log(response.data);
					localStorage.setItem("email", response.data.email);
					localStorage.setItem("token", response.data.token);

					if(response.data.role ==="ROLE_ADMIN"){
						$state.go("admin");
						$scope.message = "Login Successful";
					}else if(response.data.role === "ROLE_USER"){
						$state.go("home");
						$scope.message = "Login Successful";
					}else{
						
						if(response.data.message ==="wrong password"){
							$scope.message = response.data.message+" Remaing Attempts : "+response.data.Reaming_Attempts;
						}else{
							$scope.message ="Username and password not found";
						}
					}
				
	            })
	            .catch(function(error) {
	                $scope.message = "username and password not found";
	                console.error(error);
	            });
	    };
});

// angular.module("myApp")
// .controller("LoginController", function ($scope, $http, $window) {


// 	$scope.loginData = {
// 	    username: "",
// 	    password: ""
// 	};

// 	$scope.message = "";

// 	$scope.login = function() {
// 	    console.log("Login clicked", $scope.loginData);

// 	    $http({
// 	        method: 'POST',
// 	        url: '/Springmvc_Mongo/employee/login',
// 	        data: $scope.loginData, // send as JSON
// 	        headers: {
// 	            'Content-Type': 'application/json' // important
// 	        },
// 	        withCredentials: true // send cookies/session
// 	    })
// 	    .then(function(response) {
// 	        $scope.message = "Login Successful ✅";
// 	        // redirect to home page
// 	        $window.location.href = "app/components/HomeComponents/partials/home.html";
// 	    })
// 	    .catch(function(error) {
// 	        console.log(error);
// 	        $scope.message = "Login Failed ❌";
// 	    });
// 	};
// });
