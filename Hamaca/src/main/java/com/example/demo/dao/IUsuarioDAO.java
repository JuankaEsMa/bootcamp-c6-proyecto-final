package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.Usuario;

import java.util.Optional;
@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {
    	
	Optional<Usuario> findByEmail(String email);
    	
	void deleteByEmail(String email);
	
}