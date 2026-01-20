<!DOCTYPE html>
<html ng-app="myHelloApp">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.29/angular-ui-router.min.js"></script>

<!-- Main App -->
<script src="app/app.js"></script>
<script src="app/Router/app.routes.js"></script>

<script src="app/components/HelloConponents/hello.module.js"></script>
<script src="app/components/HomeComponents/home.module.js"></script>

<script src="app/components/LoginComponents/login.module.js"></script>


<!-- Controller & Service -->
 <script src="app/components/HomeComponents/controller/HomeController.js"></script>

<script src="app/components/LoginComponents/controller/LoginController.js"></script>

<script src="app/components/HelloConponents/controller/HelloController.js"></script> 

<script src="app/Service/HelloService.js"></script>

</head>

<body ng-app="myHelloApp">

	<!--<div ng-include="'app/components/HelloConponents/partials/hello.html'"></div>-->
    <ui-view></ui-view>
   <!-- <div ng-include="'app/components/HomeComponents/partials/home.html'"></div>-->
	
	<!-- <div ng-include="'app/components/LoginComponents/partials/login.html'"></div> -->
	


</body>
</html>