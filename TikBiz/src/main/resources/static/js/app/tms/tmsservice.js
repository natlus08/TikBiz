'use strict';

angular.module('tmsapp').factory('tmsservice',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                login: login,
                getUser: getUser,
                register: register,
                getMessage: getMessage,
                resetMessage: resetMessage,
                createTicket: createTicket,
                updateTicket: updateTicket,
                loadAllTickets: loadAllTickets,
                getAllTickets: getAllTickets,
                getTicket: getTicket
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
            
            function createTicket(ticket) {
                var deferred = $q.defer();
                $http.post(urls.API+'createticket', ticket)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateTicket(ticket) {
                var deferred = $q.defer();
                $http.put(urls.API+'updateticket', ticket)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
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
            
            function getTicket(id) {
                var deferred = $q.defer();
                $http.get(urls.API+'getticket/' + id)
                    .then(
                        function (response) {
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
        }
    ]);