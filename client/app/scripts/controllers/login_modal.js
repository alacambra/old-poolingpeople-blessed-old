(function () {
	'use strict';

	angular.module('poolingpeopleApp')

		.controller('LoginModalCtrl', ['$scope', '$rootScope', '$modalInstance', 'SessionService', 'message', 'callback', 'dismissCallback',
			function ($scope, $rootScope, $modalInstance, SessionService, message, callback, dismissCallback) {

				$scope.modal = {
					title: 'Login',
					message: message || ''
				};

				$scope.form = {
					login: {
						username: "",
						password: ""
					}
				};

				$scope.error = false;

				$scope.close = function () {
					$modalInstance.dismiss(dismissCallback);
				};

				$scope.save = function () {
					if (!$scope.form.login.$invalid) {
						$scope.error = false;
						SessionService.logIn($scope.modal.login.username, $scope.modal.login.password).then(function(response) {
							$modalInstance.close({loginData: $scope.modal.login, callback: callback});
						}, function(response) {
							$scope.error = true;
						})
					} else {
						for (var attr in $scope.form.login) {
							if ($scope.form.login.hasOwnProperty(attr) && $scope.form.login[attr].hasOwnProperty('$dirty')) {
								$scope.form.login[attr].$dirty = true;
							}
						}
					}
					
				};

			}]);
}());