<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <title>AngularJS Page</title>

    <!-- AngularJS CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.3/angular.min.js"></script>

    <!-- Your AngularJS File -->
    <script src="${pageContext.request.contextPath}/resources/testingApp/app.js"></script>
	<script src="${pageContext.request.contextPath}/resources/testingApp/controller.js"></script>
</head>

<body ng-controller="TestingController">
    <h2>{{ message }}</h2>
	<h2>{{name}}</h2>
</body>
</html>