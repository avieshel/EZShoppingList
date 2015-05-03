
/* JavaScript content from js/controllers/newlist.js in folder common */
/**
 * 
 */
app.controller('NewlistController',function($scope){
	
	$scope.listName;
	
	$scope.submit = function() {
		if(checkName()){
			//alert("Submit " + $scope.listName);
			location.hash = "editlist";
		}
		else
			alert("Try another name!");
	};
	
	function checkName(){
		if($scope.listName=="itay")
			return false;
		return true;	
	}
	
	
});