package org.lilyhe.admin.product;

import org.lilyhe.admin.user.UserService;
import org.lilyhe.common.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Lily H.
 * Working with the db
 */

// extending Paging&Sorting interface + CrudRepo both because Spring 3 updated Paging&Sorting and removed some
// features that are now found in CRUD Repo.
public interface ProductRepo extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer> {

}
