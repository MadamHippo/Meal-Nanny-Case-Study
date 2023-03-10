package org.lilyhe.admin.repository;

import org.lilyhe.admin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Lily H.
 * Repo layer for entity classes and queries
 * Hibernate used as JPA impl by default
 * we can use them in the UserService class to access data from the database using JPA
 */

// code extends CRUD ops by JPA to get data from database using search criteria. User Repo interface is used in
// UserService class to access data using JPA
public interface UserRepo extends CrudRepository<User, Integer> {

    // Query #1 ....custom query to search for user in db with specific email
    @Query("SELECT u FROM User u WHERE u.email = :email")
    // method for getting user email in db
    User getUserByEmail(@Param("email") String email);

    // Note: custom Query #2 and #3 in ProductRepo


    // method that returns count of id records in db
    Long countById(Integer id);
}
