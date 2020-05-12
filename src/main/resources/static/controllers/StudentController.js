var app = angular.module('myApp', []);
app.controller('StudentController', function($scope, $http) {
  $scope.newStudent={};
  $scope.editstd=true;
  $scope.selectStudent=function(){
	  $http({
		  method: 'GET',
		  url: '/student/'		 
		}).then(function(data) {
			  $scope.studentList=data.data;
		})		
  }  
  $scope.selectStudent();
  
  
  $scope.createStudent=function(){
	  $http({
		  method: 'POST',
		  url: '/student/',
		  data: $scope.newStudent
		}).then(function(data) {
			  $scope.selectStudent();
			  $scope.newStudent={};
		})		
  }
  
  $scope.updateStudent=function(){
	  $scope.editstd=false;
  }
  
  $scope.saveStudent=function(student){
	  $http({
		  method: 'PUT',
		  url: '/student/',
		  data: student
		}).then(function(data) {
			  $scope.selectStudent();
			  $scope.editstd=true;

		})		
  }
  $scope.deleteStudent=function(studentid){
	  $http({
		  method: 'DELETE',
		  url: '/student/'+studentid.id
		}).then(function(data) {
			  $scope.selectStudent();
		})		
  }
});