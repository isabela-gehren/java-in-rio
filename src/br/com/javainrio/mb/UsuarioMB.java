package br.com.javainrio.mb;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
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
	
	public void login() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		
		Usuario temp = dao.consultar(this.usuario);
		
		if (temp != null) {
			externalContext.getSessionMap().put("usuario", temp);
			externalContext.redirect("Index.xhtml");
		}
		else {
			context.addMessage(null,  new FacesMessage("E-Mail ou Senha inválidos!"));
		}
	}
	
	public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        externalContext.invalidateSession();
        externalContext.redirect("Login.xhtml");
    }
}
