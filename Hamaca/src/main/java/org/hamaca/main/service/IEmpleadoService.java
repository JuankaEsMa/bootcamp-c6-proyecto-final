package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Empleado;

public interface IEmpleadoService {
	public List<Empleado> listEmpleado();

	public Empleado saveEmpleado(Empleado empleado);

	public Empleado getEmpleado(Integer id);

	public void deleteEmpleado(Integer id);
}
