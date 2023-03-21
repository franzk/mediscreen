package com.abernathy.mediscreen.mauthorization.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//public class ApplicationUserDetailsService implements UserDetailsService {

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.getByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found !"));
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
//    }
//}
