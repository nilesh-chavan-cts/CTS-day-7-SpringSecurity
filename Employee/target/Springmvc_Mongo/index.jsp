<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.29/angular-ui-router.min.js"></script>

<!-- Bootstrap 5 -->
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet"
         href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>


   <!-- Your Global CSS -->
   <link rel="stylesheet" href="app/shared/css/index.css">
	<link rel="stylesheet" href="app/shared/css/Admin/admin.css">
<!-- Main App -->
<script src="app/app.js"></script>

<!--<script src="app/Router/app.routes.js"></script>-->
<script src="app/shared/services/auth.interceptor.js"></script>

<script src="app/components/HomeComponents/home.module.js"></script>

<script src="app/components/LoginComponents/login.module.js"></script>

<script src="app/components/RegisterComponents/register.module.js"></script>

<script src="app/components/AdminComponents/admin.module.js"></script>

<script src="app/components/EmployeeRegisterComponents/EmployeeRegister.module.js"></script>

<script src="app/components/EmailComponents/email.module.js"></script>

<script src="app/components/ErrorComponents/error.module.js"></script>


<script src="app/directives/employeeList.directive.js"></script>

<!-- Controller & Service -->
 <script src="app/components/HomeComponents/controller/HomeController.js"></script>

<script src="app/components/LoginComponents/controller/LoginController.js"></script>

<script src="app/components/RegisterComponents/controller/RegisterController.js"></script>

<script src="app/components/AdminComponents/controller/AdminController.js"></script>

<script src="app/components/EmployeeRegisterComponents/controller/EmployeeRegisterController.js"></script>

<script src="app/components/EmailComponents/controller/EmailController.js"></script>

<script src="app/Service/EmployeeService.js"></script>



</head>

<body ng-app="myApp">

	<!--<div ng-include="'app/components/HelloConponents/partials/hello.html'"></div>-->
    <ui-view></ui-view>
   <!-- <div ng-include="'app/components/HomeComponents/partials/home.html'"></div>-->
	
	<!-- <div ng-include="'app/components/LoginComponents/partials/login.html'"></div> -->
	


</body>
</html>