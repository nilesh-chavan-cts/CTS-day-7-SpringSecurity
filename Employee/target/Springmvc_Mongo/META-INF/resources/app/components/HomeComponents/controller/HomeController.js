angular.module("homeModule").controller("HomeController", HomeController);

function HomeController($scope, $state, EmployeeFactory) {

    $scope.email = localStorage.getItem("email");
    $scope.isLoggedIn = !!$scope.email;
    $scope.profile = {};

	$scope.openProfile = function () {
	    if (!$scope.email) {
	        $state.go("login");
	        return;
	    }

	    EmployeeFactory.getProfile($scope.email)
	        .then(function (res) {
	            $scope.profile = res.data;

	            // Open Bootstrap modal
	            const modal = new bootstrap.Modal(document.getElementById('profileModal'));
	            modal.show();
	        })
	        .catch(function () {
	            alert("Failed to load profile");
	        });
	};


    $scope.logout = function () {
        localStorage.clear();
        $state.go("login");
    };
}
