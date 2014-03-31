(function () {
    'use strict';

    angular.module('poolingpeopleApp')
        .service('ModelsService', ['ModelStampsService', function(ModelStampsService) {

            var getTask = function(data) {
                data = data || {};
                var task = ModelStampsService.task(data);

                task.startDate = (task.startDate ===  1254731900000) ? null : task.startDate;
                task.endDate = (task.endDate ===  -99999999999999) ? null : task.endDate;

                task.subtasks = getTaskList(task.subtasks || []);

                task.assignee = getUser(data.assignee);

                return task;
            };

            var getTaskList = function(data) {

                var tasks = [];

                data.forEach(function(task){
                    tasks.push(getTask(task));
                });

                return tasks;
            };

            var getUser = function (data) {
                data = data || {};
                return ModelStampsService.user(data);
            };

            var getUserList = function (data) {

                var users = [];

                data.forEach(function(user){
                    users.push(getUser(user));
                });

                return users;
            };

            var getComment = function (data) {
                data = data || {};
                return ModelStampsService.comment(data);
            };


            return {
                getTask: getTask,
                getTaskList: getTaskList,
                getUser: getUser,
                getUserList: getUserList,
                getComment: getComment
            };
        }]);
}());



