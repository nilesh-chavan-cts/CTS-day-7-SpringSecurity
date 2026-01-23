angular.module("registerModule",["ui.router"])
.constant("REGISTER_PATH", "app/components/RegisterComponents")
.config(function ($stateProvider, REGISTER_PATH) {

    $stateProvider.state("register", {
        url: "/register",
        templateUrl: REGISTER_PATH + "/partials/register.html",
        controller: "RegisterController"
    });

});