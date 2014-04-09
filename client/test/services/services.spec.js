(function () {
    'use strict';

    describe("LoadStatusService", function () {

        beforeEach(module('poolingpeopleApp'));

        var LoadStatusService;

        beforeEach(inject(function ($injector) {
            LoadStatusService = $injector.get('LoadStatusService');
        }));

        it("testing injection", function () {
            expect(LoadStatusService).toBeDefined();
        });

        it("set state", function () {
            LoadStatusService.setStatus("task", LoadStatusService.RESOLVING);
            expect(LoadStatusService.getStatus("task")).toBe(LoadStatusService.RESOLVING);
        });

        it("checking status through methods", function () {
            expect(LoadStatusService.isCompleted("task")).toBeFalsy();
            expect(LoadStatusService.isCached("task")).toBeFalsy();
            expect(LoadStatusService.isRequested("task")).toBeFalsy();
            expect(LoadStatusService.isLoading("task")).toBeFalsy();
            LoadStatusService.setStatus("task", LoadStatusService.RESOLVING);
            expect(LoadStatusService.isCompleted("task")).toBeFalsy();
            expect(LoadStatusService.isCached("task")).toBeFalsy();
            expect(LoadStatusService.isRequested("task")).toBeTruthy();
            expect(LoadStatusService.isLoading("task")).toBeTruthy();
            LoadStatusService.setStatus("task", LoadStatusService.COMPLETED);
            expect(LoadStatusService.isCompleted("task")).toBeTruthy();
            expect(LoadStatusService.isRequested("task")).toBeTruthy();
            expect(LoadStatusService.isLoading("task")).toBeFalsy();
        });

        it("set substate", function () {
            LoadStatusService.setStatus("task.taskList", LoadStatusService.RESOLVING);
            expect(LoadStatusService.getStatus("task.taskList")).toBe(LoadStatusService.RESOLVING);
        });

        it("checking substatus through methods", function () {
            expect(LoadStatusService.isRequested("task.taskList")).toBeFalsy();
            expect(LoadStatusService.isLoading("task.taskList")).toBeFalsy();
            LoadStatusService.setStatus("task.taskList", LoadStatusService.RESOLVING);
            expect(LoadStatusService.isLoading("task.taskList")).toBeTruthy();
            LoadStatusService.setStatus("task.taskList", LoadStatusService.COMPLETED);
            expect(LoadStatusService.isCompleted("task.taskList")).toBeTruthy();
            expect(LoadStatusService.isRequested("task.taskList")).toBeTruthy();
        });

        it("ihneritance resources status", function () {

            LoadStatusService.setStatus("tasks.taskList", LoadStatusService.COMPLETED);
            LoadStatusService.setStatus("users.userList", LoadStatusService.COMPLETED);
            LoadStatusService.setStatus("tasks.taskList.1", LoadStatusService.COMPLETED);
            LoadStatusService.setStatus("tasks.taskList.2", LoadStatusService.COMPLETED);
            LoadStatusService.setStatus("tasks.taskList.3.subtasks", LoadStatusService.COMPLETED);

            LoadStatusService.setStatus("tasks.taskList.1.subtasks", LoadStatusService.RESOLVING);
            expect(LoadStatusService.getStatus("tasks.taskList.1")).toBe(LoadStatusService.CHILD_RESOLVING);
            expect(LoadStatusService.getStatus("tasks.taskList")).toBe(LoadStatusService.CHILD_RESOLVING);

            LoadStatusService.setStatus("tasks.taskList.1.subtasks", LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks.taskList.1")).toBe(LoadStatusService.COMPLETED);


            LoadStatusService.setStatus("tasks.taskList.1.subtasks", LoadStatusService.RESOLVING);
            LoadStatusService.setStatus("tasks.taskList.2.subtasks", LoadStatusService.RESOLVING);
            expect(LoadStatusService.getStatus("tasks.taskList.1")).toBe(LoadStatusService.CHILD_RESOLVING);
            expect(LoadStatusService.getStatus("tasks.taskList")).toBe(LoadStatusService.CHILD_RESOLVING);
            expect(LoadStatusService.getStatus("tasks")).toBe(LoadStatusService.CHILD_RESOLVING);

            LoadStatusService.setStatus("tasks.taskList.1.subtasks", LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks.taskList.2")).toBe(LoadStatusService.CHILD_RESOLVING);
            expect(LoadStatusService.getStatus("tasks.taskList")).toBe(LoadStatusService.CHILD_RESOLVING);
            expect(LoadStatusService.getStatus("tasks")).toBe(LoadStatusService.CHILD_RESOLVING);

            LoadStatusService.setStatus("tasks.taskList.2.subtasks", LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks.taskList.1")).toBe(LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks.taskList.2")).toBe(LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks.taskList")).toBe(LoadStatusService.COMPLETED);
            expect(LoadStatusService.getStatus("tasks")).toBe(LoadStatusService.COMPLETED);

        });



    });
}());