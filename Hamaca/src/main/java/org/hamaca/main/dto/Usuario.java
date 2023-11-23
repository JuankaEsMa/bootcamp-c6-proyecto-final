package org.hamaca.main.dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="Nombre")
	private String nombre;
	@Column(name="Apellidos")
	private String apellidos;
	@Column(name="Telefono")
	private String telefono;
	@Column(name="DNI")
	private String dni;
	@Column(name="Direccion")
	private String direccion;
	@Column(name="Email")
	private String email;
	@Column(name="Fecha_Nacimiento")
	private Date fecha_nacimeinto;
	@Column(name="Is_Deleted")
	private Boolean is_deleted;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String apellidos, String telefono, String dni, String direccion, String email,
			Date fecha_nacimeinto, Boolean is_deleted) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.dni = dni;
		this.direccion = direccion;
		this.email = email;
		this.fecha_nacimeinto = fecha_nacimeinto;
		this.is_deleted = is_deleted;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha_nacimeinto() {
		return fecha_nacimeinto;
	}
	public void setFecha_nacimeinto(Date fecha_nacimeinto) {
		this.fecha_nacimeinto = fecha_nacimeinto;
	}
	public Boolean getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", dni=" + dni + ", direccion=" + direccion + ", email=" + email + ", fecha_nacimeinto="
				+ fecha_nacimeinto + ", is_deleted=" + is_deleted + "]";
	}
}
