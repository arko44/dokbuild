var app = angular.module( 'dokkanModule', [] )

app.run(function ($rootScope) { $rootScope._ = _; });

app.controller('dokkanController', function( $scope, $rootScope, $interval, httpService, dokkanService ) {
	
	//search cards
	$scope.cardsGroups = []
	$scope.raritySelected = {'N':false, 'R':false, 'SR':false, 'SSR':false, 'UR':false, 'LR':false}
	$scope.elementSelected = {'AGL':false, 'TEQ':false, 'INT':false, 'PHY':false, 'STR':false}
	$scope.classeSelected = {'SUPER':false, 'EXTREME':false}
	$scope.nameInput = ""
	
	var offset = 20
	
	var saibaimanId = 1000780
	$scope.team = [{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId}]
	$scope.commonsLinks = {'01':{'label':'', 'list':[]}, '12':{'label':'', 'list':[]}, '34':{'label':'', 'list':[]}, '45':{'label':'', 'list':[]}}
	
	$scope.affectCardToTeam = function(id, position) {
		httpService.getData("/cards/get/", {'id': id}).then(function(card) {
			$scope.team[position] = card
			console.log(card)
			updateCommonsLinks(position)
		})
	}
	
	$rootScope.$on('dropEvent', function(evt, cardId, teamPosition) {
		console.log("drop event : card " + cardId + " on position " + teamPosition)
		$scope.affectCardToTeam(cardId, teamPosition)
	});
	
	// search for commons links between two card
	function updateCommonsLinks(position) {
		if (position in [1, 2, 4, 5]) {
			//comparaison de $scope.team[position] && $scope.team[position - 1]
		} else if (position in [0, 1, 3, 4]) {
			//comparaison de $scope.team[position] && $scope.team[position + 1]
		}
	}
	
	$scope.decreaseOffset = function(nb) {
		offset = Math.max(20, offset - nb)
		searchCards()
	}
	
	$scope.increaseOffset = function(nb) {
		offset = Math.min(100, offset + nb)
		searchCards()
	}
	
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
	
	$scope.changeCardName = function($event) {
		$scope.nameInput = $event.currentTarget.value
		searchCards()
	}
	
	$scope.changeCategory = function($event) {
		$scope.category = $event.currentTarget.value
		searchCards()
	}

	$scope.changeLink = function($event) {
		$scope.link = $event.currentTarget.value
		searchCards()
	}
	
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

		params['category'] = $scope.category

		params['link'] = $scope.link
		
		params['column'] = 'cost'
		params['direction'] = 'DESC'
		params['size'] = offset
		
		httpService.getData("/cards/find/", params).then(function(page) {
			$scope.cardsGroups = []
			var length = page.content
			for (var i=0; i<Math.ceil(page.size/5); i++) {
				$scope.cardsGroups.push(page.content.slice(i * 5, 5 * (1 + i)))
			}
		})
	}
	
	function loadCategories() {
		httpService.getData("/categories/list/").then(function(categories) {
			$scope.categories = categories
		})
	}

	function loadLinks() {
		httpService.getData("/links/list/").then(function(links) {
			$scope.links = links
		})
	}

	function _init() {
		loadCategories()
		loadLinks()
		
		for (var i=0; i++; i<7) {
			$scope.affectCardToTeam(1000780, i)
		}
	}
	
	angular.element(document).ready(function () {
		_init()
	});
})
