package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.entities.Artist;
import br.com.lp3.dao.DAO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class ArtistDAO implements DAO<Artist> {

    private EntityManager entityManager;

    ArtistDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Artist> get(Long id) {
        return Optional.ofNullable(entityManager.find(Artist.class, id));
    }

    @Override
    public List<Artist> getAll() {
        Query query = entityManager.createQuery("SELECT a FROM Artist a");
        return query.getResultList();
    }

    @Override
    public void save(Artist artist) {
        execute(entityManager -> entityManager.persist(artist));
    }

    @Override
    public void update(Artist artist) {
        execute(entityManager -> entityManager.merge(artist));
    }

    @Override
    public void delete(Artist artist) {
        execute(entityManager -> entityManager.remove(artist));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
