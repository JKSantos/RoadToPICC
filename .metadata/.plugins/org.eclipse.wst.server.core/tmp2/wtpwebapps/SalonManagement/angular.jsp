<!DOCTYPE html>
<html ng-app>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mtnc-emp.css"/>
  <link rel="stylesheet" type="text/css" href="./css/datepicker.min.css">
  <link rel="stylesheet" type="text/css" href="./css/datepicker.css">
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body class="blue-grey lighten-5">
  <table class="table" ts-wrapper>
    <thead>
      <tr>
        <th ts-criteria="Id">Id</th>
        <th ts-criteria="Name|lowercase" ts-default>Name</th>
        <th ts-criteria="Price|parseFloat">Price</th>
        <th ts-criteria="Quantity|parseInt">Quantity</th>
      </tr>
    </thead>
    <tbody>
      <tr ng-repeat="item in items track by item.Id" ts-repeat>
        <td>{{item.Name}}</td>
        <td>{{item.Price | currency}}</td>
        <td>{{item.Quantity}}</td>
      </tr>
    </tbody>
  </table>

        

  </div>

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/materialize.min.js"></script>
    <script type="text/javascript" src="./js/angular.min.js"></script>
    <script type="text/javascript" src="./js/datepicker.min.js"></script>
    <script type="text/javascript" src="./js/datepicker.js"></script>

    <script type="text/javascript">
         var app = angular.module('app', []);

         app.controller('MainCtrl', function($scope) {
           $scope.orderByField = 'firstName';
           $scope.reverseSort = false;
           
           $scope.data = {
             employees: [{
               firstName: 'John',
               lastName: 'Doe',
               age: 30
             },{
               firstName: 'Frank',
               lastName: 'Burns',
               age: 54
             },{
               firstName: 'Sue',
               lastName: 'Banter',
               age: 21
             }]
           };
         });
    </script>

  </body>

</html>