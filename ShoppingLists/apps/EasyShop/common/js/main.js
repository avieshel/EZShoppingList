function wlCommonInit(){
	
	console.log("Loading Angular");
	angular.element(document).ready(function() {
		 angular.bootstrap(document, ['EasyShopApp']);
		 location.hash = "menu";
	 });
}
