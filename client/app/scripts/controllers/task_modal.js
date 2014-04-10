(function () {
    'use strict';

    angular.module('poolingpeopleApp')

        .controller('TaskModalCtrl', ['$scope', '$modalInstance', 'options', '$log', 'DataProvider', 'LoadStatusService', 'ModelsService', 'SessionService',
            function ($scope, $modalInstance, options, $log, DataProvider, LoadStatusService, ModelsService, SessionService) {

                $scope.modal = {
                    title: options.title,
                    task: options.task ? angular.copy(options.task) : ModelsService.getTask({assignee: SessionService.userData(), status: "NEW", services: []}),
                    parentTask: options.parentTask ? options.parentTask : null,
                    assignableUsers: [],
                    disabled: options.disabled,
                    askIfDefault: options.askIfDefault ? options.askIfDefault : {},
                    servicesAvailable: ["CSS/HTML Umsetzung", "Grafik Design", "Javascript Umsetzung"]
                };

                $scope.form = {
                    task: null,
                    startDatePickerOpened: false,
                    endDatePickerOpened: false
                };

                $scope.error = false;

                var loadUsers = function () {
                    var defaultUser = $scope.modal.task.assignee;
                    DataProvider.getUsers().then(function (users) {
                        $scope.modal.assignableUsers = users;
                        $scope.modal.task.assignee = defaultUser;
                    }, function (response) {
                        $scope.error = 'Couldn\'t load users: ' + response;
                    });
                };

                var saveTask = function () {
                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.RESOLVING);
                    if (!_.isNull($scope.modal.parentTask)) {
                        DataProvider.createSubtaskInTask($scope.modal.parentTask.id, $scope.modal.task).then(function (response) {
                            var newTask = _.extend($scope.modal.task, response);
                            DataProvider.assignTaskToUser(response.id, $scope.modal.task.assignee.id).then(function (response) {
                                $modalInstance.close(newTask);
                            }).finally(function (response) {
                                LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                            });
                        }, function (response) {
                            $scope.error = 'Couldn\'t save task: ' + response;
                            LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                        });
                    } else {
                        if (_.isNull($scope.modal.task.id)) {
                            DataProvider.createTask($scope.modal.task).then(function (response) {
                                var newTask = _.extend($scope.modal.task, response, {creator: SessionService.userData()});
                                DataProvider.assignTaskToUser(response.id, $scope.modal.task.assignee.id).then(function (response) {
                                    $modalInstance.close(newTask);
                                }).finally(function (response) {
                                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                });
                            }, function (response) {
                                $scope.error = 'Couldn\'t save task: ' + response;
                                LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                            });
                        } else {
                            DataProvider.updateTask($scope.modal.task.id, $scope.modal.task).then(function (response) {
                                $modalInstance.close($scope.modal.task);
                            }, function (response) {
                                $scope.error = 'Couldn\'t save task';
                            }).finally(function () {
                                LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                            });
                        }
                    }

                };

                var init = (function () {

                    loadUsers();

                }());

                $scope.save = function () {
                    if ($scope.form.task.$valid && _.keys($scope.modal.askIfDefault).length === 0) {
                        $scope.error = "";
                        saveTask();
                    } else {
                        for (var attr in $scope.form.task) {
                            if ($scope.form.task.hasOwnProperty(attr) && $scope.form.task[attr].hasOwnProperty('$dirty')) {
                                $scope.form.task[attr].$dirty = true;
                            }
                        }
                        if (_.keys($scope.modal.askIfDefault).length > 0) {
                            $scope.error = "Select fields to transfer to the new task";
                        }
                    }
                };

                $scope.setDefault = function (field, defaultValue) {
                    $scope.modal.task[field] = defaultValue ? $scope.modal.task[field] : $scope.modal.askIfDefault[field];
                    delete $scope.modal.askIfDefault[field];
                };

                $scope.close = function () {
                    $modalInstance.dismiss('cancel');
                };

                $scope.openDatePicker = function ($event, datepicker) {
                    $event.preventDefault();
                    $event.stopPropagation();

                    $scope.modal.datepicker = {};
                    $scope.modal.datepicker[datepicker] = true;
                };
                
                $scope.refreshServices = function() {
                    $scope.modal.servicesAvailable = _.difference($scope.modal.servicesAvailable, $scope.modal.task.services);
                };
                
                $scope.addService = function() {
                    $scope.modal.task.services.push($scope.modal.service);
                    $scope.modal.servicesAvailable = _.without($scope.modal.servicesAvailable, $scope.modal.service);
                    $scope.modal.service = "";
                    
                };
                
                $scope.deleteService = function (service) {
                    $scope.modal.task.services = _.without($scope.modal.task.services, service);
                    $scope.modal.servicesAvailable.push(service);
                };

            }]);
}());