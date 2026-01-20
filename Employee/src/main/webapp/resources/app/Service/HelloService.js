angular.module("myHelloApp")
	.factory("HelloFactory", function($http) {

		var factory = {};

		factory.getHello = function() {
			return $http.get("/Springmvc_Mongo/employee/api/hello");
		};

		factory.login = function(loginData) {
			return $http.post("/Springmvc_Mongo/employee/login", loginData, {
				withCredentials: true
			});
		};

		factory.home = function() {
			return $http.get("/Springmvc_Mongo/employee/list-employee", {
				withCredentials: true
			});
		}
		return factory;
	});
