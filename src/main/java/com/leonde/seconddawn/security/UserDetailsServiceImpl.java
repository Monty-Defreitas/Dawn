package com.leonde.seconddawn.security;

import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve user information from the database or other source
        User user = retrieveUserFromDatabase(username);

        // Create a list of authorities based on the user's roles
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        // Create and return a UserPrincipal object
        return new UserPrincipal(user.getUsername(), user.getPassword(), authorities);
    }

    private User retrieveUserFromDatabase(String username) {
        // Code to retrieve user information from the database
        // ...

        // Example user information
        return new User("john", "password", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
    }
}

