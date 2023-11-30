package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.IUsuarioDAO;

/**
 * @author Jose Marin
 */

@Component
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioDAO usuarioDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioDAO.findByEmail(username)
                .map(LibraryUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}