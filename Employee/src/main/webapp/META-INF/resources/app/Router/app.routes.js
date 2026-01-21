angular.module("myApp")
.config(function($stateProvider, $urlRouterProvider) {

    // Default route
    $urlRouterProvider.otherwise("/home");

    $stateProvider

        .state("login", {
            url: "/login",
            templateUrl: "app/components/LoginComponents/partials/login.html",
            controller: "LoginController"
        })

        .state("home", {
            url: "/home",
            templateUrl: "app/components/HomeComponents/partials/home.html",
            controller: "HomeController"
        })
		
		.state("register",{
			
			url:"/register",
			templateUrl:"app/components/RegisterComponents/partials/register.html",
			controller:"RegisterController"
		})
		.state("add-employee",{
			url:"/add-employee",
			templateUrl:"app/components/EmployeeRegisterComponents/partials/EmployeeRegister.html",
			controller:"EmployeeRegisterController"
		})
		
		.state("admin",{
			url:"/admin",
			templateUrl:"app/components/AdminComponents/partials/admin.html",
			controller:"AdminController"
		})
		
		.state("email",{
			url:"/email",
			templateUrl:"app/components/EmailComponents/partials/email.html",
			controller:"EmailController"
		})
});
