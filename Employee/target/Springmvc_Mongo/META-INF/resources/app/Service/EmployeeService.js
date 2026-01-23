angular.module("myApp")
.factory("EmployeeFactory", function($http) {

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
	factory.register= function(registerData){
		
		return $http.post("/Springmvc_Mongo/employee/register",registerData,{
			withCredentials:true
		});
	}
	factory.getProfile= function(email){
	    return $http.get(`/Springmvc_Mongo/employee/profile/${email}`,{withCredentials:true});
	}

	factory.addEmployee= function(employeeData){
			
		return $http.post("/Springmvc_Mongo/employee/add",employeeData,{
			withCredentials:true
		});
	}
	factory.getEmployees = function(page, size){
	    return $http.get(`/Springmvc_Mongo/employee/employee-list?page=${page}&size=${size}`, {withCredentials:true});
	};

	factory.updateEmployee = function(employee){
	    return $http.put(`/Springmvc_Mongo/employee/update/${employee.email}`, employee, {withCredentials:true});
	};

	factory.deleteEmployee = function(id){
	    return $http.delete(`/Springmvc_Mongo/employee/delete/${id}`, {withCredentials:true});
	};
	factory.sendOtp= function(email){
	    return $http.get(
	        "/Springmvc_Mongo/employee/mail-test/" + email
	    );
	}

    return factory;
});
