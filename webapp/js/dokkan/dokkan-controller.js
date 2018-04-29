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
	$scope.team = [{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId},{id:saibaimanId}]
	$scope.ownLead = 4
	$scope.friendLead = 6
	$scope.commonsLinks = {'01':{'label':'', 'links':[]}, '12':{'label':'', 'links':[]}, '34':{'label':'', 'links':[]}, '45':{'label':'', 'links':[]}}
	
	$scope.changeLead = function(position, ownLead) {
		if (ownLead) {
			$scope.ownLead = position
		} else {
			$scope.friendLead = position
		}
	}
	
	$scope.affectCardToTeam = function(id, position) {
		httpService.getData("/cards/get/", {'id': id}).then(function(card) {
			$scope.team[position] = card
			updateCommonsLinks(position)
		})
	}
	
	//
	$rootScope.$on('dropEvent', function(evt, dragObject, teamPosition) {
		var mod = dragObject.mod
		if (mod.startsWith('switch_')) {
			//mode switch
			var originPosition = parseInt(mod.split('_')[1])
			var targetId = $scope.team[teamPosition].id
			$scope.affectCardToTeam(targetId, originPosition)
		}
		$scope.affectCardToTeam(parseInt(dragObject.data), parseInt(teamPosition))
	});
	
	// search for commons links between two card
	function updateCommonsLinks(position) {
		var card = $scope.team[position]
		if ([1, 2, 4, 5].includes(position)) {
			//comparaison de $scope.team[position] && $scope.team[position - 1]
			var other = $scope.team[position - 1]
			var commonsLinks = getCommonsLinks(card, other)
			var object = {'label':'<table><tr>' + commonsLinks.map(l => '<td>' + l.name + " (" + l.description + ")</td>").join('</tr><tr>') + '</tr></table>', 'links':commonsLinks}
			$scope.commonsLinks['' + (position-1) + '' + position] = object
		}
		if ([0, 1, 3, 4].includes(position)) {
			//comparaison de $scope.team[position] && $scope.team[position + 1]
			var other = $scope.team[position + 1]
			var commonsLinks = getCommonsLinks(card, other)
			var object = {'label':'<table><tr>' + commonsLinks.map(l => '<td>' + l.name + " (" + l.description + ")</td>").join('</tr><tr>') + '</tr></table>', 'links':commonsLinks}
			$scope.commonsLinks['' + position + '' + (position+1) ] = object
		}
	}
	
	function getCommonsLinks(card1, card2) {
		var result = []
		if (card1.linkSkills != undefined && card2.linkSkills != undefined) {
			result = _.intersectionWith(card1.linkSkills, card2.linkSkills, _.isEqual);
		}
		return result
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
		
		var i=0
		$scope.affectCardToTeam(1009430, i++)
		$scope.affectCardToTeam(1009830, i++)
		$scope.affectCardToTeam(1011300, i++)
		$scope.affectCardToTeam(1011060, i++)
		$scope.affectCardToTeam(1009330, i++)
		$scope.affectCardToTeam(1011800, i++)
		$scope.affectCardToTeam(1009330, i++)
		
		updateCommonsLinks(1)
		updateCommonsLinks(4)
	}
	
	angular.element(document).ready(function () {
		_init()
	});
})
