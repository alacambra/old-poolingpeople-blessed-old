
describe ("hoursToMinutes", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter; 

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('hoursToMinutes');
	}))

	it("0 hours", function() {
		var hours = 0;
		expect($filter(hours)).toBe(0);
	});

	it("2 hours", function() {
		var hours = 2;
		expect($filter(hours)).toBe(120);
	}); 

	it("decimal hours", function() {
		var hours = 2.25;
		expect($filter(hours)).toBe(135);
	});

	it("hours as string", function() {
		var hours = "2.25";
		expect($filter(hours)).toBe(135);
	});

	it("empty string", function() {
		var hours = "";
		expect($filter(hours)).toBe(0);
	});

	it("random string", function() {
		var hours = "random string";
		expect($filter(hours)).toBe(0);
	});

});

describe ("minutesToHours", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter; 

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('minutesToHours');
	}))

	it("0 minutes", function() {
		var minutes = 0;
		expect($filter(minutes)).toBe(0);
	});

	it("2 minutes", function() {
		var minutes = 120;
		expect($filter(minutes)).toBe(2);
	}); 

	it("decimal minutes", function() {
		var minutes = 135;
		expect($filter(minutes)).toBe(2.25);
	});

	it("minutes as string", function() {
		var minutes = "135";
		expect($filter(minutes)).toBe(2.25);
	});

	it("empty string", function() {
		var minutes = "";
		expect($filter(minutes)).toBe(0);
	});

	it("random string", function() {
		var minutes = "random string";
		expect($filter(minutes)).toBe(0);
	});

});

describe ("dateToNumber", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter,
		noGMTDate = function(d) {
			var timeZone = d.getTimezoneOffset();
			return new Date(d.valueOf() - timeZone * 60000)
		} 

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('dateToNumber');
	}))

	it("timestamp 0", function() {
		var date = noGMTDate(new Date(1970, 0, 1, 0, 0, 0));
		expect($filter(date)).toBe(0);
	});

	it("timestamp 0 given string", function() {
		var date = noGMTDate(new Date("1/1/1970 00:00:00"));
		expect($filter(date)).toBe(0);
	});

	it("random timestamp", function() {
		var date = noGMTDate(new Date(2013, 5, 10, 1, 40, 31));
		expect($filter(date)).toBe(1370828431000);
	});

	it("random timestamp given string", function() {
		var date = noGMTDate(new Date("6/10/2013 01:40:31"));
		expect($filter(date)).toBe(1370828431000);
	});

	it("random string", function() {
		var date = "random string";
		expect($filter(date)).toBe(0);
	});

});

describe ("numberToDate", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter,
		noGMTDate = function(d) {
			var timeZone = d.getTimezoneOffset();
			return new Date(d.valueOf() - timeZone * 60000)
		} 

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('numberToDate');
	}))

	it("timestamp 0", function() {
		var timestamp = 0;
		expect($filter(timestamp)).toEqual(noGMTDate(new Date(1970, 0, 1, 0, 0, 0)));
	});

	it("timestamp 0 given string", function() {
		var timestamp = 0;
		expect($filter(timestamp)).toEqual(noGMTDate(new Date("1/1/1970 00:00:00")));
	});

	it("random timestamp", function() {
		var timestamp = 1370828431000;
		expect($filter(timestamp)).toEqual(noGMTDate(new Date(2013, 5, 10, 1, 40, 31)));
	});

	it("random timestamp given string", function() {
		var timestamp = 1370828431000;
		expect($filter(timestamp)).toEqual(noGMTDate(new Date("6/10/2013 01:40:31")));
	});

});

describe ("range", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter;

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('range');
	}))

	it("0 items", function() {
		var range = 0;
		expect($filter([], range)).toEqual([]);
	});

	it("random items", function() {
		var range = 5;
		expect($filter([], range)).toEqual([0, 1, 2, 3, 4]);
	});

	it("items as string", function() {
		var range = '5';
		expect($filter([], range)).toEqual([0, 1, 2, 3, 4]);
	});

	it("negative items", function() {
		var range = -5;
		expect($filter([], range)).toEqual([]);
	});

	it("incorrect input", function() {
		var range = "incorrect input";
		expect($filter([], range)).toEqual([]);
	});

});

describe ("startFrom", function() {

	beforeEach(module('poolingpeopleAppFilter'))

	var $filter;

	beforeEach(inject(function($injector) {
		$filter = $injector.get('$filter')('startFrom');
	}))

	it("0 items", function() {
		var startFrom = 0,
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual(testArray);
	});

	it("random items", function() {
		var startFrom = 5,
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual([5]);
	});

	it("items as string", function() {
		var startFrom = '5',
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual([5]);
	});

	it("index greater as items", function() {
		var startFrom = 50,
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual([]);
	});

	it("negative items", function() {
		var startFrom = -5,
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual(testArray);
	});

	it("incorrect input", function() {
		var startFrom = "incorrect input",
			testArray = [0, 1, 2, 3, 4, 5];
		expect($filter(testArray, startFrom)).toEqual(testArray);
	});


});