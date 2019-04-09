package com.demo.test.security.dao;


import com.demo.test.security.entity.Admin;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDAO {

    public Optional<Admin> findByUsername(String email){
        if(email.equals("test")){
            return Optional.of(new Admin("test", "test"));  //password must be encrypted
        }
        return Optional.empty();
    }
}
