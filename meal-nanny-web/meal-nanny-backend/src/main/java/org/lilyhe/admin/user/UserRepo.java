package org.lilyhe.admin.user;

import org.lilyhe.common.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// Repo layer for entity classes and queries
// Hibernate used as JPA impl by default
public interface UserRepo extends CrudRepository<User, Integer> {
    // custom query
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);

    Long countById(Integer id);
}
