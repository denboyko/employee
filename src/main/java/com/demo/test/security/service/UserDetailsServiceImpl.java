package com.demo.test.security.service;

import com.demo.test.security.dao.AdminDAO;
import com.demo.test.security.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> adminOpt = adminDAO.findByUsername(username);
        if (adminOpt.isPresent()) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_ADMIN");

            return new User(adminOpt.get().getUsername(), adminOpt.get().getPassword(), grantedAuthorities);
        }
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}