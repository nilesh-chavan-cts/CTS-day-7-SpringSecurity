angular.module("myApp", [
    "homeModule",
    "loginModule",
    "AdminModule",
    "registerModule",
    "EmployeeRegisterModule",
    "ui.router",
    "EmailModule",
    "errorModule",
	"oc.lazyLoad",
])
.config(function($urlRouterProvider, $httpProvider) {

    // Default route
    $urlRouterProvider.otherwise("/home");

    // Add the auth interceptor
    $httpProvider.interceptors.push("authInterceptor");
});
