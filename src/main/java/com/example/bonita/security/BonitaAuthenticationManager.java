package com.example.bonita.security;

import com.example.bonita.domain.Role;
import com.example.bonita.domain.User;
import com.example.bonita.services.RoleService;
import com.example.bonita.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BonitaAuthenticationManager implements AuthenticationManager {

    private UserService userService;


    public BonitaAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = userService.getUserByLogin(login);
        if (user == null) {
            throw new BadCredentialsException("1000");
        }

        List<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return new UsernamePasswordAuthenticationToken(login, password, authority);
    }
}
