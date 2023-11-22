package org.hamaca.main.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

public class Cliente{

	@Id
	@JoinColumn(name="Id")
	private Usuario id;
	@Column(name="Cuenta_bancaria")
	private String cuenta_bancaria;

	public String getCuenta_bancaria() {
		return cuenta_bancaria;
	}

	public void setCuenta_bancaria(String cuenta_bancaria) {
		this.cuenta_bancaria = cuenta_bancaria;
	}
}
