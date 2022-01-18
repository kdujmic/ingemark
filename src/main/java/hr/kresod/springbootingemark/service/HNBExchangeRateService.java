package hr.kresod.springbootingemark.service;

import java.math.BigDecimal;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import hr.kresod.springbooteingemark.exceptions.MyCustomRollbackException;
import hr.kresod.springbootingemark.dto.HNBTecaj;
import hr.kresod.springbootingemark.utils.BigDecimalConverter;
import reactor.core.publisher.Mono;

/*
 * HNBService for fetching && converting input price to eur price - EUR excange reate 
 */

@Service
public class HNBExchangeRateService {
	
	//String HNB_BASE_API_PATH = "https://api.hnb.hr/tecajn/v1?valuta=EUR";	
	String HNB_BASE_API_PATH;

	
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private static final Logger log = LoggerFactory.getLogger(HNBExchangeRateService.class);

    private final WebClient webClient;
	
    public HNBExchangeRateService(WebClient.Builder webClientBuilder, @Value("${hnb.api.base}") String apiUrl) {
    	HNB_BASE_API_PATH=apiUrl;
    	log.info("HNB path is " + HNB_BASE_API_PATH);
    	this.webClient = webClientBuilder
    			.baseUrl(HNB_BASE_API_PATH)
    			 .filters(exchangeFilterFunctions -> {
    			      exchangeFilterFunctions.add(logRequest());
    			  })
    			.build();
    }

	
	//Method for converting EUR to HRK 
	public BigDecimal convertToEur(BigDecimal price_hrk) {
		
		HNBTecaj[] tecajEur = getExchangeRateEURHNB();
		
		if(tecajEur.length == 0 || tecajEur == null) {
			throw new MyCustomRollbackException("Tečaj nije dohvaćen");
		}
		
		BigDecimal tecajEurSrednji = BigDecimalConverter.parseHnbString(tecajEur[0].getSrednji_za_devize());
		
		
		BigDecimal price_eur = tecajEurSrednji.multiply(price_hrk);

		return price_eur;
	}
	
	
	private HNBTecaj[] getExchangeRateEURHNB() {
		
		return webClient
        .get()
        .retrieve()
        .bodyToMono(HNBTecaj[].class)
        .log()
        .block(REQUEST_TIMEOUT);
		
		
	}
	

    // Logiramo poziv servisa
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

}
