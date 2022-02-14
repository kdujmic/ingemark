package hr.kresod.springbootingemark;


import com.github.tomakehurst.wiremock.WireMockServer;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


/*
 * Simulating when HNB service is not available, WireMock is acting as HNB Api endpoint ( Override hnb.api.base URL inside .properties file)
 */


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:offline-application.properties") // Override hnb.api.base URL inside .properties file
public class EndPointTestsHNBServiceOfflineTest {

	String baseUrl ="http://localhost:";
	 
	 @LocalServerPort
	  private Integer port;
	
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(options()
        		.port(8000)
        		.httpsPort(8001));

        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void testCreateReadUpdateDelete_MockHNBApi() {
        // Mocking HNB API localy srednji za devize == 7,521603
        wireMockServer.stubFor(get(
                urlEqualTo("/tecajn/v1?valuta=EUR"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"Broj tečajnice\":\"10\",\"Datum primjene\":\"17.01.2022\",\"Država\":\"EMU\",\"Šifra valute\":\"978\",\"Valuta\":\"EUR\",\"Jedinica\":1,\"Kupovni za devize\":\"7,499038\",\"Srednji za devize\":\"7,521603\",\"Prodajni za devize\":\"7,544168\"}]")
        ));

        //Run Crud tests pointing to local WireMock endpoint to simulate HNB service api
        String url = baseUrl + port;
        new CrudTestsIngerMark().runCRUDTests(url);

    }

}