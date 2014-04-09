(function () {
    'use strict';

    describe('ngLoading', function () {

        var $rootScope, $compile, $document, LoadStatusService,
            $body, $elem,
            compileDirective;

        beforeEach(module('poolingpeopleApp'));
        beforeEach(inject(function ($injector) {

            $rootScope = $injector.get('$rootScope');
            $compile = $injector.get('$compile');
            $document = $injector.get('$document');
            LoadStatusService = $injector.get('LoadStatusService');

            compileDirective = function (tpl) {

                $elem = angular.element(tpl);
                $body = angular.element($document[0].body);

                $body.append($elem);

                $compile($elem)($rootScope);
                $rootScope.$digest();
            };

        }));

        it('basic example', function () {

            compileDirective('<div ng-loading=""></div>');
            expect($body.find('div').is(":visible")).toBeFalsy();

        });

        it('resource', function () {

            compileDirective('<div ng-loading="test"></div>');

            expect($body.find('div').is(":visible")).toBeFalsy();

            $rootScope.$apply(function () {
                LoadStatusService.setStatus("test", LoadStatusService.RESOLVING);
            });

            expect($body.find('div').is(":visible")).toBeTruthy();

            $rootScope.$apply(function () {
                LoadStatusService.setStatus("test", LoadStatusService.COMPLETED);
            });

            expect($body.find('div').is(":visible")).toBeFalsy();

        });

        it('negative resource', function () {

            compileDirective('<div ng-loading="!test"></div>');

            expect($body.find('div').is(":visible")).toBeTruthy();

            $rootScope.$apply(function () {
                LoadStatusService.setStatus("test", LoadStatusService.RESOLVING);
            });

            expect($body.find('div').is(":visible")).toBeFalsy();

            $rootScope.$apply(function () {
                LoadStatusService.setStatus("test", LoadStatusService.COMPLETED);
            });

            expect($body.find('div').is(":visible")).toBeTruthy();

        });

        it('nested resource', function () {

            compileDirective('<div id="wrapper" ng-loading="tasks"><div id="taskList" ng-loading="tasks.taskList"></div></div>');

            expect($body.find('#wrapper').is(":visible")).toBeFalsy();
            expect($body.find('#taskList').is(":visible")).toBeFalsy();

            $rootScope.$apply(function () {
                LoadStatusService.setStatus("tasks", LoadStatusService.RESOLVING);
            });

            expect($body.find('#wrapper').is(":visible")).toBeTruthy();
            expect($body.find('#taskList').is(":visible")).toBeFalsy();
 
            $rootScope.$apply(function () {
                LoadStatusService.setStatus("tasks", LoadStatusService.COMPLETED);
            });
            
            expect($body.find('#wrapper').is(":visible")).toBeFalsy();
            expect($body.find('#taskList').is(":visible")).toBeFalsy();
            
            $rootScope.$apply(function () {
                LoadStatusService.setStatus("tasks.taskList", LoadStatusService.RESOLVING);
            });
            
            expect($body.find('#wrapper').is(":visible")).toBeFalsy(); 
            expect($body.find('#taskList').is(":visible")).toBeFalsy(); 
            
            $rootScope.$apply(function () {
                LoadStatusService.setStatus("tasks", LoadStatusService.RESOLVING);
            });
            
            expect($body.find('#wrapper').is(":visible")).toBeTruthy(); 
            expect($body.find('#taskList').is(":visible")).toBeTruthy(); 
            
            $rootScope.$apply(function () {
                LoadStatusService.setStatus("tasks.taskList", LoadStatusService.COMPLETED);
            });
            
            expect($body.find('#wrapper').is(":visible")).toBeFalsy(); 
            expect($body.find('#taskList').is(":visible")).toBeFalsy(); 


        });

    });

}());