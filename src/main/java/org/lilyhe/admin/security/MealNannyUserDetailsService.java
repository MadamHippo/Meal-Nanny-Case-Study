package org.lilyhe.admin.security;
import org.lilyhe.admin.repository.UserRepo;
import org.lilyhe.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


/**
 * @author Lily H.
 * this code is used to retrieve a user object from the db using a unique email address and then making & returning
 * userDetails object that contains info about the user from GrantedAuth.
 */


// Impl. on spring security
public class MealNannyUserDetailsService implements UserDetailsService {

    // auto-wiring annotation injects "UserRepo" (a Spring data jpa repo) into this field name variable called
    // userRepo for dependency injection basically "userRepo" is a type of "UserRepo"
    @Autowired
    private UserRepo userRepo;


    // we're overriding loadUserByUsername with this method that takes 'email address' as a parameter and returns a
    // UserDetails object.
    // note: username = email address
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // calling object in userRepo to get email and returning that email to assign to user. user = email right now
        User user = userRepo.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Cloud not find user with username: " + username);
        } else {
            // so user's email does exist in db, then return the UserDetails interface specified in the other file
            return new MealNannyUserDetails(user);
        }
    }
}
