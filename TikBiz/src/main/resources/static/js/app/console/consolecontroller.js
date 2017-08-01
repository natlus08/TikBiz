'use strict';

angular.module('consoleapp').controller('consolecontroller',
    ['consoleservice', '$scope', '$state', '$filter',  function(consoleservice, $scope, $state, $filter) {

        var self = this;
        self.user = {};
        self.tickets = [];
        self.shifts = [];
        self.login = login;
        self.goToLogin = goToLogin;
        self.register = register;
        self.goToRegister = goToRegister;
        self.logout = logout;
        self.prepareDashboard = prepareDashboard;
        self.getMessage = getMessage;
        self.getAllTickets = getAllTickets;
        self.reset = reset;
        self.getShifts = getShifts;
        self.nextWeek = nextWeek;
        self.previousWeek = previousWeek;
        
        self.successMessage = '';
        self.errorMessage = '';
        
        self.validateTicketForm = false;
        
        self.nodata = false;
        
        self.today = $filter('date')(new Date(),'MM/dd/yyyy');
        
        var activeWeek;
        
        function getMonday(date) {
    	  var day = date.getDay();
    	  var diff = date.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
    	  activeWeek = new Date(date.setDate(diff))
    	  return activeWeek;
        }
        
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
        	
        	consoleservice.loadAllTickets()
            .then(
                function (response){
                	self.tickets = getAllTickets();
                },
                function(errResponse){
                	
                }
            );
        	
        	consoleservice.getShifts($filter('date')(getMonday(new Date()),'yyyy-MM-dd'))
            .then(
                function (response){
                	self.shifts = getShifts();
                },
                function(errResponse){
                	
                }
            );
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
        
        function getShifts(){
            return consoleservice.loadShifts();
        }
        
        function nextWeek(){
        	activeWeek = new Date(activeWeek.getTime()+(7*24*60*60*1000))
        	consoleservice.getShifts($filter('date')(getMonday(activeWeek),'yyyy-MM-dd'))
            .then(
                function (response){
                	self.shifts = getShifts();
                	emptyCheck();
                },
                function(errResponse){
                	
                }
            );
        }
        
        function previousWeek(){
        	activeWeek = new Date(activeWeek.getTime()-(7*24*60*60*1000))
        	consoleservice.getShifts($filter('date')(getMonday(activeWeek),'yyyy-MM-dd'))
            .then(
                function (response){
                	self.shifts = getShifts();
                	emptyCheck();
                },
                function(errResponse){
                	
                }
            );
        }
        
        function emptyCheck(){
        	angular.forEach(self.shifts, function(shiftDetail, name){
        		if(angular.equals(shiftDetail, {})){
        			self.nodata = true;
        		}else{
        			self.nodata = false;
        		}
        	});
        }
    }
]);