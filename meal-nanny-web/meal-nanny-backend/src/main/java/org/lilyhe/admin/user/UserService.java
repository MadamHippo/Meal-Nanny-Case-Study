package org.lilyhe.admin.user;

import org.lilyhe.common.entity.Role;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Lily H.
 *
 * UserService = business logic
 */


// using this bean to give biz logic for application
@Service
public class UserService {

    // injects instance of userRepo class into repo field using Dep Injection. Allows the service to access and
    // interact with the repo without creating a new instance
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // method of the LoggerFactory class from the org.slf4j package
    // good to display different types of msg to debug
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    // gets stuff and returns user role objects from repo...and...
    public List<User> listAll(){
        // ...returns all user objects from the repo and put it in a list
        return (List<User>) userRepo.findAll();
    }

    public List<Role> listRoles(){
        return (List<Role>) roleRepo.findAll();
    }


    @Transactional
    public void save(User user) {
        // info used to log messages. (other options include debug, warn, error etc.)
        logger.info("Saving user: ", user);
        boolean isUpdatingUser = (user.getId() != null);
        if (isUpdatingUser) {
            User existingUser = userRepo.findById(user.getId()).get();

            if (user.getPassword().isEmpty()){
                user.setPassword(existingUser.getPassword());
            } else {
                encodePassword(user);
            }
        } else {
            encodePassword(user);
        }
        userRepo.save(user);
        // logger method to log this msg:
        logger.info("User saved: ", user);
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepo.getUserByEmail(email);

        if (userByEmail == null) {
            return true;
        }
        return userByEmail.getId().equals(id);
    }

    // encoding password using hashing algorithm to protect users, it cannot be reversed
    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


    // gets User object from database or throw error if user doesn't exist...
    public User get(Integer id) throws UserNotFoundException {
        try {
            // Spring data JPA has findById method
            return userRepo.findById(id).get();
            // Note, the .get() chained is used to extract the User object from the Optional<User> returned by findById().
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("Couldn't find any user with ID: " + id);
        }
    }


    // new method: delete functionality
    public void delete(Integer id) throws UserNotFoundException {
        Long countById = userRepo.countById(id);
        if (countById == null || countById < 1) {
            throw new UserNotFoundException("Couldn't find any user with ID: " + id);
        }

        userRepo.deleteById(id);
    }
}
