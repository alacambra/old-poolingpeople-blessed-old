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
                    servicesAvailable: []
                };

                $scope.originServices = angular.copy($scope.modal.task.services);

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

                var loadServices = function () {
                    var taskServices = angular.copy($scope.modal.task.services);
                    $scope.modal.servicesAvailable = [""];
                    DataProvider.getServices().then(function (services) {
                        $scope.modal.servicesAvailable = [];
                        $scope.modal.task.services = [];
                        angular.forEach(services, function (service) {
                            if (_.find(taskServices, function (taskService) {
                                return _.isEqual(taskService, service);
                            })) {
                                $scope.addService(service);
                            } else {
                                $scope.modal.servicesAvailable.push(service);
                            }
                        });
                        $scope.refreshServices(taskServices);
                    }, function (response) {
                        $scope.error = 'Couldn\'t load services: ' + response;
                    });
                };

                var saveTask = function () {

                    var servicesToAdd = [], servicesToDelete = [];


                    angular.forEach($scope.originServices, function (originService) {
                        var match = _.find($scope.modal.task.services, function (service) {
                            return _.isEqual(_.omit(service, "$$hashKey"), originService)
                        });

                        if (!match)
                            servicesToDelete.push(_.omit(originService, "$$hashKey"));
                    });

                    angular.forEach($scope.modal.task.services, function (service) {
                        var match = _.find($scope.originServices, function (originService) {
                            return _.isEqual(_.omit(service, "$$hashKey"), originService)
                        });

                        if (!match)
                            servicesToAdd.push(_.omit(service, "$$hashKey"));
                    });

                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.RESOLVING);
                    if (!_.isNull($scope.modal.parentTask)) {
                        DataProvider.createSubtaskInTask($scope.modal.parentTask.id, $scope.modal.task).then(function (response) {
                            var newTask = _.extend($scope.modal.task, response);
                            DataProvider.assignTaskToUser(response.id, $scope.modal.task.assignee.id).then(function (response) {
                                DataProvider.commitServices(newTask.id, servicesToAdd, servicesToDelete).then(function (response) {
                                    $modalInstance.close(newTask);
                                }).finally(function (response) {
                                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                });
                            }, function (response) {
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
                                    DataProvider.commitServices(newTask.id, servicesToAdd, servicesToDelete).then(function (response) {
                                        $modalInstance.close(newTask);
                                    }).finally(function (response) {
                                        LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                    });
                                }, function (response) {
                                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                });
                            }, function (response) {
                                $scope.error = 'Couldn\'t save task: ' + response;
                                LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                            });
                        } else {
                            DataProvider.updateTask($scope.modal.task.id, $scope.modal.task).then(function (response) {
                                DataProvider.assignTaskToUser($scope.modal.task.id, $scope.modal.task.assignee.id).then(function (response) {
                                    DataProvider.commitServices($scope.modal.task.id, servicesToAdd, servicesToDelete).then(function (response) {
                                        $modalInstance.close($scope.modal.task);
                                    }).finally(function (response) {
                                        LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                    });
                                }, function (response) {
                                    LoadStatusService.setStatus("taskModal.save", LoadStatusService.COMPLETED);
                                });
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
                    loadServices();

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

                $scope.refreshServices = function (services) {
                    $scope.modal.servicesAvailable = _.difference($scope.modal.servicesAvailable, services || $scope.modal.task.services);
                };

                $scope.addService = function (service) {
                    $scope.modal.task.services.push(service || $scope.modal.service);
                    $scope.modal.servicesAvailable = _.without($scope.modal.servicesAvailable, service || $scope.modal.service);
                    $scope.modal.service = "";

                };

                $scope.deleteService = function (service) {
                    $scope.modal.task.services = _.without($scope.modal.task.services, service);
                    $scope.modal.servicesAvailable.push(service);
                };

            }]);
}());