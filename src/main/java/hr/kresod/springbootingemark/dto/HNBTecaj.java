package hr.kresod.springbootingemark.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

//Only few properties are taken from HNB JSON ( we need only srednji_za_devize) 

@Data
public class HNBTecaj {

	 @JsonProperty("Broj tečajnice")
	String broj_tecajnice;
	 
	 @JsonProperty("Datum primjene")
	String datumPrimjene;
	 
	 @JsonProperty("Država")
	String drzava;
	 
	 @JsonProperty("Srednji za devize")
	String srednji_za_devize;
	
}
