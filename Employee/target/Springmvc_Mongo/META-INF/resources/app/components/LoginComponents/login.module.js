angular.module("loginModule", ["ui.router"])
    .constant("LOGIN_PATH", "app/components/LoginComponents")
    .config(function ($stateProvider, LOGIN_PATH) {

        $stateProvider.state("login", {
            url: "/login",
            templateUrl: LOGIN_PATH + "/partials/login.html",
            controller: "LoginController"
        });

    });
