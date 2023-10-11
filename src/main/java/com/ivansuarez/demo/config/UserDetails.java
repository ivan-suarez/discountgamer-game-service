package com.ivansuarez.demo.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivansuarez.demo.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserDetails implements UserDetailsService{

    private final UserRepository userRepository;

    public UserDetails(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return this.userRepository.findByUsername(username)
            .map(user->{
                var authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
                System.out.println("useeeeeeeeeeeeeeeeeeeeeeeer");
                System.out.println(user.getUsername()+""+ user.getPassword());
                return new User(user.getUsername(), user.getPassword(), authorities);
            }).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
    
}
