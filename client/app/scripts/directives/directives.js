(function () {
	'use strict';

	var ENTER_KEY = 13;

	var dependencies = [
		'poolingpeopleAppFilter'
	];

	angular.module('poolingpeopleAppDirective', dependencies)

		.directive('ppModel', ['$log',
			function ($log) {
				return {
					restrict: 'A',
					priority: 1,
					require: 'ngModel',
					link: function ($scope, $elem, $attrs, ngModelCtrl) {
						// the update function
						var update = function () {
							$scope.$apply(function () {
								$elem.off('blur');
								$elem.off('keydown');
		                        if (!$elem.hasClass("ng-pristine"))
		                            $scope.$eval($attrs.ppModel);
		                        else
		                            $scope.$eval($attrs.ppModelPristine);
								ngModelCtrl.$render();
							});
						};

						// remove events used by ngModel
						$elem.off('change').off('keydown');

						// update model on blur
						$elem.on({
							blur: function() {
								update();	
							},
							keydown: function (event) {
								if (event.which === ENTER_KEY) {
									$elem.blur();
								}
							},
		                    change: function() {
		                        $elem.blur();
		                    }
						});

						$elem.focus();
					}
				};
			}])

		.directive('ppConvertDate', ['$filter', '$log',
			function ($filter, $log) {
				return {
					restrict: 'A',
					require: 'ngModel',
					link: function ($scope, $elem, $attrs, ngModelCtrl) {
						ngModelCtrl.$formatters.push(function (modelValue) {
							if (_.isNull(modelValue)) {
								return null;
							}
							return $filter('date')(modelValue, 'dd.MM.yyyy');
						});

						ngModelCtrl.$parsers.push(function (viewValue) {
							if (_.isUndefined(viewValue) || _.isNull(viewValue)) {
								return null;
							}
							return moment(viewValue).valueOf();
						});
					}
				};
			}])

		/*
		THIS DIRECTIVE IS NOT FINISHED - CUSTOM VALIDATION EXAMPLE by TS-2014-02-19
		 */
		.directive('ppValidateDate', ['$filter', '$log',
			function ($filter, $log) {
				return {
					restrict: 'A',
					require: 'ngModel',
					link: function ($scope, $elem, $attrs, ngModelCtrl) {
						var min = $attrs.min || null,
							max = $attrs.max || null,
							validate = function (value) {
								var validMin = _.isNull(min) || moment(min, 'yyyy-MM-dd').valueOf() - value <= 0,
									validMax = _.isNull(max) || moment(max, 'yyyy-MM-dd').valueOf() - value >= 0;

								ngModelCtrl.$setValidity('min', validMin);
								ngModelCtrl.$setValidity('max', validMax);

								return validMin && validMax ? value : null;
							};

						ngModelCtrl.$formatters.unshift(function (modelValue) {
							return validate(modelValue);
						});

						ngModelCtrl.$parsers.unshift(function (viewValue) {
							return validate(moment(viewValue).valueOf());
						});
					}
				}
			}])

		.directive('ppConvertTime', ['$filter', '$log',
			function ($filter, $log) {
				return {
					restrict: 'A',
					require: 'ngModel',
					link: function ($scope, $elem, $attrs, ngModelCtrl) {
						ngModelCtrl.$formatters.push(function (modelValue) {
							return $filter('minutesToHours')(modelValue, $attrs.ppConvertTime);
						});

						ngModelCtrl.$parsers.push(function (viewValue) {
							return $filter('hoursToMinutes')(viewValue, $attrs.ppConvertTime);
						});
					}
				};
			}])

		.directive('ppConvertPercent', ['$filter', '$log',
			function ($filter, $log) {
				return {
					restrict: 'A',
					require: 'ngModel',
					link: function ($scope, $elem, $attrs, ngModelCtrl) {
						ngModelCtrl.$formatters.push(function (modelValue) {
							return modelValue * 100;
						});

						ngModelCtrl.$parsers.push(function (viewValue) {
							return $filter('number')(viewValue / 100, 4);
						});
					}
				};
			}])

		.directive('setFocus', ['$log', '$timeout',
			function ($log, $timeout) {
				return {
					restrict: 'A',
					priority: 0,
					link: function ($scope, $elem, $attrs) {
						$timeout(function() {
							$elem.focus();
						})
					}
				}
			}])

		.directive('ngClickOut', ['$log', '$document',
			function ($log, $document) {
				return {
					restrict: 'A',
					priority: 1000,
					link: function ($scope, $elem, $attrs) {
				      $document.bind('click', function(event) {
				      	var clickedOut = false,
				      		ancestors = angular.element(event.target).parents(),
				      		$target = angular.element(event.target);
				      	if ($target.is(':visible')) {
				      		clickedOut = true
					      	angular.forEach(ancestors, function (element) {
					      		if (element == $elem[0]) {
					      			clickedOut = false;
					      		}
					      	})
						    if (clickedOut === true) {
				                $scope.$apply(function (){
							    	$scope.$eval($attrs.ngClickOut)
									$document.unbind('click');
							    });
						    }
					      }
				      });
					}
				}
			}])

		.directive('ngEnter', function () {
		    return function ($scope, $elem, $attrs) {
		        $elem.bind("keydown keypress", function (event) {
		            if(event.which === 13 && $elem.is(':focus')) {
		                $scope.$apply(function (){
		                    $scope.$eval($attrs.ngEnter);
		                });
		                event.preventDefault();
		            }
		        });
		    };
		})
		.directive('ngLoading', function (LoadStatusService) {
		  return {
		    priority: 100,
		    restrict: 'A',
		    link: function ($scope, $element, $attr) {
		    	var doContrary = $attr.ngLoading.indexOf("!") === 0,
		    		targetResource = doContrary ? $attr.ngLoading.substr(1) : $attr.ngLoading;

		    	$scope.$watch(function() { // LoadStatusService.isLoading(targetResource) ⊕ doContrary
		    		if ((LoadStatusService.isLoading(targetResource) && doContrary) ||
		    		    (!LoadStatusService.isLoading(targetResource) && !doContrary)) {
		    			$element.hide();
		    		} else {
		    		    $element.show();
		    		}
		    	}, true);
		    }
		  };
		});
}());