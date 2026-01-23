angular.module("AdminModule")
.directive("helloWorld", function() {
    return {
        restrict: "E",   // Element directive
        templateUrl: "app/directives/hello-world.html"
    };
});
