(function () {
    'use strict';
    angular
        .module('app')
        .controller('reservationReportController', reservationReportController);

    function reservationReportController($scope, $filter, reportsFactory) {
        var vm = this;
           $scope.select = {};
        var moconsumed = [];
        var moexpired = [];
        var molost = [];
        var modefective = [];
        var mo2 = [];

        var quconsumed = [];
        var quexpired = [];
        var qulost = [];
        var qudefective = [];
        var qo2 = [];

        var anconsumed = [];
        var anexpired = [];
        var anlost = [];
        var andefective = [];
        var ao2 = [];   

        $scope.downloadMonthly = function(){
            function dlMonthly(handle){
            $.ajax({
                  url:'http://localhost:8080/SalonManagement/printStatisticalTagChart',
                  type: 'post',
                  data: {"type": "monthly"},
                  dataType: 'json',
                  async: true,
                  success: function (data) {
                    console.log("Success Employee");
                    handle(data);
                  },
                  error: function () {
                      console.log("Error in posting");
                  }
              });
            }

            dlMonthly(function(output){
                    console.log(output);
                    $.ajax({
                          url:'http://localhost:8080/SalonManagement/downloadReports',
                          type: 'post',
                          data: {"fileName": output},
                          dataType: 'json',
                          async: true,
                          success: function (data) {
                            console.log("Success Employee");
                            console.log(data);
                          },
                          error: function () {
                              console.log("Error in posting");
                          }
                      });
                
            });
        };
        
        function getMonthly(handle){
            $.ajax({
              url:'http://localhost:8080/SalonManagement/getProductPurchases',
              type: 'post',
              data: {"type": "monthly"},
              dataType: 'json',
              async: true,
              success: function (data) {
                console.log(data);
                handle(data);
              },
              error: function () {
                  console.log("Error in posting");
              }
          });
        }
        
        function getQuarterly(handle){
            $.ajax({
              url:'http://localhost:8080/SalonManagement/getProductPurchases',
              type: 'post',
              data: {"type": "quarterly"},
              dataType: 'json',
              async: true,
              success: function (data) {
                console.log(data);
                handle(data);
              },
              error: function () {
                  console.log("Error in posting");
              }
          });
        }
        
        
        
        $scope.generate = function(details){
            var psdata = {
                "type": "annually",
                "yearFrom": details.yearFrom,
                "yearTo": details.yearTo
            }
            
            function getAnnually(handle){
                console.log(psdata);
                $.ajax({
                      url:'http://localhost:8080/SalonManagement/getProductPurchases',
                      type: 'post',
                      data: psdata,
                      dataType: 'json',
                      async: true,
                      success: function (data) {
                        console.log("Success Employee");
                        console.log(data);
                        handle(data);
                      },
                      error: function () {
                          console.log("Error in posting");
                      }
                  });
                }

            getAnnually(function(output){
                 $scope.annuallTags = output; 
                 console.log($scope.annuallTags);
                 for(i = 0; i < $scope.annuallTags.length; i ++){
                        anconsumed.push($scope.annuallTags[i].delivery); 
                        anexpired.push($scope.annuallTags[i].eventService); 
                        anlost.push($scope.annuallTags[i].homeService); 
                        andefective.push($scope.annuallTags[i].pickup); 
                        ao2.push($scope.annuallTags[i].walkin);
                    }
                initAnnually(anconsumed, anexpired, anlost, andefective, details, ao2);
            });
            
        };
        
        
        getMonthly(function(output){
            console.log(output);
             $scope.monthTags = output; 
             for(i = 0; i < $scope.monthTags.length; i ++){
                    moconsumed.push($scope.monthTags[i].delivery); 
                    moexpired.push($scope.monthTags[i].eventService); 
                    molost.push($scope.monthTags[i].homeService); 
                    modefective.push($scope.monthTags[i].pickup);
                    mo2.push($scope.monthTags[i].walkin); 
             }
             initMonthly(moconsumed, moexpired, molost, modefective, mo2);
        });
        getQuarterly(function(output){
            console.log(output);
         $scope.quarterTags = output; 
         for(i = 0; i < $scope.quarterTags.length; i ++){
                quconsumed.push($scope.quarterTags[i].delivery); 
                quexpired.push($scope.quarterTags[i].eventService); 
                qulost.push($scope.quarterTags[i].homeService); 
                qudefective.push($scope.quarterTags[i].pickup); 
                qo2.push($scope.quarterTags[i].walkin); 
            }
        initQuarterly(quconsumed, quexpired, qulost, qudefective, qo2);
        });
        
        

        function initMonthly(consumed, expired, lost, defective, def2){
            
            $(function () {
                $('#containerMonthly').highcharts({
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: 'Monthly Product tags'
                    },
                    subtitle: {
                        text: 'Source: Database'
                    },
                    xAxis: {
                        categories: [
                            'Jan',
                            'Feb',
                            'Mar',
                            'Apr',
                            'May',
                            'Jun',
                            'Jul',
                            'Aug',
                            'Sep',
                            'Oct',
                            'Nov',
                            'Dec'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                             text: 'Pesos (Php)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} Php</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    legend: {
                               reversed: true
                   },
                   plotOptions: {
                       series: {
                           stacking: 'normal'
                       }
                   },
                    series: [{
                        name: 'Delivery',
                        data: consumed

                    }, {
                        name: 'Event Service',
                        data: expired

                    }, {
                        name: 'Home Service',
                        data: lost

                    }, {
                        name: 'Pick Up',
                        data: defective

                    }, {
                        name: 'Walk In',
                        data: def2

                    }]
                });
            });
        } // end monthly chart

        
        function initQuarterly(consumed, expired, lost, defective, qo2){
            $(function () {
                $('#containerQuarterly').highcharts({
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: 'Quarterly Product tags'
                    },
                    subtitle: {
                        text: 'Source: Database'
                    },
                    xAxis: {
                        categories: [
                            '1st',
                            '2nd',
                            '3rd',
                            '4th'
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Pesos (Php)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} Php</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    legend: {
                               reversed: true
                   },
                   plotOptions: {
                       series: {
                           stacking: 'normal'
                       }
                   },
                    series: [{
                        name: 'Delivery',
                        data: consumed

                    }, {
                        name: 'Event Service',
                        data: expired

                    }, {
                        name: 'Home Service',
                        data: lost

                    }, {
                        name: 'Pick Up',
                        data: defective

                    }, {
                        name: 'Walk In',
                        data: qo2

                    }]
                });
            });
        }// end quarterly

        
        function initAnnually(consumed, expired, lost, defective, details, ao2){
            $(function () {
                $('#containerAnnually').highcharts({
                    chart: {
                        type: 'bar'
                    },
                    title: {
                        text: 'Annuall Product tags'
                    },
                    subtitle: {
                        text: 'Source: Database'
                    },
                    xAxis: {
                        categories: [
                            details.yearFrom,
                            details.yearTo
                        ],
                        crosshair: true
                    },
                    yAxis: {
                        min: 0,
                        title: {
                             text: 'Pesos (Php)'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y:.1f} Php</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    legend: {
                               reversed: true
                   },
                   plotOptions: {
                       series: {
                           stacking: 'normal'
                       }
                   },
                    series: [{
                        name: 'Delivery',
                        data: consumed

                    }, {
                        name: 'Event Service',
                        data: expired

                    }, {
                        name: 'Home Service',
                        data: lost

                    }, {
                        name: 'Pick Up',
                        data: defective

                    }, {
                        name: 'Walk In',
                        data: ao2

                    }]
                });
            });
        }// end annually
             	
        
      }
})();
