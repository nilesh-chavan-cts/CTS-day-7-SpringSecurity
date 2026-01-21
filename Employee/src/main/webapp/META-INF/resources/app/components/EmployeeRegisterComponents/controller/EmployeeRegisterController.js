angular.module("EmployeeRegisterModule").controller("EmployeeRegisterController", EmployeeRegisterController);

function EmployeeRegisterController($scope, EmployeeFactory) {
    // Initialize employee model
    $scope.employee = {};

    // Register employee
    $scope.registerEmployee = function() {
        if(!$scope.employeeForm.$valid){
            alert("Please fill all required fields!");
            return;
        }
		console.log($scope.employee);

        EmployeeFactory.addEmployee($scope.employee)
            .then(function(response) {
                alert("Employee registered successfully!");
                $scope.employee = {}; // reset form
                $scope.employeeForm.$setPristine();
                $scope.employeeForm.$setUntouched();
            })
            .catch(function(err) {
                console.error(err);
                alert("Failed to register employee!");
            });
    };

    // Reset form
    $scope.resetForm = function() {
        $scope.employee = {};
        $scope.employeeForm.$setPristine();
        $scope.employeeForm.$setUntouched();
    };
}
