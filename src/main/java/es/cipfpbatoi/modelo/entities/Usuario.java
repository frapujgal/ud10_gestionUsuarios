package es.cipfpbatoi.modelo.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {

	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private String codPostal;
	private String telefono;
	private LocalDate fechaNacimiento;
	private String password;
	
	public Usuario(String nombre, String apellidos, String dni, String email, String codPostal, String telefono,
			LocalDate fechaNacimiento, String password) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.codPostal = codPostal;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public String getEmail() {
		return email;
	}

	public String getCodPostal() {
		return codPostal;
	}
//
	public String getTelefono() {
		return telefono;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getPassword() {
		return password;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", email=" + email
				+ ", codPostal=" + codPostal + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento
				+ ", password=" + password + "]";
	}
	
	
	
	
	
	
}
