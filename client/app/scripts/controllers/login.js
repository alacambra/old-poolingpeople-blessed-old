(function () {
	'use strict';

	angular.module('poolingpeopleApp')

		.controller('LoginCtrl', function ($scope, $rootScope, SessionService, $modal) {

			$rootScope.$on("requiredAuth", function(toState, toParams, fromState, fromParams) {
	            var toState = toState, toParams = toParams, fromState = fromState, fromParams = fromParams;
	            $scope.showLoginModal("You need to be authenticated", function(loginPromise) {
	            	loginPromise.then(function() {
	                	$rootScope.$state.go(toParams.name)
	            	})
	            }, function() {
	                $rootScope.$state.go(fromParams.name)
	            });
	        });

			$scope.loggedIn = SessionService.loggedIn;

			$scope.showLoginModal = function (message, callback, dismissCallback) {

				var modalInstance = $modal.open({
					templateUrl: 'views/login_modal.tpl.html',
					controller: 'LoginModalCtrl',
					resolve: {
						message: function() {
							return message || ""
						},
						callback: function () {
							return callback || function() {}
						},
						dismissCallback: function () {
							return dismissCallback || function() {}
						}
					}
				});

				modalInstance.result.then(function (data) {
					var loginPromise = SessionService.logIn(data.loginData.username, data.loginData.password);
					data.callback(loginPromise);
				});
			};

			$scope.logOut = function() {
				SessionService.logOut();
				$rootScope.$state.go("home");
			}

			$scope.userData = SessionService.userData;

		});
}());