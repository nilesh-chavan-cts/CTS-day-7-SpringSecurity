angular.module("homeModule").controller("HomeController", HomeController);

function HomeController($scope, $state, EmployeeFactory) {

    // read email from localStorage
    $scope.email = localStorage.getItem("email");

    // login check
    $scope.isLoggedIn = !!$scope.email; // true if email exists
    $scope.showProfile = false;
    $scope.profile = {};

    // open profile modal
    $scope.openProfile = function () {
        if (!$scope.email) {
            $state.go("login");
            return;
        }

        $scope.showProfile = true;

        EmployeeFactory.getProfile($scope.email)
            .then(function (res) {
                $scope.profile = res.data;
            })
            .catch(function () {
                alert("Failed to load profile");
            });
    };

    // logout
    $scope.logout = function () {
		localStorage.removeItem("email");
        localStorage.clear();
        $scope.isLoggedIn = false;
        $scope.showProfile = false;
        $state.go("login");
    };

    // go login
    $scope.goLogin = function () {
        $state.go("login");
    };

    // add employee (ADMIN use-case)
    $scope.addEmployee = function () {
        $state.go("register");
    };
}
