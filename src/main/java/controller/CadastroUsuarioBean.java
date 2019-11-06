package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import model.Usuario;
import service.CadastroUsuarioService;
import util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	private Usuario usuario;
	
	public CadastroUsuarioBean() {
		limpar();
	}
	
	private void limpar() {
		usuario = new Usuario();
	}
	
	public void salvar() {
		this.usuario = cadastroUsuarioService.salvar(this.usuario);
		limpar();
		
		FacesUtil.addInfoMessage("Usuaário salvo com sucesso!");
	}

	public boolean isEditando() {
		return this.usuario.getId() != null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}