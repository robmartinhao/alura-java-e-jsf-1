package br.com.alura.livraria.dao;

import br.com.alura.livraria.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Created by Robson Martinhão on 24/12/2019
 **/

public class UsuarioDao {

    public boolean existe(Usuario usuario) {

        EntityManager em = new JPAUtil().getEntityManager();
        TypedQuery<Usuario> query = em.createQuery(
                "select u from Usuario u " +
                        "where u.email =:pEmail and u.senha =:pSenha",
                Usuario.class);
        query.setParameter("pEmail", usuario.getEmail());
        query.setParameter("pSenha", usuario.getSenha());
        try {
            Usuario resultado = query.getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        em.close();

        return true;
    }
}
