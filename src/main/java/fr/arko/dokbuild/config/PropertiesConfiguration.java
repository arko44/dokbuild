package fr.arko.dokbuild.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class PropertiesConfiguration {
	@Bean
	public PropertyPlaceholderConfigurer properties() {
		final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setIgnoreResourceNotFound(true);
		ppc.setIgnoreUnresolvablePlaceholders(true);

		final List<Resource> resourceLst = new ArrayList<Resource>();

		resourceLst.add(new ClassPathResource("default.properties"));
		resourceLst.add(new ClassPathResource("dokbuild_specific.properties"));

		ppc.setLocations(resourceLst.toArray(new Resource[] {}));

		return ppc;
	}
}
