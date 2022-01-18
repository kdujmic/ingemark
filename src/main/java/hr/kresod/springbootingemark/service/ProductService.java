package hr.kresod.springbootingemark.service;

import hr.kresod.springbootingemark.dto.ProductCreateResponse;
import hr.kresod.springbootingemark.dto.ProductIn;

public interface ProductService {
	//CRUD on product - GET, DELETE are done via JPA 
	ProductCreateResponse createProduct(ProductIn productIn);
	
	ProductCreateResponse updateProduct(ProductIn productIn);
    
}
