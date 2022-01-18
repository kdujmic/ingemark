package hr.kresod.springbootingemark.dto;

import java.math.BigDecimal;

import lombok.Data;

//Response JSON from api 
@Data
public class ProductCreateResponse {
	
	private Long productId;
	private BigDecimal price_eur;
	private String msg="Product successfully saved";
	

	public ProductCreateResponse() {
	}
    public ProductCreateResponse(Long productId, BigDecimal price_eur, String msg) {
		this.productId = productId;
		this.price_eur = price_eur;
		this.msg = msg;
	}

} 