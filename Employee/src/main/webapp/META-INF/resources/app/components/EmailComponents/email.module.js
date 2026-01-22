angular.module("EmailModule",["ui.router"])
.constant("EMAIL_PATH","app/components/EmailComponents")
.config(function($stateProvider, EMAIL_PATH){
	
	$stateProvider.state("email",{
		
		url:"/email",
		templateUrl:EMAIL_PATH+"/partials/email.html",
		controller:EmailController
	})
});