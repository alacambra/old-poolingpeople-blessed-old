(function () {
    'use strict';

    angular.module('poolingpeopleApp')
        .service('DataProvider', ['$q', 'API', 'ModelsService', 'LoadStatusService', 'CachingService', function ($q, API, ModelsService, LoadStatusService, CachingService) {
                var DataSources = {API: API, Cache: CachingService},
                getDataSource = function (resource) {
                    return _.isUndefined(resource) ? DataSources.API : LoadStatusService.isCached(resource) ? DataSources.Cache : DataSources.API;
                };

                return {
                    /* SESSION */

                    getAuthStatus: function () {
                        return getDataSource().getAuthStatus();
                    },
                    /* LOG */

                    getLog: function (id) {
                        var that = this,
                            q = $q.all([
                                that.getComments(id),
                                that.getChangeLog(id)
                            ]).then(function (data) {
                            var aggregatedData = [];
                            angular.forEach(data, function (logs) {
                                angular.forEach(logs, function (log) {
                                    aggregatedData = aggregatedData.concat(log);
                                });
                            });
                            return aggregatedData;
                        });
                        return q;
                    },
                    getChangeLog: function (id) {
                        return getDataSource().getChangeLog(id);
                    },
                    getComments: function (id) {
                        return getDataSource().getComments(id);
                    },
                    createComment: function (id, data) {
                        return getDataSource().createComment(id, data);
                    },
                    /* SERVICES */

                    getServices: function () {
                        return getDataSource().getServices();
                    },
                    addService: function (taskId, serviceId) {
                        return getDataSource().addService(taskId, serviceId);
                    },
                    removeService: function (taskId, serviceId) {
                        return getDataSource().removeService(taskId, serviceId);
                    },
                    addServices: function (taskId, services) {
                        var that = this,
                            requests = [];
                        angular.forEach(services, function (service) {
                            requests.push(that.addService(taskId, service.id));
                        });
                        var that = this,
                            q = $q.all(requests);
                        return q;
                    },
                    removeServices: function (taskId, services) {
                        var that = this,
                            requests = [];
                        angular.forEach(services, function (service) {
                            requests.push(that.removeService(taskId, service.id));
                        });
                        var that = this,
                            q = $q.all(requests);
                        return q;
                    },
                    commitServices: function (taskId, servicesToAdd, servicesToDelete) {
                        var that = this,
                            requests = [];
                        if (servicesToAdd.length > 0) {
                            requests.push(this.addServices(taskId, servicesToAdd));
                        }
                        if (servicesToDelete.length > 0) {
                            requests.push(this.removeServices(taskId, servicesToDelete));
                        }
                        var that = this,
                            q = $q.all(requests);
                        return q;
                    },
                    /* User */

                    /* CRUD */

                    getUser: function (id) {
                        return getDataSource('user.' + id).getUser(id).then(function (data) {
                            return ModelsService.getUser(data);
                        });
                    },
                    getUsers: function () {
                        return getDataSource('users').getUsers().then(function (data) {
                            return ModelsService.getUserList(data);
                        });
                    },
                    createUser: function (data) {
                        return getDataSource().createUser(data);
                    },
                    updateUser: function (id, data) {
                        return getDataSource().updateUser(id, data);
                    },
                    deleteUser: function (id) {
                        return getDataSource().deleteUser(id);
                    },
                    /* END CRUD */

                    /* Task */


                    /* CRUD */

                    getTask: function (id) {
                        return getDataSource().getTask(id).then(function (data) {
                            return ModelsService.getTask(data);
                        });
                    },
                    getTasks: function (size, start) {
                        return getDataSource('tasks').getTasks(size, start).then(function (data) {
                            return ModelsService.getTaskList(data);
                        });
                    },
                    createTask: function (data) {
                        return getDataSource().createTask(data.getDTO());
                    },
                    updateTask: function (id, data) {
                        var that = this,
                            q = $q.all([
                                getDataSource().updateTask(id, data.getDTO()),
                                that.assignTaskToUser(id, data.assignee.id)
                            ]).then(function (data) {
                            return data;
                        }, function (data) {
                            return data;
                        });
                        return q;
                    },
                    deleteTask: function (id) {
                        return getDataSource().deleteTask(id);
                    },
                    /* END CRUD */

                    assignTaskToUser: function (idTask, idUser) {
                        return getDataSource().assignTaskToUser(idTask, idUser);
                    },
                    getUsersFromTask: function (idTask) {
                        return getDataSource().getUsersFromTask(idTask);
                    },
                    deleteUserFromTask: function (idTask, idUser) {
                        return getDataSource().deleteUserFromTask(idTask, idUser);
                    },
                    getTaskSubtasks: function (id) {
                        return getDataSource().getTaskSubtasks(id);
                    },
                    createSubtaskInTask: function (idParent, data) {
                        return getDataSource().createSubtaskInTask(idParent, data.getDTO());
                    },
                    addSubtaskToTask: function (idParent) {
                        return getDataSource().addSubtaskToTask(idParent);
                    },
                    moveSubtaskFromTaskToTask: function (idSource, idParent) {
                        return getDataSource().moveSubtaskFromTaskToTask(idSource, idParent);
                    },
                    /* Effort */

                    getEfforts: function (taskId) {
                        return getDataSource().getEfforts(taskId);
                    },
                    createEffort: function (taskId, data) {
                        return getDataSource().createEffort(taskId, data);
                    },
                    updateEffort: function (taskId, effortId, data) {
                        return getDataSource().updateEffort(taskId, effortId, data);
                    },
                    deleteEffort: function (taskId, effortId) {
                        return getDataSource().deleteEffort(taskId, effortId);
                    }
                };
            }]);

}());