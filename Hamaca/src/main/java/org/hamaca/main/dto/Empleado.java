package org.hamaca.main.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

public class Empleado{
	@Id
	@JoinColumn(name="Id")
	private Usuario id;
	@Column(name="Tarjeta")
	private String tarjeta;

	
	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
}
