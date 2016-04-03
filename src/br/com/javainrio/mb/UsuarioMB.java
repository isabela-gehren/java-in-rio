package br.com.javainrio.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.javainrio.entidade.Palestra;
import br.com.javainrio.entidade.Usuario;
import br.com.javainrio.facade.UsuarioFacade;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	@EJB
	private UsuarioFacade dao;
	
	public UsuarioMB(){
		usuario = new Usuario();
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario u) {
		usuario = u;
	}
	
	public void salvar() {
		try {
			usuario.setCpf(usuario.getCpf().replace(".", "").replace("-", ""));
			usuario.setCep(usuario.getCep().replace(".", "").replace("-", ""));
			
			dao.salvar(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cadastro de Usuário: ", "Usuário salvo com sucesso!"));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cadastro de Usuário: ", "E-mail e/ou CPF já cadastrado!"));
		}
	}	

	public void limpar() {
		usuario = new Usuario();
	}
}
