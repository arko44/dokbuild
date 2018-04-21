package fr.arko.dokbuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.arko.dokbuild.dao.CardCategoriesDao;
import fr.arko.dokbuild.domain.CardCategories;

@RestController
@RequestMapping(value = "/categories", method = RequestMethod.GET)
public class CategoriesController extends AbstractDokkanController {
	
	@Autowired
	private CardCategoriesDao dao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<CardCategories> list() {
		return dao.findAll();
	}
}
