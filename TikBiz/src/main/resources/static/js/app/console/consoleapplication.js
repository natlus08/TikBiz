var consoleapp = angular.module('consoleapp',['ui.router','ngStorage']);

consoleapp.constant('urls', {
    BASE: 'http://localhost:8080/tikbiz',
    API : 'http://localhost:8080/tikbiz/consoleapi/'
});

consoleapp.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('login', {
                url: '/',
                templateUrl: 'console/partial/consolelogin',
                controller:'consolecontroller',
                controllerAs:'consolecontroller'
            }).state('dashboard', {
                url : '/dashboard',
                templateUrl : 'console/partial/consoledashboard',
                controller:'consolecontroller',
                controllerAs:'consolecontroller'
            }).state('register', {
                url : '/register',
                templateUrl : 'console/partial/consoleregister',
                controller:'consolecontroller',
                controllerAs:'consolecontroller'
            });
        $urlRouterProvider.otherwise('/');
    }]);

consoleapp.filter('datefilter', ['$filter',  function($filter){
	return function(items)
	{
		var _today = $filter('date')(new Date(), 'dd/MM/yyyy');
	    var filtered = [];
	    for (var i = 0; i < items.length; i++) {
	      var item = items[i];
	      var _date = $filter('date')(new Date(item.createdDate), 'dd/MM/yyyy');
	      if (_today===_date) {
	        filtered.push(item);
	      }
	    }
	    return filtered;
	};
}]);

consoleapp.filter('datefilterwithstatus', ['$filter',  function($filter){
	return function(items,status)
	{
		var _today = $filter('date')(new Date(), 'dd/MM/yyyy');
	    var filtered = [];
	    for (var i = 0; i < items.length; i++) {
	      var item = items[i];
	      var _date = $filter('date')(new Date(item.createdDate), 'dd/MM/yyyy');
	      if ((_today===_date) && (status===item.status)) {
	        filtered.push(item);
	      }
	    }
	    return filtered;
	};
}]);

consoleapp.filter('shiftfilter', ['$filter',  function($filter){
	return function(items,shift)
	{
	    var filtered = [];
	    if(shift==='noon'){
	    	start = 14;
	    	end = 21;
	    }else if(shift==='morning'){
	    	start = 06;
	    	end = 13;
	    }
	    for (var i = 0; i < items.length; i++) {
	      var item = items[i];
	      var _hour = $filter('date')(new Date(item.createdDate), 'HH');
	      if(shift==='night'){
	    	  if(_hour>=23 || (_hour>=00 && _hour<=05)){
	    		  filtered.push(item);
	    	  }
	      }else if ((_hour >= start) && (_hour <= end)) {
	    	  filtered.push(item);
	      }
	    }
	    return filtered;
	};
}]);

consoleapp.filter('shiftfilterwithstatus', ['$filter',  function($filter){
	return function(items,shift,status)
	{
	    var filtered = [];
	    if(shift==='noon'){
	    	start = 14;
	    	end = 21;
	    }else if(shift==='morning'){
	    	start = 06;
	    	end = 13;
	    }
	    for (var i = 0; i < items.length; i++) {
	      var item = items[i];
	      var _hour = $filter('date')(new Date(item.createdDate), 'HH');
	      if(shift==='night'){
	    	  if((_hour>=23 || (_hour>=00 && _hour<=05)) && (status===item.status)){
	    		  filtered.push(item);
	    	  } 
	      }else if((_hour >= start) && (_hour <= end) && (status===item.status)) {
	        filtered.push(item);
	      }
	    }
	    return filtered;
	};
}]);

