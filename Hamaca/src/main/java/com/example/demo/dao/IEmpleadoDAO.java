package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Cliente;
import com.example.demo.dto.Empleado;
import com.example.demo.dto.Usuario;

public interface IEmpleadoDAO extends JpaRepository<Empleado, Integer>{
	public Empleado findByUsuario(Usuario usuario);
}
