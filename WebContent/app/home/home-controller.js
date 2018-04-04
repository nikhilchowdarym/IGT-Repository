angular.module("application").controller("homeController",function($scope,$http,$window){
	$scope.users={};
	$scope.login = function (){
		var username = $scope.users.userName;
		
		$http.post("http://localhost:8080/SampleApp/login.do",$scope.users)
		.success(function (data){			
			if(data.message==username){
				$window.location.href = 'http://localhost:8080/SampleApp/#/tasks';
			}
			else{
				alert(data.message);
			}
		})
		.error(function(){
			alert("ERROR IN HOME");
		});
	}
});