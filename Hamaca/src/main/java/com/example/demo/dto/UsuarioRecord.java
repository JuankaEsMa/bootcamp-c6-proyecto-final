package com.example.demo.dto;

import java.sql.Date;

public record UsuarioRecord(Integer id, String nombre, String apellidos, String email, Date fechaNacimiento){}