package fr.arko.dokbuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.arko.dokbuild.dao.LinkSkillsDao;
import fr.arko.dokbuild.domain.LinkSkills;

@RestController
@RequestMapping(value = "/links", method = RequestMethod.GET)
public class LinksController extends AbstractDokkanController {
	
	@Autowired
	private LinkSkillsDao dao;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<LinkSkills> list() {
		return dao.findAll();
	}
}
