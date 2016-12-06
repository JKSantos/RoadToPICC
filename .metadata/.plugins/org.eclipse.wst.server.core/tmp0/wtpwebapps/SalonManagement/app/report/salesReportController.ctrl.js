/**
 * Created by Castillo on 10/17/2016.
 */
(function () {
	'use strict';
	angular
		.module('app')
		.controller('prodPurchase', prodPurchase);

	function prodPurchase($scope, $http, $filter, reportsFactory) {
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

		function searchReport (datFrom, datTo) {
			var datFrom1 = moment(datFrom).format("YYYY-MM-DD"),
				datTo1 = moment(datTo).format("YYYY-MM-DD");

			var dat = $.param({
				'dateFrom': datFrom1,
				'dateTo': datTo1
			});

			$http({
				method: 'post',
				url: 'getProductPurchases',
				data: dat,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(function successCallback(data) {
				vm.pos = data.data.purchases;
				console.log(vm.pos);
				vm.searchRep = 1;
				vm.datFrom = '';
				vm.datTo = '';
			}, function errorCallback(data) {

			});

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
