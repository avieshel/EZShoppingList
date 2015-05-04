/**
 * 
 */

var app = angular.module('EasyShopApp',['ionic','ngRoute']);

app.config(['$routeProvider',
            function($routeProvider)
            {
				$routeProvider.when('/menu',
				{
					templateUrl: 'views/menu.html'
					
				}).when('/newlist',
				{
					templateUrl: 'views/newlist.html',
					controller: 'NewlistController'
				}).when('/editlist',
				{
					templateUrl: 'views/editlist.html',
					controller: 'EditListContorller'
				});
            }
]);
