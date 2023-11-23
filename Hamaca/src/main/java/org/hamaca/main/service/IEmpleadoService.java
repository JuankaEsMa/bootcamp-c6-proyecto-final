package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Empleado;
import org.hamaca.main.dto.Usuario;

public interface IEmpleadoService {
	public List<Empleado> listEmpleado();

	public Empleado saveEmpleado(Empleado empleado);

	public Empleado getEmpleado(Usuario id);

	public void deleteEmpleado(Usuario id);
}
