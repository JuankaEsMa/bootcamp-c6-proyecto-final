package org.hamaca.main.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

public class Cliente{

	@Id
	@JoinColumn(name="Id")
	private Usuario usuario;
	@Column(name="Cuenta_bancaria")
	private String cuentaBancaria;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Favorito",
        joinColumns = { @JoinColumn(name = "Id_Cliente") },
        inverseJoinColumns = { @JoinColumn(name = "Id_Chollo")}
    )
    private List<Chollo> favoritos;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCuentaBancaria() {
		return cuentaBancaria;
	}

	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	public List<Chollo> getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(List<Chollo> favoritos) {
		this.favoritos = favoritos;
	}
	
}
