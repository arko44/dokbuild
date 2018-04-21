package fr.arko.dokbuild.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Classe;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;
import fr.arko.dokbuild.service.CardsService;

@RestController
@RequestMapping(value = "/cards", method = RequestMethod.GET)
public class CardsController extends AbstractDokkanController {
	
	@Autowired
	private CardsService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<String> list() {
		List<String> findAll = Arrays.asList("toto", "tata", "titi");
		return findAll;
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public Page<Cards> find(@RequestParam String name, 
			@RequestParam(required = false, defaultValue = "-1") Integer category,
			@RequestParam(required = false, defaultValue = "-1") Integer link,
			@RequestParam(required = false, defaultValue = "") List<Rarity> rarities, 
			@RequestParam(required = false, defaultValue = "") List<Element> elements, 
			@RequestParam(required = false, defaultValue = "") List<Classe> classes,
			@RequestParam Direction direction, @RequestParam String column,
			@RequestParam Integer size ) {
		PageRequest pageRequest = new PageRequest(0, size, new Sort(new Order(direction, column), new Order(Direction.DESC, "id")));
		return service.find(name, category, link, rarities, elements, classes, pageRequest);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public Cards get(@RequestParam Integer id) {
		return service.findOne(id);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public PageRequest test() {
		return new PageRequest(0, 20, new Sort(new Order(Direction.DESC, "cost"), new Order(Direction.DESC, "element"), new Order(Direction.DESC, "id")));
	}
}
