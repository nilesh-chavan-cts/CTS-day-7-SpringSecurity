angular.module("myApp")

.controller("HelloController", function($scope, $http) {

    $scope.message = "Loading...";

    $http.get("/Springmvc_Mongo/api/hello")
        .then(function(response) {
            $scope.message = response.data;
        })
        .catch(function(error) {
            $scope.message = "Error fetching message!";
            console.error(error);
        });
});
