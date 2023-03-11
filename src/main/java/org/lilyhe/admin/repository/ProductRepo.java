package org.lilyhe.admin.repository;

import org.lilyhe.admin.model.Product;
import org.lilyhe.admin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Lily H.
 * Working with the db
 */

// extending Paging&Sorting interface + CrudRepo both because Spring 3 updated Paging&Sorting and removed some
// features that are now found in CRUD Repo.
public interface ProductRepo extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer> {

    // public Product findByName(String name);


    // Query #1 ... custom query to search for user in db with specific first name
    @Query("SELECT p.alias FROM Product p WHERE p.alias = :alias")
    // method for getting user email in db
    User getUserByAlias(@Param("alias") String alias);

    // Query #2
    //@Query("SELECT p.id FROM Product p WHERE p.cost > :cost")
    //List<Product> findByCostGreaterThan(float cost);

    // custom query #3 in UserRepo.




    public Long countById(Integer id);
}
