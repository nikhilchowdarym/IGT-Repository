angular.module("application").config(function($stateProvider){
	$stateProvider.state("tasks",{
		url:"/tasks",
		views:{
		"body":{templateUrl:"app/tasks/tasks.html",controller:"tasks"},
		
		}
	});
});