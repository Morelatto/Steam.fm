package com.br.lp3.RMI;

import com.br.lp3.entities.GeneroJogo;
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
public class GeneroJogoDAO implements GenericoDAO<GeneroJogo> {

    EntityManagerFactory emf;
    EntityManager em;

    public GeneroJogoDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(GeneroJogo e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<GeneroJogo> read() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<GeneroJogo> listaGeneroJogo = em.createNamedQuery("GeneroJogo.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaGeneroJogo;
    }

    @Override
    public void update(GeneroJogo e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        GeneroJogo generoJogo = em.find(GeneroJogo.class, e.getIdGeneroJogo());
        if (generoJogo != null) {
            generoJogo.setNomeGenero(e.getNomeGenero());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        GeneroJogo generoJogo = em.find(GeneroJogo.class, id);
        if (generoJogo != null) {
            em.remove(generoJogo);
        }
        em.getTransaction().commit();
        em.close();
    }
    
}
