angular.module("myApp")
.factory("authInterceptor", function ($q, $injector) {

    return {

        // 1️⃣ Attach JWT to every outgoing request
        request: function (config) {
            var token = localStorage.getItem("token");

            if (token) {
                config.headers = config.headers || {};
                config.headers.Authorization = "Bearer " + token;
            }

            return config;
        },

        // 2️⃣ Handle 401 / 403 responses globally
        responseError: function (rejection) {

            if (rejection.status === 401 || rejection.status === 403) {
                var $state = $injector.get("$state");

                // Optional: clear invalid token
                localStorage.clear();

                $state.go("access-denied"); // must match ui-router state name
            }

            return $q.reject(rejection);
        }
    };
})
.config(function ($httpProvider) {
    $httpProvider.interceptors.push("authInterceptor");
});
