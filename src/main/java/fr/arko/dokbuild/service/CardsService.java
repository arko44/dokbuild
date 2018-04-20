package fr.arko.dokbuild.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.dao.PassiveSkillsDao;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Classe;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;

@Transactional(readOnly = true)
@Service
public class CardsService {
	
	@Autowired
	private CardsDao dao;

	@Autowired
	private PassiveSkillsDao passiveSkillsDao;
	
	public Page<Cards> find(String name, List<Rarity> rarities, List<Element> elements, List<Classe> classes) {
		PageRequest pageRequest = new PageRequest(0, 20, Direction.DESC, "cost");
		
		List<Integer> intRarities = (rarities.isEmpty() ? Arrays.asList(Rarity.values()) : rarities).stream().map(x -> x.value()).collect(Collectors.toList());
		List<Integer> intElements = (elements.isEmpty() ? Arrays.asList(Element.values()) : elements).stream().map(x -> x.value()).collect(Collectors.toList());
		
		List<Integer> searchIntElements = new ArrayList<Integer>();
		if (classes.isEmpty() || classes.contains(Classe.SUPER)) {
			searchIntElements.addAll(intElements.stream().map(x -> x + Classe.SUPER.value()).collect(Collectors.toList()));
		}
		if (classes.isEmpty() || classes.contains(Classe.EXTREME)) {
			searchIntElements.addAll(intElements.stream().map(x -> x + Classe.EXTREME.value()).collect(Collectors.toList()));
		}
		
		Page<Cards> findByRarityIn = dao.searchCard(name, intRarities, searchIntElements, pageRequest);
		
		return findByRarityIn;
	}

}
