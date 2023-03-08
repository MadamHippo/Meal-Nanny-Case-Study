package org.lilyhe.admin.security;

import org.lilyhe.admin.model.Role;
import org.lilyhe.admin.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Lily H.
 * UserDetails is a Spring security interface to represent user details like username, password, roles etc.
 * I'm creating session management for login. A session is created and maintained until the user logs out.
 * The user's identity and permissions are stored in the session while logged in.
 */
public class MealNannyUserDetails implements UserDetails {

    // variable for user represented in model
    private User user;

    public MealNannyUserDetails(User user) {
        this.user = user;
    }

    // this method returns a collection of objects that represent user roles and iterates through the user roles to
    // make an object for each role. Basically it's used by Spring Security to determine roles and permissions like
    // Admin vs Assistant vs Volunteer
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // SimpleGrantedAuth is a spring security impl of GrantedAuth
        // authorities = role permissions
        // puts it in a list and creates a new SimpleGrantedAuth for each role using name string
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        // the list is then returned
        return authorities;
    }


    // all for authentication part of the UserDetails interface used by Spring security to make sure all users are legit
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullname(){
        return this.user.getFirstName() + " " + this.user.getLastName();
    }
}
