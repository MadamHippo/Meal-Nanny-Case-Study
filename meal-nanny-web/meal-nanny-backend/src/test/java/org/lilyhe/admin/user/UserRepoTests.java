package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.lilyhe.common.entity.Role;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// committing changes to db after each test...
@Rollback(false)
public class UserRepoTests {
    @Autowired
    private UserRepo repo;

    // provided by SpringDataJPA for unit testing with Repo
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        Role testAdmin = entityManager.find(Role.class, 1);
        User userLilyHe = new User("lilyhe1991@gmail.com", "password123", "Lily", "He");
        userLilyHe.addRole(testAdmin);

        // impl. by Spring data jpa at runtime and will return an object that can be asserted
        User savedUser = repo.save(userLilyHe);
        // testing to see if user object is actually persisted...
        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
