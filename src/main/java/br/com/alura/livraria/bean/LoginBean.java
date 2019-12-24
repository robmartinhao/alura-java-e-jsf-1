package br.com.alura.livraria.bean;

import br.com.alura.livraria.dao.UsuarioDao;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

    public String efetuaLogin(){
        System.out.println("Fazendo login do usuario " + this.usuario.getSenha());

        boolean existe = new UsuarioDao().existe(this.usuario);
        if(existe) {
            return "livro?faces-redirect=true";
        }
        return "login?faces-redirect=true";
    }
}
