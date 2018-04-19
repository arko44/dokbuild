var app = angular.module( 'dokkanModule', [] )

app.run(function ($rootScope) { $rootScope._ = _; });

app.controller('dokkanController', function( $scope, $interval, httpService, dokkanService ) {
	
	var UPDATE_WORLD_MS_INTERVAL = 15 * 1000
	var MAX_DISTANCE_WITHOUT_REFRESH_WORLD = 10
	var lastCenter = {'x':0, 'y':0}
	var lastMoveMap = {}
	
	$scope.raritySelected = {'n':false, 'r':false, 'sr':false, 'ssr':false, 'ur':false, 'lr':false}
	$scope.elementSelected = {'agl':false, 'teq':false, 'int':false, 'phy':false, 'str':false}
	
	$scope.toggleRarity = function($event, rarity) {
		$scope.raritySelected[rarity] = $scope.raritySelected[rarity] == false
		
		if ($scope.raritySelected[rarity]) {
			$event.currentTarget.className = "rarity calque"
		} else {
			$event.currentTarget.className = "rarity"
		}
		searchCards()
	}
	
	$scope.toggleElement = function($event, element) {
		$scope.elementSelected[element] = $scope.elementSelected[element] == false
		
		if ($scope.elementSelected[element]) {
			$event.currentTarget.className = "element calque"
		} else {
			$event.currentTarget.className = "element"
		}
		searchCards()
	}
	
	$scope.changeInputValue = function() {
		
	}

	//add empty spot to city and display it
	function searchCards() {
		httpService.getData("/cards/find/", {}).then(function(page) {
			$scope.cards = page.content
		})
	}

	// function call once, init data for app
	function _init() {
		
	}
	
	//TODO : fix dev frequency, externalize & integrate with gulp ?
	angular.element(document).ready(function () {
		
	});
})
