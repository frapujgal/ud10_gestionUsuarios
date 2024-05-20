package es.cipfpbatoi.modelo.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.cipfpbatoi.excepciones.AlreadyExistsException;
import es.cipfpbatoi.excepciones.NotFoundException;
import es.cipfpbatoi.modelo.entities.Usuario;

@Service
public class UsuariosRepository {

	private List<Usuario> usuarios;

	public UsuariosRepository() {
		this.usuarios = new ArrayList<>();
		
		Usuario user1 = new Usuario("Paco", "Pujol Gallego", "21689308D", "frapujgal@alu.edu.gva.es", "03804", "652315815", LocalDate.of(1989,  7, 14), "12345");
		Usuario user2 = new Usuario("Javier", "López de Pablo Lahuerta", "21690000J", "javloplah@alu.edu.gva.es", "03810", "652310000", LocalDate.of(1991,  6, 22), "12345");
		Usuario user3 = new Usuario("Denis", "Cerro Feo", "23905403P", "denis@alu.edu.gva.es", "03804", "652058129", LocalDate.of(2005, 10, 04), "12345");
		Usuario user4 = new Usuario("Oscar", "Juana Juano", "21691258A", "oscar@alu.edu.gva.es", "03810", "652314586", LocalDate.of(2005,  6, 22), "12345");
		Usuario user5 = new Usuario("Paco", "Porras", "21650825F", "pacoporras@alu.edu.gva.es", "03804", "652315815", LocalDate.of(1989,  7, 14), "12345");
		
		usuarios.add(user1);
		usuarios.add(user2);
		usuarios.add(user3);
		usuarios.add(user4);
		usuarios.add(user5);
	}
	
	public void add(Usuario usuario) throws AlreadyExistsException {
		if (!usuarios.contains(usuario)) {
			this.usuarios.add(usuario);
			System.out.println("Añadido con exito!");
			mostrarUsuarios();
			return;
		}		
		throw new AlreadyExistsException(usuario);
	}

	private void mostrarUsuarios() {
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

	public void delete(String dni) throws NotFoundException {
		Usuario usuario = this.get(dni);
		this.usuarios.remove(usuario);
		System.out.println("Borrado con exito!");
		mostrarUsuarios();
	}

	public void delete(Usuario usuario) throws NotFoundException {
		this.usuarios.remove(usuario);
		System.out.println("Borrado con exito!");
		mostrarUsuarios();
	}

	public Usuario get(String dni) throws NotFoundException {
		for (Usuario u : this.usuarios) {
			if (u.getDni().equals(dni)) {
				return u;
			}
		}
		throw new NotFoundException("El usuario con DNI " + dni + " no existe");
	}
	
	public List<Usuario> getSome(String dni) throws NotFoundException {
		
		List<Usuario> users = new ArrayList<Usuario>();
		
		for (Usuario u : this.usuarios) {
			if (u.getDni().equals(dni)) {
				users.add(u);
			}
			if (u.getNombre().equals(dni)) {
				users.add(u);
			}
			if (u.getApellidos().equals(dni)) {
				users.add(u);
			}
			if (u.getEmail().equals(dni)) {
				users.add(u);
			}
		}
		
		return users;
	}

	public List<Usuario> findAll() {
		return this.usuarios;
	}


}
