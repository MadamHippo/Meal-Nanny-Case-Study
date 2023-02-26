package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.lilyhe.common.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
// using this annotation to override default spring JPA database to run test against a real db
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RoleRepoTests {

    // making a reference to the repository using autowired
    @Autowired
    private RoleRepo repo;


    @Test
    public void testCreateFirstRole(){
        Role testAdmin = new Role("Admin", "Advocate for food accessibility & nutrition for all.");
        Role savedAdmin = repo.save(testAdmin);
        // making sure the Row object is actually in the database using assertThat API
        assertThat(savedAdmin.getId()).isGreaterThan(0);
    }
}
