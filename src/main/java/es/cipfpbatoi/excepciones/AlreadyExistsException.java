package es.cipfpbatoi.excepciones;

import es.cipfpbatoi.modelo.entities.Usuario;

public class AlreadyExistsException extends Exception {
	
	public AlreadyExistsException(Usuario usuario) {
		super("El usuario con DNI " + usuario.getDni() + " ya existe");
	}

}

