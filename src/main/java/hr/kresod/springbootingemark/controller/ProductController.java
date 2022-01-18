package hr.kresod.springbootingemark.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.kresod.springbootingemark.dto.ProductCreateResponse;
import hr.kresod.springbootingemark.dto.ProductIn;
import hr.kresod.springbootingemark.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ProductCreateResponse saveProduct(@RequestBody ProductIn productIn) {

        ProductCreateResponse productCreateResponse = productService.createProduct(productIn);

        return productCreateResponse;
    }
    
    @PostMapping("/update")
    public ProductCreateResponse updateProduct(@RequestBody ProductIn productIn) {

        ProductCreateResponse productCreateResponse = productService.updateProduct(productIn);

        return productCreateResponse;
    }

}









