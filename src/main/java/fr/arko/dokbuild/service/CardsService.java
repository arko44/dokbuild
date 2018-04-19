package fr.arko.dokbuild.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.dao.PassiveSkillsDao;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;

@Transactional(readOnly = true)
@Service
public class CardsService {
	
	@Autowired
	private CardsDao dao;

	@Autowired
	private PassiveSkillsDao passiveSkillsDao;
	
	public Page<Cards> find(List<Rarity> rarities, List<Element> elements, Boolean classe) {
		PageRequest pageRequest = new PageRequest(0, 20, Direction.DESC, "name");
		return dao.findAll(pageRequest);
	}

}
