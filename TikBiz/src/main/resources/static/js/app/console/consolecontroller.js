'use strict';

angular.module('consoleapp').controller('consolecontroller',
    ['consoleservice', '$scope', '$state', '$filter',  function(consoleservice, $scope, $state, $filter) {

        var self = this;
        self.user = {};
        self.tickets = [];
        self.login = login;
        self.goToLogin = goToLogin;
        self.register = register;
        self.goToRegister = goToRegister;
        self.logout = logout;
        self.prepareDashboard = prepareDashboard;
        self.getMessage = getMessage;
        self.getAllTickets = getAllTickets;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        
        self.validateTicketForm = false;
        
        self.today = $filter('date')(new Date(),'MM/dd/yyyy');
        
        function login() {
        	consoleservice.login(self.user)
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
        	consoleservice.register(self.user)
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
        }
        
        function prepareDashboard(){
        	self.user = consoleservice.getUser();
        	consoleservice.loadAllTickets();
        	self.tickets = getAllTickets();
        }
        
        function getMessage(){
        	self.successMessage = consoleservice.getMessage();
        	resetMessage();
        }
        
        function resetMessage(){
        	consoleservice.resetMessage();
        }
        
        function getAllTickets(){
            return consoleservice.getAllTickets();
        }
        
        //console welcome
        
        self.tab = 1;
        self.setTab = setTab;
        self.isSet = isSet;

        function setTab(newTab){
          self.tab = newTab;
        };

        function isSet(tabNum){
          return self.tab === tabNum;
        };
        
        function dateFilter(createdDate){
        	var today = $filter('date')(new Date(),'yyyyMMdd');
        	var createdDate = $filter('date')(new Date(createdDate),'yyyyMMdd');
        	if(today===createdDate){
        		return true;
        	}
        }
    }
]);