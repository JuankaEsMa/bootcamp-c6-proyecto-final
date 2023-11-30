package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Usuario;
import com.example.demo.dto.UserRecord;

public interface IUserService {
    Usuario add(Usuario user);
    List<UserRecord> getAllUsers();
    void delete(String email);
    Usuario getUser(String email);
    Usuario update(Usuario user);
}