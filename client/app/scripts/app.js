(function () {
    'use strict';

    var dependencies = [
        'ui.bootstrap',
        'ui.router',
        'poolingpeopleAppDirective',
        'poolingpeopleAppFilter',
        'ngCookies',
        'ngSanitize'
    ];

    angular.module('poolingpeopleApp', dependencies)

        .value('siteTitle', 'poolingpeople')

        .config(['$stateProvider', '$urlRouterProvider',
            function ($stateProvider, $urlRouterProvider) {

                var homeState = {
                    url: '/home',
                    name: 'home',
                    viewName: 'home',
                    templateUrl: 'views/home.tpl.html',
                    controller: 'HomeCtrl',
                    navbar: true,
                    needAuth: false
                }, usersState = {
                    url: '/users',
                    name: 'users',
                    viewName: 'users',
                    templateUrl: 'views/users.tpl.html',
                    controller: 'UsersCtrl',
                    navbar: true,
                    needAuth: true
                }, tasksState = {
                    url: '/tasks',
                    name: 'tasks',
                    viewName: 'tasks',
                    templateUrl: 'views/tasks.tpl.html',
                    controller: 'TasksCtrl',
                    navbar: true,
                    needAuth: true,
                    substates: [{
                            url: '/alle',
                            name: 'alle',
                            viewName: 'alle',
                            templateUrl: 'views/tasks.tpl.html',
                            parent: 'tasks',
                            controller: 'TasksCtrl',
                            search: true,
                            navbar: false,
                            needAuth: true,
                            taskFilter: "alle"
                        }, {
                            url: '/mine',
                            name: 'mine',
                            viewName: 'mine',
                            templateUrl: 'views/tasks.tpl.html',
                            parent: 'tasks',
                            controller: 'TasksCtrl',
                            search: true,
                            navbar: false,
                            needAuth: true,
                            taskFilter: "mine"
                        }, {
                            url: '/others',
                            name: 'others',
                            viewName: 'others',
                            templateUrl: 'views/tasks.tpl.html',
                            parent: 'tasks',
                            controller: 'TasksCtrl',
                            search: true,
                            navbar: false,
                            needAuth: true,
                            taskFilter: "other"
                        }, {
                            url: '/observed',
                            name: 'observed',
                            viewName: 'observed',
                            templateUrl: 'views/tasks.tpl.html',
                            parent: 'tasks',
                            controller: 'TasksCtrl',
                            search: true,
                            navbar: false,
                            needAuth: true,
                            taskFilter: "observed"
                        }
                    ]
                };

                $urlRouterProvider.otherwise(homeState.url);

                $stateProvider.state('home', homeState);
                $stateProvider.state('users', usersState);
                $stateProvider.state('tasks', tasksState);
                $stateProvider.state('tasks.alle', tasksState.substates[0]);
                $stateProvider.state('tasks.mine', tasksState.substates[1]);
                $stateProvider.state('tasks.others', tasksState.substates[2]);
                $stateProvider.state('tasks.observed', tasksState.substates[3]);

            }])

        .run(['$rootScope', '$state', '$stateParams', '$cookieStore', 'SessionService',
            function ($rootScope, $state, $stateParams, $cookieStore, SessionService) {

                var saveLastState = function (newState) {
                    $cookieStore.put('lastState', newState.name);
                };

                var firstTime = true;

                var updateBreadcrum = function () {
                    var ancestors = $state.current.name;
                    var ancestorsArray = [];
                    while (ancestors !== "") {
                        var splittedAncestor = ancestors.split(".");
                        ancestorsArray.push({
                            name: splittedAncestor[splittedAncestor.length - 1],
                            route: splittedAncestor.join("/")
                        });
                        ancestors = splittedAncestor.slice(0, splittedAncestor.length - 1).join(".");
                    }
                    return ancestorsArray.reverse();
                };

                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
                $rootScope.breadcrum = updateBreadcrum();

                $rootScope.$on('$locationChangeSuccess', function () {
                    $rootScope.breadcrum = updateBreadcrum();
                });

                $rootScope.$on('$viewContentLoaded', function () {
                    $rootScope.breadcrum = updateBreadcrum();
                    if (firstTime) {
                        firstTime = false;
                        if (!_.isUndefined($cookieStore.get('lastState')) && $state.current.name === "home") {
                            $state.go($cookieStore.get('lastState'))
                        }
                    } else {
                        saveLastState($state.current);
                    }

                });

                $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {

                    if (toState.needAuth && !SessionService.loggedIn()) {
                        event.preventDefault();
                        $rootScope.$broadcast("requiredAuth", toState, toParams, fromState, fromParams);
                    }
                });

            }]);
}());