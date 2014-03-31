(function () {
    'use strict';

    angular.module('poolingpeopleApp')

        .service('SessionService', function (Base64, $cookieStore, $http, $q, DataProvider) {
		 
		    var userData = {
		    	id: "",
		    	username: "",
		    	password: "",
		    	lastName: "",
		    	firstName: "",
		    	email: "",
		    	birthDate: 0
		    };

	        var setCredentials = function (username, password) {

	        	userData.username = username;
	        	userData.password = password;

	            var encoded = Base64.encode(username + ':' + password);
	            $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
	            $cookieStore.put('authdata', encoded);
	        };

	        var clearCredentials = function () {
	            $cookieStore.remove('authdata');
	            delete $http.defaults.headers.common.Authorization;
	        };

	        var loggedIn = function () {
	        	if ($cookieStore.get('authdata')) return true;
	        	else return false
	        }

	        var logIn = function (username, password, cookie) {

                var q = $q.defer(),
                	username = username, 
                	password = password;

	        	setCredentials(username, password);

	        	var authRequest = DataProvider.getAuthStatus().then(function (data) {
	        		setCredentials(username, password);
	        		userData = _.extend(userData, data[0]);
	        		q.resolve(data);
	        	}, function (data) {
	        		q.reject(data);
	        	});

	        	if (!cookie) clearCredentials();

                return q.promise;
	        }

	        var logOut = function () {
	        	clearCredentials();
	        }

	        var init = (function() {

				if (loggedIn()) {
					$http.defaults.headers.common.Authorization = 'Basic ' + $cookieStore.get('authdata');
					var authdata = Base64.decode($cookieStore.get('authdata')).split(":");
					logIn(authdata[0], authdata[1], true)
				}

	        })();


		    return {

		        loggedIn: loggedIn,

		        logIn: logIn,

		        logOut: logOut,

		        userData: function() {
		        	var userDataWithoutPassword = _.extend({}, userData);
		        	delete userDataWithoutPassword.password;
		        	return userDataWithoutPassword;
		        }

			}
		});

}());