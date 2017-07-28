'use strict';

angular.module('consoleapp').factory('consoleservice',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                login: login,
                getUser: getUser,
                register: register,
                getMessage: getMessage,
                resetMessage: resetMessage,
                loadAllTickets: loadAllTickets,
                getAllTickets: getAllTickets,
                getShifts: getShifts,
                loadShifts: loadShifts
            };

            return factory;

            function login(user) {
                var deferred = $q.defer();
                $http.post(urls.API+'login',user)
                    .then(
                        function (response) {
                            $localStorage.user = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getUser(){
            	return $localStorage.user;
            }
            
            function register(user) {
                var deferred = $q.defer();
                $http.post(urls.API+'register',user)
                    .then(
                        function (response) {
                        	$localStorage.message = "Registration Successful!"
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getMessage(){
            	return $localStorage.message;
            }
            
            function resetMessage(){
            	$localStorage.message = '';
            }
            
            function loadAllTickets() {
                var deferred = $q.defer();
                $http.get(urls.API+'getalltickets')
                    .then(
                        function (response) {
                            $localStorage.tickets = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllTickets(){
                return $localStorage.tickets;
            }
            
            function getShifts(date) {
                var deferred = $q.defer();
                $http.get(urls.API+'getshifts/'+date)
                    .then(
                        function (response) {
                        	$localStorage.shifts = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function loadShifts(){
                return $localStorage.shifts;
            }
        }
    ]);