package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.Album;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class AlbumDAO implements DAO<Album> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Album> get(long id) {
        return Optional.ofNullable(entityManager.find(Album.class, id));
    }

    @Override
    public List<Album> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Album e");
        return query.getResultList();
    }

    @Override
    public void save(Album album) {
        execute(entityManager -> entityManager.persist(album));
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
