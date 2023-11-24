package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.Localidad;


public interface ILocalidadService {
	public List<Localidad> listLocalidad();

	public Localidad saveLocalidad(Localidad localidad);

	public Localidad getLocalidad(Integer id);

	public void deleteLocalidad(Integer id);
}
