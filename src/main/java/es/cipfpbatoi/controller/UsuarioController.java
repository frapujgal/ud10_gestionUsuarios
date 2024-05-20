package es.cipfpbatoi.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.cipfpbatoi.excepciones.AlreadyExistsException;
import es.cipfpbatoi.excepciones.NotFoundException;
import es.cipfpbatoi.modelo.entities.Usuario;
import es.cipfpbatoi.modelo.repositories.UsuariosRepository;
import es.cipfpbatoi.validator.Validator;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@GetMapping("/usuario-form")
	public String usuarioFormActionView() {
		return "usuario_form_view";
	}

	@GetMapping("/usuario-form-buscar")
	public String usuarioBuscarActionView() {
		return "usuario_form_buscar_view";
	}
	
	@GetMapping("/usuarios")
	public String listUsuariosAction(Model model) {
		model.addAttribute("usuarios", usuariosRepository.findAll());
		return "listado_usuarios_view";
	}
	
	@PostMapping(value = "/usuario-add")
	public String postAddAction(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
		HashMap<String, String> errors = new HashMap<>();
		
		if (!Validator.isValidNumberOfLength(params.get("nombre"), 5, 20) ||
				!Validator.isValidText(params.get("nombre"))) {
			errors.put("nombre", "El nombre debe empezar por mayúscula y "
					+ " no puede contener caracteres no alfabéticos o estar vacío");
		}
		String nombre = params.get("nombre");
		
		if (!Validator.isValidNumberOfLength(params.get("apellidos"), 5, 20) ||
				!Validator.isValidText(params.get("nombre"))) {
			errors.put("apellidos", "Los apellidos deben empezar por mayúscula y "
					+ " no puede contener caracteres no alfabéticos o estar vacío");
		}
		String apellidos = params.get("apellidos");
		
		if (!Validator.isValidDni(params.get("dni"))) {
			errors.put("dni", "DNI no válido");
		}
		String dni = params.get("dni");
		
		if (!Validator.isValidEmail(params.get("email"))) {
			errors.put("email", "Email no válido");
		}
		String email = params.get("email");
		
		if (!Validator.isValidPostalCode(params.get("codPostal"))) {
			errors.put("codPostal", "Debe ser un valor numérico de 5 dígitos y no estar vacío");
		}
		String codPostal = params.get("codPostal");
		
		if (!Validator.isValidPhone(params.get("telefono"))) {
			errors.put("telefono", "Debe ser un valor numérico de 9 dígitos y no estar vacío");
		}
		String telefono = params.get("telefono");
		
		LocalDate fechaNacimiento = LocalDate.parse(params.get("fechaNacimiento"));
		
		if (!Validator.isValidPassword(params.get("password"))) {
			errors.put("password", "Debe ser un valor numérico de 9 dígitos y no estar vacío");
		}
		String password = params.get("password");
		
		if (errors.size() > 0) {
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:/usuario-form";
		}

		try {
			Usuario usuario = new Usuario(nombre, apellidos, dni, email, codPostal, telefono, fechaNacimiento, password);
			usuariosRepository.add(usuario);
			redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario añadido con éxito");
			redirectAttributes.addAttribute("dni", dni);
			return "redirect:/usuarios";
		} catch (AlreadyExistsException e) {
			errors.put("codigo", e.getMessage());
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:/usuario-form";
		}
	}

	
	@GetMapping("/usuario-find")
	public String getFindAction(@RequestParam String dni, Model model, RedirectAttributes redirectAttributes) {
		try {
			Usuario u = usuariosRepository.get(dni);
			model.addAttribute("usuario", u);
			return "usuario_details_view";
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/usuario-form-buscar";
		}
	}
	
	@GetMapping("/usuarios-find")
	public String getSomeFindAction(@RequestParam String info, Model model, RedirectAttributes redirectAttributes) {
		try {
			List usuarios = usuariosRepository.getSome(info);
			model.addAttribute("usuarios", usuarios);
			return "listado_find_view";
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/usuario-form-buscar";
		}
	}

	@GetMapping("/usuario-delete")
	public String deleteAction(@RequestParam String dni, RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = usuariosRepository.get(dni);
			usuariosRepository.delete(usuario);
			redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario con DNI " + dni + " borrado con éxito");
			return "redirect:/usuarios";
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/usuario-form-buscar";
		}
	}
	
	@GetMapping("/usuario-edit")
	public String editUsuarioAction(@RequestParam String dni, Model model, RedirectAttributes redirectAttributes) {
		
		try {
			Usuario usuario = usuariosRepository.get(dni);
			model.addAttribute(usuario);
			return "usuario_edit_view";
		} catch (NotFoundException e) {
			redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());
			return "redirect:/usuarios";
		}
	}
	
	@PostMapping("/usuario-edited")
	public String editedUsuarioAction(@RequestParam Map<String, String> params, RedirectAttributes redirectAttributes) {
		
		HashMap<String, String> errors = new HashMap<>();
		
		if (!Validator.isValidNumberOfLength(params.get("nombre"), 5, 20) ||
				!Validator.isValidText(params.get("nombre"))) {
			errors.put("nombre", "El nombre debe empezar por mayúscula y "
					+ " no puede contener caracteres no alfabéticos o estar vacío");
		}
		String nombre = params.get("nombre");
		
		if (!Validator.isValidNumberOfLength(params.get("apellidos"), 5, 20) ||
				!Validator.isValidText(params.get("nombre"))) {
			errors.put("apellidos", "Los apellidos deben empezar por mayúscula y "
					+ " no puede contener caracteres no alfabéticos o estar vacío");
		}
		String apellidos = params.get("apellidos");
		
		if (!Validator.isValidEmail(params.get("email"))) {
			errors.put("email", "Email no válido");
		}
		String email = params.get("email");
		
		if (!Validator.isValidPostalCode(params.get("codPostal"))) {
			errors.put("codPostal", "Debe ser un valor numérico de 5 dígitos y no estar vacío");
		}
		String codPostal = params.get("codPostal");
		
		if (!Validator.isValidPhone(params.get("telefono"))) {
			errors.put("telefono", "Debe ser un valor numérico de 9 dígitos y no estar vacío");
		}
		String telefono = params.get("telefono");
		
		LocalDate fechaNacimiento = LocalDate.parse(params.get("fechaNacimiento"));
		
		if (!Validator.isValidPassword(params.get("password"))) {
			errors.put("password", "Debe ser un valor numérico de 9 dígitos y no estar vacío");
		}
		String password = params.get("password");
		
		if (errors.size() > 0) {
			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:/usuario-form";
		}
		
			
		for(Usuario u : usuariosRepository.findAll()) {
			if (u.getDni().equals(params.get("dni"))) {
				u.setNombre(nombre);
				u.setApellidos(apellidos);
				u.setEmail(email);
				u.setCodPostal(codPostal);
				u.setTelefono(telefono);
				u.setFechaNacimiento(fechaNacimiento);
				u.setPassword(password);
				
				redirectAttributes.addFlashAttribute("mensajeInfo", "Usuario editado con éxito");
				redirectAttributes.addAttribute("dni", params.get("dni"));
			}
		}
		return "redirect:/usuarios";
	}
	
	
}
