package fr.arko.dokbuild.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Classe;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;
import fr.arko.dokbuild.service.CardsService;

@RestController
@RequestMapping(value = "/cards", method = RequestMethod.GET)
public class CardsController extends AbstractDokkanController {
	
	@Autowired
	private CardsDao dao;

	@Autowired
	private CardsService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<String> list() {
		List<String> findAll = Arrays.asList("toto", "tata", "titi");
		return findAll;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public Page<Cards> find(@RequestParam String name,
			@RequestParam(required = false, defaultValue = "") List<Rarity> rarities, 
			@RequestParam(required = false, defaultValue = "") List<Element> elements, 
			@RequestParam(required = false, defaultValue = "") List<Classe> classes) {
		return service.find(name, rarities, elements, classes);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Cards test() {
		Cards findOne = dao.findOne(1009831l);
		return findOne;
	}
}
