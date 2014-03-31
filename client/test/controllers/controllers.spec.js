describe ("HomeCtrl", function() {

    beforeEach(module("poolingpeopleApp"));

    var $scope, $rootScope, controllerLoader, DataProvider;

    beforeEach(inject(function($injector) {

        $rootScope = $injector.get('$rootScope');
        $controller = $injector.get('$controller');
        DataProvider = $injector.get('DataProvider');
        $scope = $rootScope.$new();

        controllerLoader = function() {
            return $controller('HomeCtrl', {
                '$scope': $scope
            });
        };
    }));

    it ("testing injection", function() {
        var controller = controllerLoader();
        expect($scope).toBeDefined();
    });

    it ("site title", function() {
        var controller = controllerLoader();
        expect($scope.title).toContain("poolingpeople");
    });

});
