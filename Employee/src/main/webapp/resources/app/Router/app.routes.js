angular.module("myHelloApp")
.config(function($stateProvider, $urlRouterProvider) {

    // Default route
    $urlRouterProvider.otherwise("/login");

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
		
		.state("hello",{
			
			url:"/hello",
			templateUrl:"app/components/HelloConponents/partials/hello.html",
			controller:"HelloController"
		});
});
