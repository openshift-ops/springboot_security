package com.springboot.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bishesh
 */
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean userenabled;

    public MyUserDetails(String username) {
        this.username = username;
    }

    private List<String> roles;


    public MyUserDetails(String username, String password, boolean userenabled, List<String> roles) {
        this.username = username;
        this.password = password;
        this.userenabled = userenabled;
        this.roles = roles;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(s -> new SimpleGrantedAuthority(s)).collect(Collectors.toList());
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return userenabled;
    }
}
