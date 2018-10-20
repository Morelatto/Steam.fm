package br.com.lp3.rmi.dao;

import br.com.lp3.entities.Relacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author Leandro Meneguzzi - 3144893-3
 * @author Lucas Gianfrancesco - 3147173-0
 * @author Pedro Morelatto - 3142463-5
 */
public class RelacaoDAO implements GenericoDAO<Relacao> {

    EntityManagerFactory emf;
    EntityManager em;

    public RelacaoDAO() {
        emf = Persistence.createEntityManagerFactory("SteamFM_EntitiesPU");
        em = emf.createEntityManager();
    }

    @Override
    public void create(Relacao e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Relacao> read() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Relacao> listaRelacao = em.createNamedQuery("Relacao.findAll").getResultList();
        em.getTransaction().commit();
        em.close();
        return listaRelacao;
    }

    @Override
    public void update(Relacao e) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Relacao relacao = em.find(Relacao.class, e.getIdRelacao());
        if (relacao != null) {
            relacao.setIdArtista(e.getIdArtista());
            relacao.setIdGeneroJogo(e.getIdGeneroJogo());
            relacao.setIdMusica(e.getIdMusica());
            relacao.setIdAlbum(e.getIdAlbum());
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(int id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Relacao relacao = em.find(Relacao.class, id);
        if (relacao != null) {
            em.remove(relacao);
        }
        em.getTransaction().commit();
        em.close();
    }

}
