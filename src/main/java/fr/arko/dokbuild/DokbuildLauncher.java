package fr.arko.dokbuild;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

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
		ctx.getBean(CardsDao.class);
		cardsService.findOne(1009831);
		
		PageRequest pageRequest = new PageRequest(0, 20, new Sort(new Order(Direction.ASC, "cost")));
		Page<Cards> find = cardsService.find("vege", -1, -1, Arrays.asList(Rarity.SSR), Arrays.asList(Element.AGL), Arrays.asList(Classe.SUPER), pageRequest);
		
		LOGGER.info("nb cards : " + find.getContent().size());
		
		LOGGER.info("Database connected !");
	}
}
