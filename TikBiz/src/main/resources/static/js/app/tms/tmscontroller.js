'use strict';

angular.module('tmsapp').controller('tmscontroller',
    ['tmsservice', '$scope', '$state',  function(tmsservice, $scope, $state) {

        var self = this;
        self.user = {};
        self.ticket = {};
        self.tickets = [];
        self.login = login;
        self.goToLogin = goToLogin;
        self.register = register;
        self.goToRegister = goToRegister;
        self.logout = logout;
        self.prepareDashboard = prepareDashboard;
        self.getMessage = getMessage;
        self.submitTicketForm = submitTicketForm;
        self.editTicket = editTicket;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        
        self.onlyIntegers = /^\d+$/;
        
        self.validateTicketForm = false;
        
        self.viewby = '5';

        function login() {
        	tmsservice.login(self.user)
                 .then(
                     function (response){
                    	 $state.transitionTo('dashboard');
                     },
                     function(errResponse){
                         self.errorMessage='Invalid Credentials - '+errResponse.data.errorMessage;
                         self.successMessage='';
                     }
                 );
        }
        
        function register() {
        	tmsservice.register(self.user)
                 .then(
                     function (response){
                    	 $state.transitionTo('login');
                     },
                     function(errResponse){
                         self.errorMessage='Invalid Registration - '+errResponse.data.errorMessage;
                         self.successMessage='';
                     }
                 );
        }
        
        function goToLogin() {
        	$state.transitionTo('login');
        }

        function goToRegister() {
        	$state.transitionTo('register');
        }
        
        function logout(){
        	$state.transitionTo('login');
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.ticket={};
            $scope.ticketForm.$setPristine();
        }
        
        function prepareDashboard(){
        	self.user = tmsservice.getUser();
        	listTickets();
        	
        }
        
        function getMessage(){
        	self.successMessage = tmsservice.getMessage();
        	resetMessage();
        }
        
        function resetMessage(){
        	tmsservice.resetMessage();
        }
        
        function submitTicketForm() {
        	self.validateTicketForm = true;
        	if($scope.ticketForm.$valid){
        		self.validateTicketForm = false;
	            if (self.ticket.id === undefined || self.ticket.id === null) {
	                createTicket(self.ticket);
	            } else {
	                updateTicket(self.ticket, self.ticket.id);
	            }
			}
        }
        
        function createTicket(ticket) {
            tmsservice.createTicket(ticket)
                .then(
                    function (response) {
                        self.successMessage = 'Ticket created successfully';
                        self.errorMessage='';
                        self.ticket={};
                        $scope.ticketForm.$setPristine();
                        listTickets();
                    },
                    function (errResponse) {
                        self.errorMessage = 'Error while creating Ticket : ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateTicket(ticket){
            tmsservice.updateTicket(ticket)
                .then(
                    function (response){
                        self.successMessage='Ticket updated successfully';
                        self.errorMessage='';
                        $scope.ticketForm.$setPristine();
                        listTickets();
                    },
                    function(errResponse){
                        self.errorMessage='Error while updating Ticket - '+errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function editTicket(id) {
            self.successMessage='';
            self.errorMessage='';
            tmsservice.getTicket(id).then(
                function (ticket) {
                    self.ticket = ticket;
                },
                function (errResponse) {
                	
                }
            );
        }
        
        function listTickets(){
        	tmsservice.loadAllTickets().then(
                function (response) {
                    self.tickets = tmsservice.getAllTickets();
                    
                    //pagination parameters
                    self.totalItems = self.tickets.length;
                    self.currentPage = 1;
                    self.itemsPerPage =  self.viewby;
                    self.maxSize = 5; //Number of pager buttons to show
                },
                function (errResponse) {
                	
                }
            );
        }
        
        //pagination code - begins
        self.setPage = function (pageNo) {
        	 self.currentPage = pageNo;
        };

        self.setItemsPerPage = function(num) {
        	 self.itemsPerPage = num;
        	 self.currentPage = 1; //reset to first paghe
        }
        //pagination code - ends
        
        //sorting - starts
        self.sortType = 'id';
        self.sortReverse = false;
        self.searchWord = ''; 
        //sorting - ends 
    }

    ]);