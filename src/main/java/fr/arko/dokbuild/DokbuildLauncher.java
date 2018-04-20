package fr.arko.dokbuild;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;

import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.enumeration.Classe;
import fr.arko.dokbuild.enumeration.Element;
import fr.arko.dokbuild.enumeration.Rarity;
import fr.arko.dokbuild.service.CardsService;
import fr.arko.dokbuild.service.FactoryService;

@SpringBootApplication
public class DokbuildLauncher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DokbuildLauncher.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DokbuildLauncher.class, args);
		
		FactoryService factory = ctx.getBean(FactoryService.class);
		factory.generateData();

		LOGGER.info("SupervisionChart Start successfull, wait for http layer ...");

		CardsService cardsService = ctx.getBean(CardsService.class);
		CardsDao cardsDao = ctx.getBean(CardsDao.class);
		Cards findOne = cardsService.findOne(1009831);
		
		Page<Cards> find = cardsService.find("vege", Arrays.asList(Rarity.SSR), Arrays.asList(Element.AGL), Arrays.asList(Classe.SUPER));
		
		LOGGER.info("nb cards : " + find.getContent().size());
		
		LOGGER.info("Database connected !");
	}
}
