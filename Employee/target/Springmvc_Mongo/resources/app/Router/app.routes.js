angular.module("myApp")
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
        });
});
