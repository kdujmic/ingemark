package hr.kresod.springbootingemark.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @RestController configuration
@Configuration 
public class MyAppRestController implements WebMvcConfigurer {

	@Value("${allowed.origins}")
	String[] allowedOrigins;
	
	
	@Value("${spring.data.rest.base-path}")
	String basePath;
	
	@Override
	public void addCorsMappings(CorsRegistry cors) {
		cors.addMapping(basePath + "/**").allowedOrigins(allowedOrigins);
		
	}
	
}
