package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.Album;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class AlbumDAO implements DAO<Album> {

    // TODO close connection
    private EntityManager entityManager;

    AlbumDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Album> get(Long id) {
        return Optional.ofNullable(entityManager.find(Album.class, id));
    }

    @Override
    public List<Album> getAll() {
        Query query = entityManager.createQuery("SELECT a FROM Album a");
        return query.getResultList();
    }

    @Override
    public Album save(Album album) {
        execute(entityManager -> entityManager.persist(album));
        return album;
    }

    @Override
    public void update(Album album) {
        execute(entityManager -> entityManager.merge(album));
    }

    @Override
    public void delete(Album album) {
        execute(entityManager -> entityManager.remove(album));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
