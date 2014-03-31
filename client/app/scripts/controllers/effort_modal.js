(function () {
	'use strict';

	angular.module('poolingpeopleApp')

		.controller('EffortModalCtrl', ['$scope', '$modalInstance', '$modal', 'options', '$log', 'DataProvider', 'LoadStatusService',
			function ($scope, $modalInstance, $modal, options, $log, DataProvider, LoadStatusService) {
				
				$scope.modal = {
					title: options.title,

					task: options.task,

					newEffort: factory.effort({
						date: moment().valueOf()
					})
				};

				LoadStatusService.setStatus("effortModal.efforts." + $scope.modal.task.id, LoadStatusService.RESOLVING);

				DataProvider.getEfforts($scope.modal.task.id).then(function (efforts) {
					var effortsList = [];
					efforts.forEach(function (effort) {
						var _effort = factory.effort(effort);
						_effort.taskId = $scope.modal.task.id;
						_effort.id = effort.id;
						effortsList.push(_effort);
					});
					$scope.modal.task.efforts = effortsList;
				}).finally(function() {
					LoadStatusService.setStatus("effortModal.efforts." + $scope.modal.task.id, LoadStatusService.COMPLETED);
				});


				$scope.form = {
					datePickerOpened: false
				};

				$scope.editable = {
					datepicker: {
						date: false
					}
				};

				$scope.clearFields = function() {
					$scope.form.model.$setPristine();
					$scope.modal.newEffort = factory.effort({
						date: moment().valueOf()
					})
				}

				$scope.save = function () {
					if (!$scope.form.model.$invalid) {
						LoadStatusService.setStatus("effortModal.newEffort", LoadStatusService.RESOLVING);
						DataProvider.createEffort($scope.modal.task.id, $scope.modal.newEffort.getRequestObj()).then(function (response) {
							response.taskId = $scope.modal.task.id;
							$scope.modal.task.efforts.push(factory.effort(response));
							$scope.clearFields();
						}, function (response) {
							$log.error(response);
						}).finally(function() {
							LoadStatusService.setStatus("effortModal.newEffort", LoadStatusService.COMPLETED);
						});
					}
				};

				$scope.remove = function (effort) {
					var modalInstance = $modal.open({
						templateUrl: 'views/confirm_modal.tpl.html',
						controller: 'ConfirmModalCtrl',
						resolve: {
							message: function() {
								return "Soll die effort wirklich gelöscht werden?";
							}
						}
					});

					modalInstance.result.then(function () {

						LoadStatusService.setStatus("effortModal.effortList.effort." + effort.id, LoadStatusService.RESOLVING);

						DataProvider.deleteEffort($scope.modal.task.id, effort.id).then(function (response) {
							$scope.modal.task.removeEffort(effort);
						}, function (response) {
							$log.error(response);
						}).finally(function() {
							LoadStatusService.setStatus("effortModal.effortList.effort." + effort.id, LoadStatusService.COMPLETED);
						});
					});
				};

				$scope.close = function () {
					$modalInstance.dismiss('cancel');
				};

				$scope.openDatePicker = function ($event, datepicker) {
					$event.preventDefault();
					$event.stopPropagation();
					$scope.form[datepicker] = true;
				};

			}])

		.controller('EffortCtrl', ['$scope', '$log', 'DataProvider', 'LoadStatusService',
			function($scope, $log, DataProvider, LoadStatusService) {
				$scope.editableEffort = angular.copy($scope.effort);

				$scope.editable = {
					datepicker: {
						date: false
					}
				};

				$scope.updateEffort = function () {
					
					LoadStatusService.setStatus("effortModal.effortList.effort." + $scope.effort.id, LoadStatusService.RESOLVING);
					
					DataProvider.updateEffort($scope.editableEffort.taskId, $scope.editableEffort.id, $scope.editableEffort.getRequestObj()).then(function (response) {
						$scope.effort = angular.copy($scope.editableEffort);
					}, function (response) {
						$scope.editableEffort = angular.copy($scope.effort);
						$log.error(response);
					}).finally(function() {
						LoadStatusService.setStatus("effortModal.effortList.effort." + $scope.effort.id, LoadStatusService.COMPLETED);
					});
				};

				$scope.openDatePicker = function ($event) {
					$event.preventDefault();
					$event.stopPropagation();
					$scope.editable.datepicker.date = true;
				};
			}]);
}());