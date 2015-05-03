
/* JavaScript content from js/controllers/editlist.js in folder common */
/**
 * 
 */

app.controller('EditListContorller',function($scope){
	
	$scope.item = {};
	$scope.item.name;
	$scope.item.quantity;
	$scope.item.isDone;
	
	$scope.items = [{"name":"Apples","quantity":3,"isDone":true},
	                {"name":"Milk","quantity":1,"isDone":false},
	                {"name":"IceCream","quantity":2,"isDone":true},
	                {"name":"Water","quantity":6,"isDone":false},
	                ];


   $scope.addItem = function(){
   	  if ($scope.item.name === "") {
            return false;
        }

        $scope.items.push({
            name: $scope.item.name,
            quantity: $scope.item.quantity,
            isDone: false
        });

        this.todoName = '';
        this.todoEstimate = 1;
    };

    $scope.delItem = function () {
    	alert("del");
    	
    }

	
	
	
});