var tmsapp = angular.module('tmsapp',['ui.router','ngStorage','ui.bootstrap']);

tmsapp.constant('urls', {
    BASE: 'http://localhost:8080/tikbiz',
    API : 'http://localhost:8080/tikbiz/tmsapi/'
});

tmsapp.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('login', {
                url: '/',
                templateUrl: 'tms/partial/tmslogin',
                controller:'tmscontroller',
                controllerAs:'tmscontroller'
            }).state('dashboard', {
                url : '/dashboard',
                templateUrl : 'tms/partial/tmsdashboard',
                controller:'tmscontroller',
                controllerAs:'tmscontroller'
            }).state('register', {
                url : '/register',
                templateUrl : 'tms/partial/tmsregister',
                controller:'tmscontroller',
                controllerAs:'tmscontroller'
            });
        $urlRouterProvider.otherwise('/');
    }]);

