package fr.arko.dokbuild.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({@PropertySource("classpath:/default.properties"),@PropertySource(ignoreResourceNotFound=true, value="classpath:/application.properties")})
public class PropertiesConfiguration {
}
