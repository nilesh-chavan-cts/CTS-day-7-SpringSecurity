angular.module("homeModule",["ui.router"])
.constant("HOME_PATH", "app/components/HomeComponents")
.config(function($stateProvider, HOME_PATH) {

    $stateProvider.state("home", {
        url: "/home",
        templateUrl: HOME_PATH + "/partials/home.html",
        controller: "HomeController"
    });

});
