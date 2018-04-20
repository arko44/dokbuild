var app = angular.module( 'dokkanModule', [] )

app.run(function ($rootScope) { $rootScope._ = _; });

app.controller('dokkanController', function( $scope, $interval, httpService, dokkanService ) {
	
	//search cards
	$scope.raritySelected = {'N':false, 'R':false, 'SR':false, 'SSR':false, 'UR':false, 'LR':false}
	$scope.elementSelected = {'AGL':false, 'TEQ':false, 'INT':false, 'PHY':false, 'STR':false}
	$scope.classeSelected = {'SUPER':false, 'EXTREME':false}
	$scope.nameInput = ""
		
	$scope.displayCard = function(cardId) {
		if (cardId != undefined && cardId > 0) {
			httpService.getData("/cards/get/", {'id': cardId}).then(function(card) {
				$scope.cardSelected = card
			})
		}
	}
	
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
	
	$scope.toggleClasse = function($event, classe) {
		$scope.classeSelected[classe] = $scope.classeSelected[classe] == false
		
		if ($scope.classeSelected[classe]) {
			$event.currentTarget.className = "classe calque"
		} else {
			$event.currentTarget.className = "classe"
		}
		searchCards()
	}
	
	$scope.changeInputValue = function($event) {
		$scope.nameInput = $event.currentTarget.value
		
		searchCards()
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
		
		var resultClasses = []
		Object.keys($scope.classeSelected).forEach(function(key, index) {
			if ($scope.classeSelected[key]) {
				resultClasses.push(key)
			}
		})
		params['classes'] = resultClasses
		
		params['name'] = $scope.nameInput
		
		httpService.getData("/cards/find/", params).then(function(page) {
			var length = page.content
			$scope.cardsGroups = []
			for (var i=0; i<Math.ceil(page.size/5); i++) {
				$scope.cardsGroups.push(page.content.slice(i*5, 5 * (1+i)))
			}
		})
	}

	// function call once, init data for app
	function _init() {
		
	}
	
	//TODO : fix dev frequency, externalize & integrate with gulp ?
	angular.element(document).ready(function () {
		
	});
})
