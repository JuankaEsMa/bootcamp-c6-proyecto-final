package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Pais;

public interface IPaisDAO extends JpaRepository<Pais,Integer>{

    Pais findByNombre(String nombre);
}
