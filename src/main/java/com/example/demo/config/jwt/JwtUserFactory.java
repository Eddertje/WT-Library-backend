package com.example.demo.config.jwt;

import com.example.demo.security.Authority;
import com.example.demo.config.jwt.JwtUser;
import com.example.demo.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Employee emp) {
        return new JwtUser(
                emp.getEmployeeId(),
                emp.getEmail(),
                emp.getPassword(),
                mapToGrantedAuthorities(emp.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}