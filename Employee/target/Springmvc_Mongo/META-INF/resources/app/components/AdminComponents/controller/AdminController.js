angular.module("AdminModule").controller("AdminController", AdminController);

function AdminController($scope, $state, EmployeeFactory) {
	$scope.employees = [];
	$scope.errorMessage = "";
	$scope.currentPage = 0;
	$scope.pageSize = 5;
	$scope.totalPages = 1;

	$scope.loadEmployees = function(page) {
		page = page || 0;
		EmployeeFactory.getEmployees(page, $scope.pageSize)
			.then(function(response) {
				$scope.employees = response.data.content.map(emp => ({
					id: emp.id,
					firstName: emp.firstName,
					lastName: emp.lastName,
					email: emp.email,
					phoneNo: emp.phoneNo,
					dept: emp.dept,
					address: emp.address,
					salary: emp.salary,
					role: emp.role,
					gender: emp.gender
				}));
				$scope.totalPages = response.data.totalPages;
				$scope.currentPage = page;
			})
			.catch(function(err) {
				console.error(err);
				$scope.errorMessage = "Failed to load employees.";
			});
	};

	// Initial load
	$scope.loadEmployees();

	// Pagination functions
	$scope.prevPage = function() {
		if ($scope.currentPage > 0) $scope.loadEmployees($scope.currentPage - 1);
	};
	$scope.nextPage = function() {
		if ($scope.currentPage < $scope.totalPages - 1) $scope.loadEmployees($scope.currentPage + 1);
	};

	// View Employee
	/* $scope.viewEmployee = function(emp) {
		 $scope.selectedEmployee = angular.copy(emp);
		 var myModal = new bootstrap.Modal(document.getElementById('viewModal'));
		 myModal.show();
	 };
 
	 // Update Employee
	 $scope.editEmployee = function(emp) {
		 $scope.selectedEmployee = angular.copy(emp);
		 var myModal = new bootstrap.Modal(document.getElementById('editModal'));
		 myModal.show();
	 };
 */
	$scope.updateEmployee = function() {
		EmployeeFactory.updateEmployee($scope.selectedEmployee)
			.then(function(response) {
				alert("Employee updated successfully!");

				// Reload the employee list
				$scope.loadEmployees($scope.currentPage);

				// Hide custom modal
				$scope.showEditModal = false;

			})
			.catch(function(err) {
				console.error(err);
				alert("Failed to update employee!");
			});
	};


	$scope.deleteEmployee = function(emp) {
		$scope.selectedEmployee = emp;
		$scope.showDeleteModal = true;
		EmployeeFactory.deleteEmployee($scope.selectedEmployee.id)
			.then(function(response) {
				alert("Employee deleted successfully!");
				$scope.loadEmployees($scope.currentPage);
				$scope.showDeleteModal = false;
			})
			.catch(function(err) {
				console.error(err);
				alert("Employee deleted successfully!");
			});
		}
	
		$scope.showViewModal = false;
		$scope.showEditModal = false;

		$scope.viewEmployee = function(emp) {
			$scope.selectedEmployee = angular.copy(emp);
			$scope.showViewModal = true;
		};

		$scope.editEmployee = function(emp) {
			$scope.selectedEmployee = angular.copy(emp);
			$scope.showEditModal = true;
		};

	}
