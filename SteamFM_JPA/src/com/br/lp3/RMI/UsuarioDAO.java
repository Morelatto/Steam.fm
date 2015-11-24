package com.br.lp3.RMI;

import com.br.lp3.entities.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class UsuarioDAO implements GenericoDAO<Usuario> {

    EntityManagerFactory emf;
    EntityManager em;

    public UsuarioDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Usuario e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List read() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Usuario> listaUsuario = em.createNamedQuery("Usuario.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaUsuario;
    }

    @Override
    public void update(Usuario e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, e.getIdUsuario());
        if (usuario != null) {
            usuario.setLogin(e.getLogin());
            usuario.setSenha(e.getSenha());
            usuario.setUsuarioSteam(e.getUsuarioSteam());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.remove(usuario);
        }
        em.getTransaction().commit();
        em.close();
    }

}
