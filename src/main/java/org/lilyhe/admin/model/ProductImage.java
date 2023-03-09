package org.lilyhe.admin.model;


import jakarta.persistence.*;

/**
 * @author Lily H.
 * Mapping images to tables in db
 */


@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    // a product can have more than 1 images if in future I want this ability
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    // generated constructor for name and product
    public ProductImage(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    // default no arg constructor for Hibernate
    public ProductImage(){
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
