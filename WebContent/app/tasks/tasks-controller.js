angular.module("application").controller("tasks",function($scope,$http){
		$scope.obj={};
		$scope.obj1={};
		var test="";
		//document.getElementById('showDiv').style.display = 'none';
		$('.showDiv').css("display","none");	
		$scope.showUpdate = false;	
		$scope.showAdd = true;
		$scope.showBack = false;
		$scope.submit=function(){
			$('.showDiv').css("display","block");
			  $http.post("http://localhost:8080/SampleApp/inserTask.do",$scope.obj)
			  .success(function(data){
				  $scope.data=data;
				 test=$scope.obj;
			  })
			  .error(function(data){
				  alert("error"+data);
			  })
			
			}
		
		$scope.viewAll=function(){
			$('.showDiv').css("display","block");			
				$http.get("http://localhost:8080/SampleApp/getAllTasks.do")
			  .success(function(data){
				  $scope.data=data;
			  })
			  .error(function(data){
				  alert("error"+data);
			  })
		}
		
		$scope.editTasks=function(id){
			$scope.showAdd = false;
			$scope.showUpdate = true;
			$scope.showBack = true;

			  $http.post("http://localhost:8080/SampleApp/findById.do",id)
			  .success(function(data){
				  $scope.data1=data;
				  $scope.obj=data;
				  $('#taskid').val(data.id);
				  $('#taskName').val(data.taskName);
				  $('#taskStatus').val(data.taskStatus);
			  })
			  .error(function(data){
				  alert("error"+data);
			  })
			
			}
		
		$scope.update=function(){
			$scope.showBack = true;
			$('.showDiv').css("display","block");
			$('#viewAll').css("display","block");
			
			//alert($scope.obj.taskName);
			  $http.post("http://localhost:8080/SampleApp/updateTask.do",$scope.obj)
			  .success(function(data){
				  $scope.data=data;
				  $scope.obj.taskName= '';
				  $scope.obj.taskStatus = '';
				  $scope.obj.personName= '';
			  })
			  .error(function(data){
				  alert("error"+data);
			  })
			
			}
		
		$scope.deleteTasks=function(){
			$('.showDiv').css("display","block");
			var taskName=document.getElementById("taskid").value;
			  $http.post("http://localhost:8080/SampleApp/deleteTaskByName.do",taskName)
			  .success(function(data){
				  $scope.data=data;
			  })
			  .error(function(data){
				  alert("error"+data);
			  })
			
			}
		
		
		$scope.back = function (){
			$scope.showAdd = true;
			$scope.showUpdate = false;
			$scope.showBack = false;
			$scope.obj.taskName= '';
   		    $scope.obj.taskStatus = '';
		    $scope.obj.personName= '';
			
		}
});