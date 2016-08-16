(function () {
    'use strict';

    angular
        .module("app", ['datatables', 'checklist-model', 'ngResource', 'oitozero.ngSweetAlert', 'filters']);

    angular
        .module('filters', [])
        .filter('truncate', function () {
        return function (text, length, end) {
            if (isNaN(length))
                length = 10;

            if (end === undefined)
                end = "...";

            if (text.length <= length || text.length - end.length <= length) {
                return text;
            }
            else {
                return String(text).substring(0, length-end.length) + end;
            }

        };
    }).directive('format', ['$filter', function ($filter) {
        return {
            require: '?ngModel',
            link: function (scope, elem, attrs, ctrl) {
                if (!ctrl) return;


                ctrl.$formatters.unshift(function (a) {
                    return $filter(attrs.format)(ctrl.$modelValue)
                });


                ctrl.$parsers.unshift(function (viewValue) {

                    elem.priceFormat({
                        prefix: 'Php ',
                        centsSeparator: '.',
                        thousandsSeparator: ','
                    });

                    return elem[0].value;
                });
            }
        };
    }]);
})();
