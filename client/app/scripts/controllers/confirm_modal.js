(function () {
	'use strict';

	angular.module('poolingpeopleApp')

		.controller('ConfirmModalCtrl', ['$scope', '$modalInstance', 'message', 'LoadStatusService',
			function ($scope, $modalInstance, message, LoadStatusService) {

				$scope.modal = {
					message: message
				};

				$scope.dismiss = function () {
					$modalInstance.dismiss('cancel');
				};

				$scope.confirm = function () {
					$modalInstance.close();
				};

			}]);
}());