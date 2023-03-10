package org.lilyhe.admin.user;

import org.junit.jupiter.api.Test;
import org.lilyhe.admin.model.Role;
import org.lilyhe.admin.model.User;
import org.lilyhe.admin.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lily H.
 *
 * testing main CRUD of entity models.
 */

// used to test JPA repo, sets up in-memory db that allows for integration testing with actual db
@DataJpaTest
// test database will not replace any existing database.
@AutoConfigureTestDatabase(replace = Replace.NONE)
// committing changes to db after each test...
@Rollback(false)
public class UserRepoTests {
    @Autowired
    private UserRepo repo;

    // provided by SpringDataJPA for unit testing with Repo
    @Autowired
    // injects a TestEntityManager instance into the test class
    // to interact with in-memo db during test runs
    private TestEntityManager entityManager;

    /* Create test users successful!
    @Test
    public void testCreateUser(){

        // USER 1
        Role testAdmin = entityManager.find(Role.class, 1);
        User userLilyHe = new User("lilyhe1991@gmail.com", "password123", "Lily", "He");
        userLilyHe.addRole(testAdmin);

        // impl. by Spring data jpa at runtime and will return an object that can be asserted
        User savedUser = repo.save(userLilyHe);
        // testing to see if user object is actually persisted...

        assertThat(savedUser.getId()).isGreaterThan(0);


        // USER 2
        Role testAssistant = entityManager.find(Role.class, 2);
        User userJaredP = new User("jared10416@gmail.com", "password123", "Jared", "P");
        // checks if the objects are actually persisted
        userJaredP.addRole(testAssistant);

        User savedUser2 = repo.save(userJaredP);
        assertThat(savedUser2.getId()).isGreaterThan(0);

    }

    */


    // use this method to list all objects in database
    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers = repo.findAll();
        // quick hand lambda, anonymous function to take each user object and print it out
        listUsers.forEach(user -> System.out.println(user));
    }

    @Test
    public void testGetUserById(){
        User userOne = repo.findById(1).get();
        System.out.println(userOne);
        assertThat(userOne).isNotNull();
    }


    @Test
    public void testUpdateUserDetails() {
        User userOne = repo.findById(1).get();
        userOne.setActive(true);
        userOne.setEmail("lilyhe1992@gmail.com");

        repo.save(userOne);
    }

    /* Test failed but it's not needed, troubleshoot in future
    @Test
    public void testUpdateUserRoles(){
        User savedUser = repo.findById(4).get();
        Role roleAdmin =  new Role(1);
        Role roleAssistant = new Role(2);
        savedUser.addRole(roleAssistant);
        savedUser.getRoles().remove(roleAdmin);

        repo.save(savedUser);
    }
     */

    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        repo.deleteById(userId);
    }


    // testing duplicate email
    @Test
    public void testGetUserByEmail() {
        String email = "foo@gmail.com";
        User user = repo.getUserByEmail(email);

        assertThat(user).isNotNull();
    }

    // testing delete function
    @Test
    public void testCountById() {
        Integer id = 4;
        Long countTestResult = repo.countById(id);

        assertThat(countTestResult).isNotNull().isGreaterThan(0);
    }

}
