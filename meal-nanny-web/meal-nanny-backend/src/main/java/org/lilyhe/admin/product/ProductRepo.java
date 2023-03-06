package org.lilyhe.admin.product;

import org.lilyhe.admin.user.UserService;
import org.lilyhe.common.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Lily H.
 */
public interface ProductRepo extends PagingAndSortingRepository<Product, Integer>, CrudRepository<Product, Integer> {

}
