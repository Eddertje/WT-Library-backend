package com.example.demo;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.security.Authority;
import com.example.demo.security.AuthorityName;
import com.example.demo.security.AuthorityRepository;
import com.example.demo.entity.Employee;
import com.example.demo.repository.IEmployeeRepository;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

/**
 * A method that gets called within the framwork that serves as initiation of roles and basic users
 * 
 *
 */
@Service
public class InitService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private IEmployeeRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method that initialises authoritise, basic users johndoe (admin) and janedoe (user) [passwords2019]
     */
    @Transactional
    @PostConstruct
    public void initUsers() {


        //this.userRepository.deleteAll();
        //this.authorityRepository.deleteAll();
    	
    	//checking if authorities exist
        for (AuthorityName authorityName : AuthorityName.values()) {
        	if(Objects.isNull(authorityRepository.findByName(authorityName))) {
        		Authority authority = new Authority();
                authority.setName(authorityName);
                authorityRepository.save(authority);
            }
        }
    	
        //create basic user john doe (admin)
        Employee user = new Employee();
        user.setEmail("johndoe"); // not to be confused with the user accessing the DB
        user.setFirstName("john");
        user.setLastName("doe");
        user.setActive(true);
        user.setAdmin(true);
        user.setPassword(this.passwordEncoder.encode("password2019")); // password2019 bcrypted
        //check if default user doesn't already exist
        if(Objects.isNull(userRepository.findEmployeeByEmail(user.getEmail()))) {
            this.userRepository.save(user);
            initAuthorities(user, true);
        }

        //create basic user jane doe (not-admin)
        user = new Employee();
        user.setEmail("janedoe");
        user.setFirstName("jane");
        user.setLastName("doe");
        user.setActive(true);
        user.setAdmin(false);
        user.setPassword(this.passwordEncoder.encode("password2019")); // password2019 bcrypted
        //check if default user doesn't already exist
        if(Objects.isNull(userRepository.findEmployeeByEmail(user.getEmail()))) {
            this.userRepository.save(user);
            initAuthorities(user, false);
        }
    }

    /**
     * method to initialise basic user authorities based on boolean admin
     * @param user the user for which you want to set authorities
     * @param admin a boolean that signifies if the user should have admin role
     */
    private void initAuthorities(Employee user, Boolean admin) {
        Authority authority = this.authorityRepository.findByName(AuthorityName.USER);
        user.getAuthorities().add(authority);
    	if(admin) {
    		//add admin role
    		authority = this.authorityRepository.findByName(AuthorityName.ADMIN);
            user.getAuthorities().add(authority);
    	}
       this.userRepository.save(user);
    }
}