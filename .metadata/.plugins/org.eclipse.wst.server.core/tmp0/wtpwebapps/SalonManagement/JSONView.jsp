<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html data-ng-app="jsonSample">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>JSON SAMPLE</title>
	</head>
	<body data-ng-controller="jsonController">
		<button data-ng-click="click()">Click me</button>
	</body>
	<script type="text/javascript" src="js/angular.min.js"></script>
	<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
	<script type="text/javascript">
		
		var app = angular.module('jsonSample', []);
		
		app.controller('jsonController', function($scope) {
			
			var movies = {"action": "splinter cell", "scifi":"chaos theory", "horror":"double agent"};
				
			var jsonData = {
					"jsonData":{
						"name": "Jeffrey",
						"age":16,
						"movies": movies
					}
			}
			
			
			
			$scope.click = function() {
				alert("HELLO");
				$.ajax({
		            url: "postJSON"+"?jsonData="+JSON.stringify(jsonData),
		            type: 'POST',
		            dataType: 'json',           
		            success:function(response){
		                alert("success!");                          
		            },
		            error:function(jqXhr, textStatus, errorThrown){
		                alert(textStatus);
		            }
		        });    
			}
			
			
		});
	
	</script>
</html>