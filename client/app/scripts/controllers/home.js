(function () {
    'use strict';

    angular.module('poolingpeopleApp')

        .controller('HomeCtrl', function ($scope, siteTitle) {
            $scope.title = siteTitle;
        });
}());
