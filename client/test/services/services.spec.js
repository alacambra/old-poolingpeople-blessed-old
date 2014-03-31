
describe ("LoadStatusService", function() {

	beforeEach(module('poolingpeopleApp'))

	var LoadStatusService; 

	beforeEach(inject(function($injector) {
		LoadStatusService = $injector.get('LoadStatusService');
	}))

	it("testing injection", function() {
		expect(LoadStatusService).toBeDefined();
	});

	it("set state", function() {
		LoadStatusService.setStatus("task", LoadStatusService.RESOLVING);
		expect(LoadStatusService.getStatus("task")).toBe(LoadStatusService.RESOLVING);
	});

	it("checking status through methods", function() {
		expect(LoadStatusService.isLoading("task")).toBeFalsy();
		LoadStatusService.setStatus("task", LoadStatusService.RESOLVING);
		expect(LoadStatusService.isLoading("task")).toBeTruthy();
		LoadStatusService.setStatus("task", LoadStatusService.COMPLETED);
		expect(LoadStatusService.isCompleted("task")).toBeTruthy();
	});

	it("set substate", function() {
		LoadStatusService.setStatus("task.taskList", LoadStatusService.RESOLVING);
		expect(LoadStatusService.getStatus("task.taskList")).toBe(LoadStatusService.RESOLVING);
	});

	it("checking substatus through methods", function() {
		expect(LoadStatusService.isLoading("task.taskList")).toBeFalsy();
		LoadStatusService.setStatus("task.taskList", LoadStatusService.RESOLVING);
		expect(LoadStatusService.isLoading("task.taskList")).toBeTruthy();
		LoadStatusService.setStatus("task.taskList", LoadStatusService.COMPLETED);
		expect(LoadStatusService.isCompleted("task.taskList")).toBeTruthy();
	});



});
