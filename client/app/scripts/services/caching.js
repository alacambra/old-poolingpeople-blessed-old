(function () {
    'use strict';

    angular.module('poolingpeopleApp')
        .service('CachingService', ['$cacheFactory', '$q', 'LoadStatusService', function ($cacheFactory, $q, LoadStatusService) {

                var APICache = $cacheFactory("APICache", {});

                return {
                    put: function (resource, data) {
                        LoadStatusService.setStatus(resource, LoadStatusService.CACHED);
                        APICache.put(resource, data);
                    },
                    getUser: function (id) {
                        var q = $q.defer();
                        q.resolve(APICache.get('users.' + id));
                        return q.promise;
                    },
                    getUsers: function () {
                        var q = $q.defer();
                        q.resolve(APICache.get('users'));
                        return q.promise;
                    },
                    getTask: function (id) {
                        var q = $q.defer();
                        q.resolve(APICache.get('tasks.' + id));
                        return q.promise;
                    },
                    getTasks: function (size, start) {
                        var q = $q.defer();
                        q.resolve(APICache.get('tasks'));
                        return q.promise;
                    },
                    getMyTasks: function (size, start) {
                        var q = $q.defer();
                        q.resolve(APICache.get('myTasks'));
                        return q.promise;
                    },
                    getOtherTasks: function (size, start) {
                        var q = $q.defer();
                        q.resolve(APICache.get('otherTasks'));
                        return q.promise;
                    },
                    getObservedTasks: function (size, start) {
                        var q = $q.defer();
                        q.resolve(APICache.get('observedTasks'));
                        return q.promise;
                    },
                    getEfforts: function (taskId) {
                        var q = $q.defer();
                        q.resolve(APICache.get('efforts.' + taskId));
                        return q.promise;
                    },
                    getLog: function (id) {
                        return [];
                    },
                    getChangeLog: function (id) {
                        return [];
                    },
                    getUsersFromTask: function (idTask) {
                        return [];
                    },
                    getTaskSubtasks: function (id) {
                        return [];
                    }

                };

            }]);

}());