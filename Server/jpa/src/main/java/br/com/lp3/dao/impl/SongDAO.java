package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.entities.Song;
import br.com.lp3.dao.DAO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class SongDAO implements DAO<Song> {

    private EntityManager entityManager;

    SongDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Song> get(Long id) {
        return Optional.ofNullable(entityManager.find(Song.class, id));
    }

    @Override
    public List<Song> getAll() {
        Query query = entityManager.createQuery("SELECT s FROM Song s");
        return query.getResultList();
    }

    @Override
    public Song save(Song song) {
        execute(entityManager -> entityManager.persist(song));
        return song;
    }

    @Override
    public void update(Song song) {
        execute(entityManager -> entityManager.merge(song));
    }

    @Override
    public void delete(Song song) {
        execute(entityManager -> entityManager.remove(song));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
