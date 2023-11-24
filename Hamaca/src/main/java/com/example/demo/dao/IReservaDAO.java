package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Reserva;

public interface IReservaDAO extends JpaRepository<Reserva,Integer>{

}
