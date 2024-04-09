package com.example.tollschallengeapi.config.security;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.tollschallengeapi.domain.Usuario;
import com.example.tollschallengeapi.repository.UsuarioRepository;

@Component
public class CustomUsuarioDetailsService implements UserDetailsService {
    
	@Autowired
    private UsuarioRepository repository;
    
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = this.repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

}
