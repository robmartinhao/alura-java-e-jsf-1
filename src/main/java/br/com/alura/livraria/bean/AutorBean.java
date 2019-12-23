package br.com.alura.livraria.bean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
public class AutorBean {

    private Autor autor = new Autor();

    private Integer autorId;

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public void carregarAutorPeloId() {
        this.autor = new DAO<>(Autor.class).buscaPorId(autorId);
    }

    public Autor getAutor() {
        return autor;
    }

    public List<Autor> getAutores() {
        return new DAO<>(Autor.class).listaTodos();
    }

    public String formLivro() {
        System.out.println("Chamando o formulario do Livro");
        return "livro?faces-redirect=true";
    }

    public void carregar(Autor autor) {
        System.out.println("Carregando o livro " + autor.getNome());
        this.autor = autor;
    }

    public void remover(Autor autor) {
        System.out.println("Removendo autor " + autor.getNome());
        new DAO<>(Autor.class).remove(autor);
    }

    public void gravar() {
        System.out.println("Gravando autor " + this.autor.getNome());

        if (this.autor.getId() == null) {
            new DAO<>(Autor.class).adiciona(this.autor);
        } else {
            new DAO<>(Autor.class).atualiza(this.autor);
        }

        this.autor = new Autor();
    }
}
