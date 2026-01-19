

angular.module("myApp")
.controller("LoginController", function ($scope, $http, $window) {


	$scope.loginData = {
	    username: "",
	    password: ""
	};

	$scope.message = "";

	$scope.login = function() {
	    console.log("Login clicked", $scope.loginData);

	    $http({
	        method: 'POST',
	        url: '/Springmvc_Mongo/employee/login',
	        data: $scope.loginData, // send as JSON
	        headers: {
	            'Content-Type': 'application/json' // important
	        },
	        withCredentials: true // send cookies/session
	    })
	    .then(function(response) {
	        $scope.message = "Login Successful ✅";
	        // redirect to home page
	        $window.location.href = "app/components/HomeComponents/partials/home.html";
	    })
	    .catch(function(error) {
	        console.log(error);
	        $scope.message = "Login Failed ❌";
	    });
	};
});
