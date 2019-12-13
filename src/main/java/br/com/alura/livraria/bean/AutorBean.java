package br.com.alura.livraria.bean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AutorBean {

    private Autor autor = new Autor();

    public Autor getAutor() {
        return autor;
    }

    public String gravar() {
        System.out.println("Gravando autor " + this.autor.getNome());

        new DAO<Autor>(Autor.class).adiciona(this.autor);

        this.autor = new Autor();

        return "livro?faces-redirect=true";
    }
}
