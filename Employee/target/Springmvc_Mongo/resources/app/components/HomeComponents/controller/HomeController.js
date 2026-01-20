angular.module("homeModule").controller("HomeController", HomeController);

function HomeController($scope, $http) {
	
	
	
	$scope.employees = [];
	    $scope.errorMessage = "";

	    $http.get("/Springmvc_Mongo/api/list-employee")
	        .then(function(response) {
	            // response.data is List<Employee>
	            $scope.employees = response.data;
	        })
	        .catch(function(error) {
	            console.error(error);
	            $scope.errorMessage = "Failed to load employee list";
	        });
	/*$scope.message = "Loading...";

	    $http({
	        method: 'GET',
	        url: '/Springmvc_Mongo/api/hello',
	        responseType: 'text'   // 👈 VERY IMPORTANT
	    })
	    .then(function(response) {
	        $scope.message = response.data;
	    })
	    .catch(function(error) {
	        $scope.message = "Error calling API";
	        console.error(error);
	    });*/
};