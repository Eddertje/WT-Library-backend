package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.security.Authority;
import com.example.demo.security.AuthorityName;
import com.example.demo.security.AuthorityRepository;
import com.example.demo.entity.Employee;
import com.example.demo.repository.IEmployeeRepository;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;


@Service
public class InitService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private IEmployeeRepository userRepository;

    @Transactional
    @PostConstruct
    public void initUsers() {


        this.userRepository.deleteAll();
        this.authorityRepository.deleteAll();

        Employee user = new Employee();
        user.setEmail("johndoe"); // not to be confused with the user accessing the DB
        user.setPassword("$2a$04$mOcweZoue3.bVKiRrpPU8u1e734k2v1C0F5r8yOKYj2x5a1RrjR/O"); // password2019 bcrypted
        this.userRepository.save(user);

        user = new Employee();
        user.setEmail("janedoe");
        user.setPassword("$2a$04$mOcweZoue3.bVKiRrpPU8u1e734k2v1C0F5r8yOKYj2x5a1RrjR/O"); // password2019 bcrypted
        this.userRepository.save(user);

        initAuthorities();
    }

    private void initAuthorities() {
        // John has authority USER and ADMIN
        Employee user = this.userRepository.findEmployeeByEmail("johndoe");
        for (AuthorityName authorityName : AuthorityName.values()) {
            Authority authority = new Authority();
            authority.setName(authorityName);
            user.getAuthorities().add(authority);
        }
        this.userRepository.save(user);
        // Jane has authority USER
        user = this.userRepository.findEmployeeByEmail("janedoe");
        Authority authority = this.authorityRepository.findByName(AuthorityName.USER);
        user.getAuthorities().add(authority);
        this.userRepository.save(user);
    }
}