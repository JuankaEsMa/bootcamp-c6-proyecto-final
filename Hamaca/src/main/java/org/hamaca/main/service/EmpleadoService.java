package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dao.IEmpleadoDAO;
import org.hamaca.main.dto.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	IEmpleadoDAO empleadoDAO;

	@Override
	public List<Empleado> listEmpleado() {
		// TODO Auto-generated method stub
		return empleadoDAO.findAll();
	}

	@Override
	public Empleado saveEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return empleadoDAO.save(empleado);
	}

	@Override
	public Empleado getEmpleado(Integer id) {
		// TODO Auto-generated method stub
		return empleadoDAO.findById(id).get();
	}

	@Override
	public void deleteEmpleado(Integer id) {
		// TODO Auto-generated method stub
		empleadoDAO.deleteById(id);
	}

}
