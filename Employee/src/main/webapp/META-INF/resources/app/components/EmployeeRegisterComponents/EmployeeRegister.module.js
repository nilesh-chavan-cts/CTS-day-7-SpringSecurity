angular.module("EmployeeRegisterModule",["ui.router"])
.constant("EMPLOYEEREGISTER_PATH","app/components/EmployeeRegisterComponents")
.config(function($stateProvider,EMPLOYEEREGISTER_PATH){
	
	$stateProvider.state("add-employee",{
		
		url:"/add-employee",
		templateUrl:EMPLOYEEREGISTER_PATH+"/partials/EmployeeRegister.html",
		controller:EmployeeRegisterController
	})
})