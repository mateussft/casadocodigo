package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.ProdutoValidation;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioDAO usuarioDao;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	@RequestMapping(value = "/form")
	public ModelAndView form(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("usuarios/form");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(usuario);
		}
		if(!usuarioDao.usuarioJaExiste(usuario)) {	
			usuarioDao.cadastrar(usuario);	
			redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");
			}//fim do primeiro if
		
		else {
			redirectAttributes.addFlashAttribute("message", "Usuario já existe em nosso sistema!");
			return new ModelAndView("redirect:/usuarios/form");
		}
		return new ModelAndView("redirect:/usuarios/");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		List<Usuario> usuarios = usuarioDao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/lista");
		modelAndView.addObject("usuarios", usuarios);
		return modelAndView;
	}
	@RequestMapping(value="/role/form")
	public ModelAndView alterarRole(String email) {
		System.out.println("Email do crash: "+email);
		Usuario usuario = usuarioDao.find(email.substring(1));
		System.out.println("User: "+usuario);	
		ModelAndView modelAndView = new ModelAndView("usuarios/formRole");
		modelAndView.addObject("usuario", usuario);
		return modelAndView;		
	}
	@RequestMapping(value="/role/form/gravar")
	public ModelAndView atualizarRole(Usuario usuario, RedirectAttributes redirectAttributes) {
		System.out.println(usuario.getEmail()+"-----"+ usuario.getRoles());
		usuarioDao.atualizaRole(usuario.getEmail(), usuario.getRoles());
		redirectAttributes.addFlashAttribute("message", "Permissões atualizadas com sucesso!");
		return new ModelAndView("redirect:/usuarios");
	}
}
