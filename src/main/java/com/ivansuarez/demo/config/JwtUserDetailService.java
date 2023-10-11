package com.ivansuarez.demo.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ivansuarez.demo.repositories.UserRepository;

@Service
public class JwtUserDetailService implements UserDetailsService{


    private final UserRepository userRepository;

    public JwtUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
            .map(user->{
                var authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
                return new User(user.getUsername(), user.getPassword(), authorities);
            }).orElseThrow(()->new UsernameNotFoundException("User not found"));

        
    }
    
}
