angular.module("errorModule", ["ui.router"])
.config(function($stateProvider) {
    $stateProvider.state("access-denied", {
        url: "/access-denied",
        templateUrl: "app/components/ErrorComponents/partials/accessDenied.html"
    });
});
