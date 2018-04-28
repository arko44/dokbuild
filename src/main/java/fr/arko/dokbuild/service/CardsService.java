package fr.arko.dokbuild.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.arko.dokbuild.dao.CardCategoriesDao;
import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.dao.LeaderSkillsDao;
import fr.arko.dokbuild.dao.LinkSkillsDao;
import fr.arko.dokbuild.dao.PassiveSkillsDao;
import fr.arko.dokbuild.dao.SpecialsDao;
import fr.arko.dokbuild.domain.CardCategories;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.domain.LeaderSkills;
import fr.arko.dokbuild.domain.LinkSkills;
import fr.arko.dokbuild.domain.PassiveSkills;
import fr.arko.dokbuild.domain.Specials;
import fr.arko.dokbuild.enumeration.Classe;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;

@Transactional(readOnly = true)
@Service
public class CardsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CardsService.class);
	
	@Autowired
	private CardsDao dao;

	@Autowired
	private LeaderSkillsDao leaderSkillsDao;
	
	@Autowired
	private PassiveSkillsDao passiveSkillsDao;

	@Autowired
	private LinkSkillsDao linkSkillsDao;
	
	@Autowired
	private SpecialsDao specialsDao;
	
	@Autowired
	private CardCategoriesDao cardCategoriesDao;

	public Page<Cards> find(String name, Integer category, Integer link, List<Rarity> rarities, List<Element> elements, List<Classe> classes, PageRequest pageRequest) {
		List<Integer> intRarities = (rarities.isEmpty() ? Arrays.asList(Rarity.values()) : rarities).stream()
				.map(x -> x.value()).collect(Collectors.toList());
		List<Integer> intElements = (elements.isEmpty() ? Arrays.asList(Element.values()) : elements).stream()
				.map(x -> x.value()).collect(Collectors.toList());

		List<Integer> searchIntElements = new ArrayList<Integer>();
		if (classes.isEmpty() || classes.contains(Classe.SUPER)) {
			searchIntElements
					.addAll(intElements.stream().map(x -> x + Classe.SUPER.value()).collect(Collectors.toList()));
		}
		if (classes.isEmpty() || classes.contains(Classe.EXTREME)) {
			searchIntElements
					.addAll(intElements.stream().map(x -> x + Classe.EXTREME.value()).collect(Collectors.toList()));
		}

		Page<Cards> result = dao.searchCard(name, category, link, intRarities, searchIntElements, pageRequest);
		LOGGER.debug("Get {} cards on {}", result.getNumberOfElements(), result.getTotalElements());

		return result;
	}
	
	public Cards findOne(int id) {
		Cards card = dao.findOne(id);
		
		if (card.getLeaderSkillId() != null) {
			LeaderSkills leader = leaderSkillsDao.findOne(card.getLeaderSkillId());
			card.setLeaderSkill(leader);
		}
		
		if (card.getPassiveSkillSetId() != null) {
			PassiveSkills passive = passiveSkillsDao.findOne(card.getPassiveSkillSetId());
			card.setPassiveSkill(passive);
		}
		
		List<Specials> listSpecials = specialsDao.findByCardId(id);
		card.setSpecials(listSpecials);

		List<Integer> categoriesId = Arrays.asList(card.getCardCategory1Id(), card.getCardCategory2Id())
				.stream().filter(x->x!=null).collect(Collectors.toList());
		List<CardCategories> listCategories = cardCategoriesDao.findByIdIn(categoriesId);
		card.setCardCategories(listCategories);
		
		List<Integer> linkSkillsId = Arrays.asList(card.getLinkSkill1Id(), card.getLinkSkill2Id(), card.getLinkSkill3Id(), 
				card.getLinkSkill4Id(), card.getLinkSkill5Id(), card.getLinkSkill6Id(), card.getLinkSkill7Id())
				.stream().filter(x->x!=null).collect(Collectors.toList());
		List<LinkSkills> listSkills = linkSkillsDao.findByIdIn(linkSkillsId);
		card.setLinkSkills(listSkills);
		
		return card;
	}
}
