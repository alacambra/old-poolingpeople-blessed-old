(function () {
    'use strict';

    angular.module('poolingpeopleApp')

        .controller('SearchCtrl', function ($scope, $rootScope) {
        
            $scope.search = "";
        
            $scope.clearSearch = function() {
                $scope.search = "";
            };
            
            $scope.notifySearch = function() {
                $rootScope.$broadcast("search", $scope.search);
            };
            
        });
}());
