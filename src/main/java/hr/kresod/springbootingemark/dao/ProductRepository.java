package hr.kresod.springbootingemark.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import hr.kresod.springbootingemark.entity.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findByCode(@RequestParam(value="code") String code);
	
	@Modifying
	@Query(value= "Update product set name = :name, description= :description, is_available = :is_available, price_eur =:price_eur, price_hrk =:price_hrk "
			+ "where id = :id " , nativeQuery = true)
	int update(Long id, String name, String description, Boolean is_available,  BigDecimal price_eur, BigDecimal price_hrk );
	
}


