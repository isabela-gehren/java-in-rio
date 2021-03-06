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
import br.com.javainrio.entidade.Usuario;
import br.com.javainrio.facade.UsuarioFacade;

@ManagedBean(name = "usuarioMB")
@RequestScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Boolean edicao = false;

	@EJB
	private UsuarioFacade dao;

	public UsuarioMB() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {

		if (!edicao) {
			// se tenho o usuario na session entro com edi�ao

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			Object logado = externalContext.getSessionMap().get("usuario");

			if (logado != null) {
				usuario = dao.consultar((Usuario) logado);
				edicao = true;
			}
		}
		return usuario;
	}

	public void setUsuario(Usuario u) {
		usuario = u;
	}

	public Boolean getLogado() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object logado = externalContext.getSessionMap().get("usuario");

		if (logado != null)
			return true;
		else
			return false;
	}

	public Boolean getAdmin() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Object logado = externalContext.getSessionMap().get("usuario");

		if (logado != null) {
			return ((Usuario) logado).getAdmin();
		} else
			return false;
	}

	public void salvar() {
		try {
			usuario.setCpf(usuario.getCpf().replace(".", "").replace("-", ""));
			usuario.setCep(usuario.getCep().replace(".", "").replace("-", ""));

			dao.salvar(usuario);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cadastro de Usu�rio: ", "Usu�rio salvo com sucesso!"));

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cadastro de Usu�rio: ", "E-mail e/ou CPF j� cadastrado!"));
		}
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void login() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();

		Usuario temp = dao.autenticar(this.usuario.getEmail(), this.usuario.getSenha());

		if (temp != null) {
			externalContext.getSessionMap().put("usuario", temp);
			externalContext.redirect("Index.xhtml");
		} else {
			context.addMessage(null, new FacesMessage("Ops!", "E-Mail ou Senha inv�lidos!"));
		}
	}

	public void logout() throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

		externalContext.invalidateSession();
		externalContext.redirect("Login.xhtml");
	}
}
