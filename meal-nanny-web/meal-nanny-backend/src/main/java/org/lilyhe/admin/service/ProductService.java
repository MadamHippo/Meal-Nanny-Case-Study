package org.lilyhe.admin.service;

import org.lilyhe.admin.errors.ProductNotFoundException;
import org.lilyhe.admin.model.Product;
import org.lilyhe.admin.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lily H.
 * New service class
 */

@Service
public class ProductService {

    // references product repo
    @Autowired
    private ProductRepo repo;

    // return list of products from db
    public List<Product> listAll(){
        return (List<Product>) repo.findAll();
    }


    // saving product

    public Product save(Product product){

        // formatting if no alias to turn name into alias using string manipulation
        if (product.getAlias() == null || product.getAlias().isEmpty()) {
            String defaultAlias = product.getName().replaceAll(" ", "-");
            product.setAlias(defaultAlias);
        } else {
            product.setAlias(product.getAlias().replaceAll(" ", "-"));
        }
        return repo.save(product);
    }

    // delete product
    public void delete(Integer id) throws ProductNotFoundException {
        Long countById = repo.countById(id);

        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Could not find any product with ID " + id);
        }
        repo.deleteById(id);
    }
}
