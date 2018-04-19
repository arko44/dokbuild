var app = angular.module( 'dokkanModule', [] )

app.run(function ($rootScope) { $rootScope._ = _; });

app.controller('dokkanController', function( $scope, $interval, httpService, dokkanService ) {
	
	$scope.raritySelected = {'N':false, 'R':false, 'SR':false, 'SSR':false, 'UR':false, 'LR':false}
	$scope.elementSelected = {'AGL':false, 'TEQ':false, 'INT':false, 'PHY':false, 'STR':false}
	
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
		var params = {}
		
		var resultRarity = []
		Object.keys($scope.raritySelected).forEach(function(key, index) {
			if ($scope.raritySelected[key]) {
				resultRarity.push(key)
			}
		})
		params['rarities'] = resultRarity
		
		var resultElement = []
		Object.keys($scope.elementSelected).forEach(function(key, index) {
			if ($scope.elementSelected[key]) {
				resultElement.push(key)
			}
		})
		params['elements'] = resultElement
		
		httpService.getData("/cards/find/", params).then(function(page) {
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
