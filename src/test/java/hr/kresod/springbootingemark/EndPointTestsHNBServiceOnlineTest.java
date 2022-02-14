package hr.kresod.springbootingemark;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndPointTestsHNBServiceOnlineTest {

	String baseUrl ="http://localhost:";
	 
	 @LocalServerPort
	  private Integer port;
	
	@Test
	public void testCreateReadUpdateDelete_LiveConnectionToHNB() {
		
		String url = baseUrl + port;
		
	      //Run Crud tests pointing on live HNB api
        new CrudTestsIngerMark().runCRUDTests(url);
	}

	@Test
	public void testErrorHandlingReturnsBadRequest() {

		RestTemplate restTemplate = new RestTemplate();

		String url = baseUrl + port + "/wrong";

		try {
			restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException e) {
			Assertions.assertThat(e.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	

}
