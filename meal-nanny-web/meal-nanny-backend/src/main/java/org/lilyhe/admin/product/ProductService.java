package org.lilyhe.admin.product;

import org.lilyhe.common.entity.Product;
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
}
