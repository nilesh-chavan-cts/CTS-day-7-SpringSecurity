angular.module("loginModule", ["ui.router","oc.lazyLoad"])
    .constant("LOGIN_PATH", "app/components/LoginComponents")
    .config(function ($stateProvider, LOGIN_PATH) {

        $stateProvider.state("login", {
            url: "/login",
            templateUrl: LOGIN_PATH + "/partials/login.html",
            controller: "LoginController",
			resolve: {
				       loadLoginFiles: function ($ocLazyLoad) {
				           return $ocLazyLoad.load([
				               "app/components/LoginComponents/controller/LoginController.js",
				               "app/components/LoginComponents/partials/login.html"
				           ]);
				       }
				   }
        });

    });
