package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// committing changes to db after each test...
@Rollback(false)
public class UserRepoTests {
    @Autowired
    private UserRepo repo;

    @Test
    public void testCreateUser(){
        
    }
}
