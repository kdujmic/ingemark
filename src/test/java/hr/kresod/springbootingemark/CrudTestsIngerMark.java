package hr.kresod.springbootingemark;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import hr.kresod.springbootingemark.dto.ProductCreateResponse;
import hr.kresod.springbootingemark.dto.ProductIn;
import hr.kresod.springbootingemark.entity.Product;

public class CrudTestsIngerMark {

	public CrudTestsIngerMark() {
	}
	
	//CRUD testing api endpoints 
	public void runCRUDTests(String baseUrl) {
		
		
	       RestTemplate restTemplate = new RestTemplate();
	        
	       final String url = baseUrl + "/api/products"; //Api endpoing
			
	       
	       
	       //Create new product
			ProductIn productIn = new ProductIn("x123456789", "Matija", BigDecimal.TEN , "Usisavač 2000", true );
			ResponseEntity<ProductCreateResponse> entity = restTemplate.postForEntity(url + "/create", productIn, ProductCreateResponse.class);

			
			//Read is created
			Product employee = restTemplate.getForObject(url + "/" + entity.getBody().getProductId(), Product.class);
			assertEquals(employee.getName(),"Matija");

			
			//Update
			productIn = new ProductIn("x123456789", "Matija2", BigDecimal.TEN , "Neki product", true );
			entity = restTemplate.postForEntity(url + "/update", productIn, ProductCreateResponse.class);
			
			//Check is updated
			employee = restTemplate.getForObject(url + "/" + entity.getBody().getProductId(), Product.class);
			assertEquals(employee.getName(),"Matija2");
			
			//Delete
			restTemplate.delete(url + "/" + entity.getBody().getProductId());
			
			//Check is deleted
			try {
				restTemplate.getForObject(url + "/" + entity.getBody().getProductId(), Product[].class);
			} catch (HttpClientErrorException e) {
				assertEquals(e.getStatusCode(),HttpStatus.NOT_FOUND);
			}
			
			
	}
}