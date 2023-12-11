package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Reserva;

public interface IReservaDAO extends JpaRepository<Reserva,Integer>{
	public List<Reserva> findByCliente(Cliente cliente);
}
