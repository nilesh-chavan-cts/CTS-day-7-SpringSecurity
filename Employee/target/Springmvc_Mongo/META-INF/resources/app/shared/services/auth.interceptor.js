angular.module("myApp")
.factory("authInterceptor", function($q, $injector) {
    return {
        responseError: function(rejection) {
            if (rejection.status === 401 || rejection.status === 403) {
                var $state = $injector.get("$state");
                $state.go("access-denied");  // must match errorModule state name
            }
            return $q.reject(rejection);
        }
    };
})
.config(function($httpProvider) {
    $httpProvider.interceptors.push("authInterceptor");
});
