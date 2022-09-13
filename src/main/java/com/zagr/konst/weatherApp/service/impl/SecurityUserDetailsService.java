package com.zagr.konst.weatherApp.service.impl;

import com.zagr.konst.weatherApp.model.User;
import com.zagr.konst.weatherApp.repository.UserRepository;
import com.zagr.konst.weatherApp.security.SecurityUserDetails;
import com.zagr.konst.weatherApp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user==null) throw new NullPointerException();

        return new SecurityUserDetails(user);
    }
}
