package org.hamaca.main.service;

import java.util.List;
import org.hamaca.main.dto.Localidad;

public interface ILocalidadService {
	public List<Localidad> listLocalidad();

	public Localidad saveLocalidad(Localidad localidad);

	public Localidad getLocalidad(Integer id);

	public void deleteLocalidad(Integer id);
}
