package br.com.lp3.rmi.dao;

import br.com.lp3.entities.Artista;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class ArtistaDAO implements GenericoDAO<Artista> {

    EntityManagerFactory emf;
    EntityManager em;

    public ArtistaDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Artista e) {
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
        List<Artista> listaArtista = em.createNamedQuery("Artista.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaArtista;
    }

    @Override
    public void update(Artista e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Artista artista = em.find(Artista.class, e.getIdArtista());
        if (artista != null) {
            artista.setIdArtistaLastfm(e.getIdArtistaLastfm());
            artista.setNomeArtista(e.getNomeArtista());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Artista artista = em.find(Artista.class, id);
        if (artista != null) {
            em.remove(artista);
        }
        em.getTransaction().commit();
        em.close();
    }

}
