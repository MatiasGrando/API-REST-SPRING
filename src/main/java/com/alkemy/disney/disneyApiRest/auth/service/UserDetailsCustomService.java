package com.alkemy.disney.disneyApiRest.auth.service;

import com.alkemy.disney.disneyApiRest.auth.dto.UserDTO;
import com.alkemy.disney.disneyApiRest.auth.model.UserModel;
import com.alkemy.disney.disneyApiRest.auth.repository.UserRepository;
import com.alkemy.disney.disneyApiRest.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username);
        if(userModel == null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(userModel.getUsername(),userModel.getPassword(),Collections.emptyList());
    }

    public boolean save(UserDTO userDTO) {
        String passwordEncode = passwordEncoder.encode(userDTO.getPassword());
        UserModel userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(passwordEncode);
        userModel = this.userRepository.save(userModel);
        if(userModel != null){
            emailService.sendWelcomeEmailTo(userModel.getUsername());
        }
        return userModel != null;
    }


}
