package com.example.isabackend.services.impl;

import com.example.isabackend.entity.User;
import com.example.isabackend.entity.UserDetailsFactory;
import com.example.isabackend.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final IUserRepository _userRepository;

    @Autowired
    public MyUserDetailService(IUserRepository userRepository) {
        _userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        final User user = _userRepository.findOneByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }
        return UserDetailsFactory.create(user);
    }
}
