/**
 * Created by Castillo on 10/17/2016.
 */
(function () {
	'use strict';
	angular
		.module('app')
		.controller('salesCtrl', salesCtrl);

	function salesCtrl($scope, $http, $filter, reportsFactory) {
		var vm = this;

		vm.productTagsSearch = '';
		vm.thisDate = new Date();
		vm.searchRep = 0;

		vm.Type = 'all';
		vm.titleSales = 'All';

		vm.dtInstanceCallback = dtInstanceCallback;
		vm.searchTable = searchTable;
		vm.printPdf = printPdf;
		vm.searchReport = searchReport;

		vm.transType = transType;

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
				url: 'getSalesReport',
				data: dat,
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				}
			}).then(function successCallback(data) {
				vm.sales = data.data.sales;
				vm.salesTemp = data.data.sales;
				vm.searchRep = 1;
			}, function errorCallback(data) {

			});

		}

		function res(sales) {
			var s = [];

			angular.forEach(sales, function(item) {
				if(item.type == 'RESERVATION' || item.type == 'reservation') {
					s.push(item);
				}
			});

			return s;
		}

		function wi(sales) {
			var w = [];

			angular.forEach(sales, function(item) {
				if(item.type == 'WALKIN' || item.type == 'walkin') {
					w.push(item);
				}
			});

			return w;
		}

		function or(sales) {
			var o = [];

			angular.forEach(sales, function(item) {
				if(item.type == 'ORDER' || item.type == 'order') {
					o.push(item);
				}
			});

			return o;
		}

		function transType(type) {
			if (type == 'all') {
				vm.sales = vm.salesTemp;
				vm.titleSales = 'All';
			} else if (type == 'reservation') {
				vm.sales = res(vm.salesTemp);
				vm.titleSales = 'Reservation'
				console.log(vm.sales);
			} else if (type == 'walkin') {
				vm.titleSales = 'Walk In'
				vm.sales = wi(vm.salesTemp);
				console.log(vm.sales);
			} else if (type == 'order') {
				vm.titleSales = 'Order';
				vm.sales = or(vm.salesTemp);
				console.log(vm.sales);
			}
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
