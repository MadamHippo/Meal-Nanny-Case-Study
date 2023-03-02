package org.lilyhe.admin.user;

import org.lilyhe.common.entity.Role;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

// UserService = business logic
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
    private BCryptPasswordEncoder passwordEncoder;


    public List<User> listAll(){
        // returns all user objects from the repo and put it in a list
        return (List<User>) userRepo.findAll();
    }

    public List<Role> listRoles(){
        return (List<Role>) roleRepo.findAll();
    }

    public void save(User user) {
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
    }

    public boolean isEmailUnique(String email) {
        User userByEmail = userRepo.getUserByEmail(email);


        return userByEmail == null;
    }

    private void encodePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


    public User get(Integer id) throws UserNotFoundException {
        try {
            // Spring data JPA has findById method
            return userRepo.findById(id).get();
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
