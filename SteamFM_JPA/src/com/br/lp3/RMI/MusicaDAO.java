package com.br.lp3.RMI;

import com.br.lp3.entities.Musica;
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
public class MusicaDAO implements GenericoDAO<Musica> {

    EntityManagerFactory emf;
    EntityManager em;

    public MusicaDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Musica e) {
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
        List<Musica> listaMusica = em.createNamedQuery("Musica.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaMusica;
    }

    @Override
    public void update(Musica e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Musica musica = em.find(Musica.class, e.getIdMusica());
        if (musica != null) {
            musica.setIdMusicaLastfm(e.getIdMusicaLastfm());
            musica.setTituloMusica(e.getTituloMusica());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Musica musica = em.find(Musica.class, id);
        if (musica != null) {
            em.remove(musica);
        }
        em.getTransaction().commit();
        em.close();
    }
    
}
