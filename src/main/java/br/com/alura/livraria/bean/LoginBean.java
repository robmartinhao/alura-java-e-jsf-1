package br.com.alura.livraria.bean;

import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Usuario;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Robson Martinhão on 24/12/2019
 **/

@ManagedBean
@ViewScoped
public class LoginBean {

    private Usuario usuario = new Usuario();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String efetuaLogin() {
        System.out.println("Fazendo login do usuario " + this.usuario.getSenha());

        FacesContext context = FacesContext.getCurrentInstance();

        boolean existe = new UsuarioDao().existe(this.usuario);
        if (existe) {
            context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
            return "livro?faces-redirect=true";
        }

        context.getExternalContext().getFlash().setKeepMessages(true);
        context.addMessage(null, new FacesMessage("Usuario nao encontrado"));

        return "login?faces-redirect=true";
    }

    public String deslogar() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("usuarioLogado");
        return "login?faces-redirect=true";
    }
}
