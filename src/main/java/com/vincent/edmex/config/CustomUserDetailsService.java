package com.vincent.edmex.config;

import com.vincent.edmex.pojo.User;
import com.vincent.edmex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service()
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserFromUsername(username);
        if (null == user || !user.getUsername().equals(username)) {
            throw new UsernameNotFoundException("No user for username : " + username);
        } else {

            return new CustomUserDetails(user);
        }
    }
}
