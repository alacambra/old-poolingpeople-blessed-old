(function () {
    'use strict';

    angular.module('poolingpeopleApp')
        .service('ModelStampsService', function() {

            var poolingpeopleObject = stampit().methods({
                getUUID: function(){
                    return this.id;
                }
            }).state({
                id : null
            });

            var scheduledObject = stampit().methods({

                setStartDate: function(date) {
                    this.startDate = Date.UTC(date.getDay(), date.getMonth(), date.getYear());
                },

                getStartDate: function() {
                    return this.startDate ===  1254731900000 ? null : this.startDate;
                },

                getEndDate: function() {
                    return this.endDate ===  -99999999999999 ? null : this.endDate;
                },

                setEndDate: function(date) {
                    this.endDate = Date.UTC(date.getYear(), date.getMonth(), date.getDay());
                },

                getFormatedStartDate: function(){
                    var date = new Date(this.startDate),
                        day = date.getDate(),
                        month = date.getMonth() + 1,
                        year = date.getFullYear();
                    return ((day < 10 ? "0" + day : day) + "." + (month < 10 ? "0" + month : month) + "." + year);
                },

                getFormatedEndDate: function(){
                    var date = new Date(this.endDate),
                        day = date.getDate(),
                        month = date.getMonth() + 1,
                        year = date.getFullYear();
                    return this.endDate === -99999999999999 ? null : 
                            ((day < 10 ? "0" + day : day) + "." + (month < 10 ? "0" + month : month) + "." + year);
                },

                getShortTitle: function(){
                    if(this.title.length > 100){
                        return this.title.substring(0, 100) + "..."
                    }

                    return this.title
                },

                statusEncoder : function(code){
                    return code.toLowerCase();
                },

                getShortDescription: function(){
                    var size = 50;
                    if(this.description.length > size){
                        return this.description.substring(0, size) + "..."
                    }

                    return this.title
                },

                getAssignee: function(){
                    return this.assignee;
                },

                getDTO: function(omittedFields) {
                    return _.omit(this, ['id', 'project', 'assignee', 'subtasks'].concat(omittedFields || []))
                }

            }).state({
                title: null,
                description: null,
                status: null,
                priority: 'NORMAL',
                duration: 0,
                effort: 0,
                progress: 0,
                startDate : null,
                endDate : null,
                assignee: null
            });

            var task = stampit.compose(poolingpeopleObject, scheduledObject).state({

                project: null,
                assignee: null,
                subtasks: []

            }).methods({

                getPrettyStatus: function() {
                    var statusList = this.getStatusList();
                    return statusList[this.status];
                },

                getPrettyPriority: function() {
                    var priorityList = this.getPriorityList();
                    return priorityList[this.priority];
                }

            }).enclose(function () {
                var _efforts = [];

                this.getEfforts = function () {
                    return _efforts;
                };

                this.setEfforts = function (efforts) {
                    _efforts = efforts;
                };

                this.getStatusList = function() {
                    
                    return {
                        "TODO": "To do",
                        "NEW": "New",
                        "ASSIGNED": "Assigned",
                        "HOLD": "Hold",
                        "COMPLETED": "Completed",
                        "ARCHIVED": "Archived",
                        "REQUESTED": "Requested",
                        "OFFERED": "Offered"
                    }
                };

                this.getPriorityList = function() {
                    
                    return {
                        "LOW": "Low",
                        "NORMAL": "Normal",
                        "HIGH": "High"
                    }
                };

            });


            var user = stampit.compose(poolingpeopleObject).state({

                firstName: null,
                lastName: null,
                birthday: null,
                email: null

            }).methods({
                getPrettyName: function () {

                    var name = "";

                    if ( this.lastName != null ){
                        name = this.lastName + ", ";
                    }

                    if ( this.firstName != null ){
                        name += this.firstName;
                    }

                    return name;
                }
            });


            var comment = stampit.compose(poolingpeopleObject).state({

                id: null,
                date: null,
                comment: "",
                owner: null

            });

            return {
                
                task: task,
                project: null,
                user: user,
                comment: comment,
                effort: null

            }
        });
}());