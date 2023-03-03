package org.lilyhe.admin.security;

import org.lilyhe.admin.user.UserRepo;
import org.lilyhe.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MealNannyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Cloud not find user with username: " + username);
        } else {
            return new MealNannyUserDetails(user);
        }
    }
}
