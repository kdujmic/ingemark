package hr.kresod.springbootingemark.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import hr.kresod.springbootingemark.dao.ProductRepository;
import hr.kresod.springbootingemark.dto.ProductCreateResponse;
import hr.kresod.springbootingemark.dto.ProductIn;
import hr.kresod.springbootingemark.entity.Product;
import hr.kresod.springbootingemark.exception.MyCustomRollbackException;


/*
 * Service for CRUD actions CREATE, UPDATE. Deleting and getting are done via JPA  
 */


@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private HNBExchangeRateService exchangeServiceHNB;
    private ProductRepository productRepository;
    
    
    public ProductServiceImpl(HNBExchangeRateService exchangeServiceHNB, ProductRepository productRepository) {
        this.exchangeServiceHNB = exchangeServiceHNB;
        this.productRepository = productRepository;
    }

    @Override
    public ProductCreateResponse createProduct(ProductIn inProduct) {

    	//Validate input data
    	if(!checkValidProductIn(inProduct, false)) {
    		throw new MyCustomRollbackException("Validation checks didn't pass");
    	}
    	
        // Create product from input data && convert price to EUR
        Product product = new Product();
        
        product.setCode(inProduct.getCode());
        product.setDescription(inProduct.getDescription());
        product.setPrice_hrk(inProduct.getPrice_hrk());
        product.setPrice_eur(exchangeServiceHNB.convertToEur(inProduct.getPrice_hrk()));
        product.setName(inProduct.getName());
        product.set_available(inProduct.isAvailible());
        

        // save to the database
        product = productRepository.save(product);

        // return a response
        return new ProductCreateResponse(product.getId(), product.getPrice_eur(), "Product " + product.getName() + " created");}

    
    @Override
    @Transactional(rollbackOn = {MyCustomRollbackException.class})
    public ProductCreateResponse updateProduct(ProductIn inProduct) {

    	//Validate input data
    	if(!checkValidProductIn(inProduct,true)) {
    		throw new MyCustomRollbackException("Validation checks didn't pass");
    	}
    	
    	Product product = productRepository.findByCode(inProduct.getCode());
		if(product == null) {
			log.error("Product code is not found " + inProduct.getCode());
			throw new MyCustomRollbackException("Product not found");
		}
    	
        // Create product from input data && convert price to EUR
        
		product.setCode(inProduct.getCode());
		product.setName(inProduct.getName());
        product.setDescription(inProduct.getDescription());
        product.setPrice_hrk(inProduct.getPrice_hrk());
        product.setPrice_eur(exchangeServiceHNB.convertToEur(inProduct.getPrice_hrk()));
        product.set_available(inProduct.isAvailible());
        

        // save to the database
        Integer num = productRepository.update(product.getId(), product.getName(), product.getDescription(), 
        		product.is_available(), product.getPrice_eur(), product.getPrice_hrk());
        log.debug("Num of records updated: " + num);

        // return a response
        return new ProductCreateResponse(product.getId(), product.getPrice_eur(), "Product " + product.getName() + " created");}
    
    private boolean checkValidProductIn(ProductIn productIn, boolean isUpdate) {
    	
    	//Is code unique 
    	
    	if(!isUpdate) {
    		Product product = productRepository.findByCode(productIn.getCode());
    		if(product != null) {
    			log.error("Product code is not unique " + productIn.getCode());
    			return false;
    		}
    	}
    	
    	if(BigDecimal.ZERO.compareTo(productIn.getPrice_hrk()) > 0 ) {
    		log.error("Product price is not greater from ZERO " + productIn.getPrice_hrk());
    		return false; 
    	}
    	
    	
		return true;
	}

}









