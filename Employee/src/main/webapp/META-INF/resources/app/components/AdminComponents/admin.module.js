angular.module("AdminModule",["ui.router","oc.lazyLoad"])
.constant("ADMIN_PATH", "app/components/AdminComponents")
 .config(function ($stateProvider, ADMIN_PATH) {

     $stateProvider.state("admin", {
         url: "/admin",
         templateUrl: ADMIN_PATH + "/partials/admin.html",
         controller: "AdminController",
		 resolve: {
		       loadAdminFiles: function ($ocLazyLoad) {
		           return $ocLazyLoad.load([
		               "app/components/AdminComponents/controller/AdminController.js",
		               "app/directives/employeeList.directive.js"
		           ]);
		       }
		   }
     });

 });
