(function () {
    'use strict';
    angular
        .module('app')
        .controller('productTagsReportController', productTagsReportController);

    function productTagsReportController($scope, $http, $filter, reportsFactory) {
		var vm = this;

		vm.productTagsSearch = '';
		vm.thisDate = new Date();
		vm.searchRep = 0;

		vm.dtInstanceCallback = dtInstanceCallback;
		vm.searchTable = searchTable;
		vm.printPdf = printPdf;
		vm.searchReport = searchReport;

		function dtInstanceCallback(dtInstance) {
			var datatableObj = dtInstance.DataTable;
			vm.tableInstance = datatableObj;
		}

		function searchTable() {
			var query = vm.productTagsSearch;
			vm.tableInstance.search(query).draw();
			console.log('search');
		}

		reportsFactory.getProductTags().then(function (data) {
			vm.productTags = data.data.tagSum;
			console.log(vm.productTags);
		});



		function searchReport (datFrom, datTo) {
			var datFrom = moment(datFrom).format("YYYY-MM-DD"),
				datTo = moment(datTo).format("YYYY-MM-DD");

			var dat = $.param({
				'dateFrom': datFrom,
				'dateTo': datTo
			});

			$http({
				method: 'post',
				url: 'getProductTags',
				data: dat,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(function successCallback(data) {
				vm.productTags = data.data.tagSum;
				vm.productTag = data.data.report;
				vm.totalProd = total(data.data.report);
				vm.searchRep = 1;
				var totalQ = 0,
					totalC = 0,
					totalD = 0,
					totalE = 0,
					totalL = 0;
				for(var i = 0; i < data.data.tagSum.length; i ++) {
					totalQ += data.data.tagSum[i].intQuantity;
					totalC += data.data.tagSum[i].intConsumed;
					totalD += data.data.tagSum[i].intDefective;
					totalE += data.data.tagSum[i].intExpired;
					totalL += data.data.tagSum[i].intLost;
				}
				vm.totalq = totalQ;
				vm.totalc = totalC;
				vm.totald = totalD;
				vm.totale = totalE;
				vm.totall = totalL;
				vm.datFrom = '';
				vm.datTo = '';
			}, function errorCallback(data) {

			});

		}

		function total(total) {
			let t = 0;

			angular.forEach(total, function(x, i) {
				t += x.intQuantity;
			});

			return t;
		}

		function printPdf(thisDiv) {
			var printContents = document.getElementById(thisDiv).innerHTML;
			var popupWin = window.open('', '_blank', 'width=800,height=800,scrollbars=no,menubar=no,toolbar=no,location=no,status=no,titlebar=no,top=10');
			popupWin.window.focus();
			popupWin.document.open();
			popupWin.document.write('<html><head>' +
				'<link type="text/css" rel="stylesheet" href="css/materialize.css"/>' +
				'<link rel="stylesheet" type="text/css" href="css/bartstable.css"/>' +
				'<link rel="stylesheet" type="text/css" href="css/material.min.css"/>' +
				'<link rel="stylesheet" type="text/css" href="css/baselayout.css"/>' +
				'</head><body onload="window.print()">' + '<div class="col s12">' + printContents + '</div>' + '</body></html>');
			popupWin.document.close();
		}

      }
})();
