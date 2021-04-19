package com.example.logiqueapi.service;

import java.util.List;
import java.util.Optional;

import com.example.logiqueapi.model.User;
import com.example.logiqueapi.repository.UserDbRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserDbRepository userDb;

    @Autowired
    public CustomUserDetailService(UserDbRepository userDb) {
        this.userDb = userDb;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userDb.findByName(username))
            .orElseThrow(()-> new UsernameNotFoundException("User not Found"));
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");
        return new org.springframework.security.core.userdetails.User
            (user.getName(), user.getPassword(), authorityListUser);
    }
}