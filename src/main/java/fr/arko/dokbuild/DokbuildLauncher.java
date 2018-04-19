package fr.arko.dokbuild;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import fr.arko.dokbuild.dao.CardsDao;
import fr.arko.dokbuild.domain.Cards;
import fr.arko.dokbuild.service.FactoryService;

@SpringBootApplication
public class DokbuildLauncher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DokbuildLauncher.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DokbuildLauncher.class, args);
		
		//Resource resourceConfig = ctx.getResource("classpath:servers.json");
		
		FactoryService factory = ctx.getBean(FactoryService.class);
		factory.generateData();

		LOGGER.info("SupervisionChart Start successfull, wait for http layer ...");

		CardsDao cardsDao = ctx.getBean(CardsDao.class);
		Cards findOne = cardsDao.findOne(1009831l);
		LOGGER.info("Database connected !");
	}
}
