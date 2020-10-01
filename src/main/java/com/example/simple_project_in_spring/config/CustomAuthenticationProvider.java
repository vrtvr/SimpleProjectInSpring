package com.example.simple_project_in_spring.config;

import com.example.simple_project_in_spring.model.User;
import com.example.simple_project_in_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    private final UserRepository repo;

    @Autowired
    public CustomAuthenticationProvider(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        User u = repo
                .findByUsername(authentication
                        .getName());
        if (u == null) {
            throw new BadCredentialsException("Invalid username");
        }
        Authentication auth = super.authenticate(authentication);
        return new UsernamePasswordAuthenticationToken(u, auth.getCredentials(),
                auth.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}





