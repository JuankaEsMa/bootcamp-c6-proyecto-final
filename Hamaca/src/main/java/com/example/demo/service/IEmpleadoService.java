package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Empleado;

public interface IEmpleadoService {
	public List<Empleado> listEmpleado();
	public Empleado saveEmpleado(Empleado empleado);
	public Empleado getEmpleado (Integer id);
	public void deleteEmpleado (Integer id);
}
