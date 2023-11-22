package org.hamaca.main.service;

import java.util.List;

import org.hamaca.main.dto.Tematica;

public interface ITematicaService {
	public List<Tematica> listTematica();
	public Tematica saveTematica(Tematica tematica);
	public Tematica getTematica(Integer id);
	public void deleteTematica(Integer id);
}
