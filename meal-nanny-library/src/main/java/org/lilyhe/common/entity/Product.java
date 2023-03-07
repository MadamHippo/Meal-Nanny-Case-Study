package org.lilyhe.common.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Lily H.
 * Product entity for our definition of product specifications
 */

// Jpa time
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 256, nullable = false)
    private String name;
    @Column(unique = true, length = 256, nullable = false)
    private String alias;
    @Column(name = "description", length = 4000, nullable = false)
    private String description;
    @Column(name = "in_stock")
    private int inStock;
    private float cost;


    // main image required
    @Column(name = "main_image")
    private String mainImage;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductImage> images = new HashSet<>();


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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Product [id= " + id + ", name= " + name + "]";
    }

    // getter setter for main image
    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }


    //maybe in the future for adding extra images

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public void addExtraImage(String imageName) {
        this.images.add(new ProductImage(imageName, this));
    }


    // Transient means Hibernate can remember this getter to any columns in database
    @Transient
    public String getMainImagePath() {
        if (id == null || mainImage == null) return "/images/Meal-Nanny-logo-2.png";

        // Help product-images??
        return "/product-images/" + this.id + "/" + this.mainImage + "?width=100";
    }

}

