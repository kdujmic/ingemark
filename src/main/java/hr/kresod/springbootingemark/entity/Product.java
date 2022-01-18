package hr.kresod.springbootingemark.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price_hrk")
    private BigDecimal price_hrk;

    @Column(name = "price_eur")
    private BigDecimal price_eur;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available")
    private boolean is_available;
   
}

