package service;

import java.io.Serializable;

import javax.inject.Inject;

import model.Usuario;
import repository.Usuarios;
import util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		
		if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
			throw new NegocioException("Já existe um usuário com o CPF informado!");
		}
		
		return usuarios.guardar(usuario);
	}

}
