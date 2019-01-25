package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;


@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService{
	
	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}
	
	public void cadastrar(Usuario usuario) {
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada); 
		manager.persist(usuario);
	}
	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u", Usuario.class)
				.getResultList();
	}
	public boolean usuarioJaExiste(Usuario usuario) {
		if (manager.find(Usuario.class, usuario.getEmail()) != null) {
			return true;
		}
		return false;
	}
	
	public boolean senhasSaoIguais(Usuario usuario) {
	if(usuario.getSenha()==usuario.getSenhaRepetida()) {
		return true;
	}
	else {
		return false;
	}
}
	public Usuario find(String email) {
			return manager.find(Usuario.class, email);
	}
	public void atualizaRole(String email, List<Role> roles) {
		Usuario usuario = manager.find(Usuario.class, email);
		usuario.setRoles(roles);	
	}

}