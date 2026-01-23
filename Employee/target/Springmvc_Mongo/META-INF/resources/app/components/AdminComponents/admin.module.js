angular.module("AdminModule",["ui.router"])
.constant("ADMIN_PATH", "app/components/AdminComponents")
 .config(function ($stateProvider, ADMIN_PATH) {

     $stateProvider.state("admin", {
         url: "/admin",
         templateUrl: ADMIN_PATH + "/partials/admin.html",
         controller: "AdminController"
     });

 });
