
/* JavaScript content from js/controllers/editlist.js in folder common */
/**
 * 
 */

app.controller('EditListContorller',function($scope){
	
	$scope.list ={};
	$scope.list.title;
	$scope.list.username;
	$scope.list.items = {};
	
	$scope.item = {};
	$scope.item.name;
	$scope.item.quantity;
	$scope.item.done;
	
	var resourceRequest = new WLResourceRequest("/adapters/cloudantAdapter/shoppinglist",
			WLResourceRequest.GET);
	resourceRequest.setQueryParameter("username", "Itayzr");
	resourceRequest.setQueryParameter("title", "grocery");
	resourceRequest.send().then(
			loadListSuccess,
			loadListFailure
	);
	
	function loadListSuccess(result) {
		var resp = result.responseJSON; 
		if(resp.statusReason == "OK")
		{
			$scope.list.title = resp.title;
			$scope.list.username = resp.username;
			$scope.list.items = resp.items;
			$scope.$apply();
		}
		else if(resp.statusReason == "Object Not Found")
		{
			alert(resp.statusReason);
		}
	}
	
	function loadListFailure(result){
		alert("Fail");
	}
	
//	$scope.items = [{"name":"Apples","quantity":3,"isDone":true},
//	                {"name":"Milk","quantity":1,"isDone":false},
//	                {"name":"IceCream","quantity":2,"isDone":true},
//	                {"name":"Water","quantity":6,"isDone":false},
//	                ];


   $scope.addItem = function(){
   	  if ($scope.item.name === "") {
            return false;
        }

        $scope.list.items.push({
            name: $scope.item.name,
            quantity: $scope.item.quantity,
            done: false
        });

        this.todoName = '';
        this.todoEstimate = 1;
    };

    $scope.delItem = function () {
    	alert("del");
    	
    };

	
	$scope.uploadList = function () {
		var resourceRequest = new WLResourceRequest("/adapters/cloudantAdapter/shoppinglist",
				WLResourceRequest.PUT);
		resourceRequest.addHeader("Content-Type","application/json");
		var list = angular.toJson($scope.list);
		resourceRequest.sendFormParameters(list).then(
				uploadListSuccess,
				uploadListFailure
		);
	};
	
	function uploadListSuccess(result) {
		var resp = result.responseJSON; 
		
	}
	
	function uploadListFailure(result){
		alert("Fail");
	}
	
	
	
});