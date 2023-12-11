package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Empleado;
import com.example.demo.dto.Usuario;

public interface IEmpleadoService {
	public List<Empleado> listEmpleado();
	public Empleado saveEmpleado(Empleado empleado);
	public Empleado getEmpleado (Integer id);
	public void deleteEmpleado (Integer id);
	public Empleado findEmpleadoByUsuario(Usuario usuario);
}
