package hr.kresod.springbootingemark.config;


import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import hr.kresod.springbootingemark.entity.Product;

//  @RepositoryRestResource configuration
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	
	@Value("${allowed.origins}")
	String[] allowedOrigins;
	
	
	private EntityManager entityManager;
	
	
	@Autowired
	public MyDataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		
		HttpMethod[] theUnsupportedMethods = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.PATCH};
		
		//disable PUT, POST for Product leave DELETE and GET
		configUnsuportedActions(config, theUnsupportedMethods, Product.class);

			
		exposeIdForAllEntityClasses(config);
		
		// Dozvola za CORS 
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins);

		
	}



	private void configUnsuportedActions(RepositoryRestConfiguration config, HttpMethod[] theUnsupportedMethods, Class argClass) {
		config.getExposureConfiguration()
			.forDomainType(argClass)
			.withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedMethods))
			.withItemExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedMethods));
	}

	
	
	private void exposeIdForAllEntityClasses(RepositoryRestConfiguration config){
		
        config.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream()
                .map(Type::getJavaType)
                .toArray(Class[]::new));
		
		
	};
	
}

