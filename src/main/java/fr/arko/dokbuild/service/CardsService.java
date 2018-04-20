package fr.arko.dokbuild.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(CardsService.class);
	
	@Autowired
	private CardsDao dao;

	@Autowired
	private PassiveSkillsDao passiveSkillsDao;

	public Page<Cards> find(String name, List<Rarity> rarities, List<Element> elements, List<Classe> classes) {
		PageRequest pageRequest = new PageRequest(0, 20, Direction.DESC, "cost");

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

		Page<Cards> findByRarityIn = dao.searchCard(name, intRarities, searchIntElements, pageRequest);

		//downloadThumbsIfNotExist(findByRarityIn.getContent());
		
		return findByRarityIn;
	}
	
	private static void downloadThumbsIfNotExist(List<Cards> list) {
		String root = "D:/projets/repository/dokbuild/webapp/images/thumbs/";
		
		list.forEach(x -> {
			try {
				String newFilePath = root + x.getId() + "_thumb.png";
				Path path = Paths.get(newFilePath);
				if (!path.toFile().exists()) {
					LOGGER.error("File " + x.getId() + " not exist");
					downloadUsingNIO(
							"http://images.dokkan.info/images/card_thumbs_full/small/card_" + x.getId() + "_thumb_full.png", 
							newFilePath);
				}
			} catch (Exception e) {
				
			}
		});
	}

	private static void downloadUsingNIO(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		ReadableByteChannel rbc = null;
		FileOutputStream fos = null;
		try {
			rbc = Channels.newChannel(url.openStream());
			fos = new FileOutputStream(file);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (rbc != null) {
				rbc.close();
			}
		}
	}
}
