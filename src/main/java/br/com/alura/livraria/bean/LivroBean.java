package br.com.alura.livraria.bean;

import br.com.alura.livraria.dao.DAO;
import br.com.alura.livraria.modelo.Autor;
import br.com.alura.livraria.modelo.Livro;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import java.util.List;

@ManagedBean
@ViewScoped
public class LivroBean {

    private Livro livro = new Livro();
    private Integer autorId;

    public Livro getLivro() {
        return livro;
    }

    public List<Livro> getLivros() {
        return new DAO<Livro>(Livro.class).listaTodos();
    }

    public List<Autor> getAutores() {
        return new DAO<Autor>(Autor.class).listaTodos();
    }

    public List<Autor> getAutoresDoLivro() {
        return this.livro.getAutores();
    }

    public void gravarAutor() {
        Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.autorId);
        this.livro.adicionaAutor(autor);
        System.out.println("Livro escrito por: " + autor.getNome());
    }

    public void gravar() {
        System.out.println("Gravando livro " + this.livro.getTitulo());

        if (livro.getAutores().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
            return;
        }

        if (this.livro.getId() == null) {
            new DAO<Livro>(Livro.class).adiciona(this.livro);
        } else {
            new DAO<Livro>(Livro.class).atualiza(this.livro);
        }
    }

    public void remover(Livro livro) {
        System.out.println("Removendo o livro " + livro.getTitulo());
        new DAO<>(Livro.class).remove(livro);
    }

    public void carregar(Livro livro) {
        System.out.println("Carregando o livro " + livro.getTitulo());
        this.livro = livro;
    }

    public String formAutor() {
        System.out.println("Chamando o formulario do Autor");
        return "autor?faces-redirect=true";
    }

    public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
        String valor = value.toString();
        if (!valor.startsWith("1")) {
            throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
        }
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }
}
