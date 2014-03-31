(function () {
	'use strict';

	var dependencies = [];

	angular.module('poolingpeopleAppFilter', dependencies)

		.filter('minutesToHours', [
			function () {
				return function (minutes) {
					var h = parseInt(minutes / 60, 10),
						m = minutes % 60 / 60,
						output = h + m;

					return output ? output : 0;
				};
			}])

		.filter('hoursToMinutes', [
			function () {
				return function (hours) {
					var output = ("" + hours).replace(",", ".") * 60; 
					return output ? output : 0;
				}
			}])

		.filter('dateToNumber', [
			function () {
				return function (date) {
					var output = moment(date).valueOf();
					return output ? output : 0;
				}
			}])

		.filter('numberToDate', [
			function () {
				return function (date, format) {
					var output = moment(date).toDate();
					return output ? output : 0;
				}
			}])

		.filter('range', function() {
			return function(input, total) {
				total = parseInt(total);
				for (var i=0; i<total; i++)
					input.push(i);
				return input;
				};
			})

		.filter('startFrom', function() {
		    return function(input, start) {
		        start = +start; //parse to int
		        return (start < 1) ? input : input.slice(start);
		    }
		});
}());