package hr.kresod.springbootingemark.dto;

import java.math.BigDecimal;

import lombok.Data;

//Input JSON for API 
@Data
public class ProductIn {
	
	private String code;
	private String name;
	private BigDecimal price_hrk;
	private String description;
	private boolean isAvailible;
	
	
	
    public ProductIn(String code, String name, BigDecimal price_hrk, String description, boolean isAvailible) {
		super();
		this.code = code;
		this.name = name;
		this.price_hrk = price_hrk;
		this.description = description;
		this.isAvailible = isAvailible;
	}
    

} 