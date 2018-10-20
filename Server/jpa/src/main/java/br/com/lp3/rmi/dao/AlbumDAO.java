package br.com.lp3.rmi.dao;

import br.com.lp3.entities.Album;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class AlbumDAO implements GenericoDAO<Album> {

    EntityManagerFactory emf;
    EntityManager em;

    public AlbumDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Album e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Album> read() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Album> listaAlbum = em.createNamedQuery("Album.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaAlbum;
    }

    @Override
    public void update(Album e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Album album = em.find(Album.class, e.getIdAlbum());
        if (album != null) {
            album.setIdAlbumLastfm(e.getIdAlbumLastfm());
            album.setTituloAlbum(e.getTituloAlbum());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Album album = em.find(Album.class, id);
        if (album != null) {
            em.remove(album);
        }
        em.getTransaction().commit();
        em.close();
    }

}
