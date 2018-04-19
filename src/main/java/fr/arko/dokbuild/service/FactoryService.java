package fr.arko.dokbuild.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FactoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FactoryService.class);

	@Transactional(readOnly = false)
	public void generateData() {
	}
}
