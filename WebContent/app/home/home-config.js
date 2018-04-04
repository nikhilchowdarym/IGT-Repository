angular.module("application").config(function($stateProvider,$urlRouterProvider){
	$urlRouterProvider.otherwise("/home");
	$stateProvider.state("home",{
		url:"/home",
		views:{
			"body":{templateUrl:"app/home/home.html",controller:"homeController"}
		}
	});
	
})