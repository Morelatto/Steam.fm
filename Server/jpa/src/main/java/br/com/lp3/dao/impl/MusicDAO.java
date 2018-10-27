package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.entities.Music;
import br.com.lp3.dao.DAO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class MusicDAO implements DAO<Music> {

    private EntityManager entityManager;

    MusicDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Music> get(long id) {
        return Optional.ofNullable(entityManager.find(Music.class, id));
    }

    @Override
    public List<Music> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Musica e");
        return query.getResultList();
    }
    @Override
    public void save(Music music) {
        execute(entityManager -> entityManager.persist(music));
    }
    @Override
    public void update(Music music) {
        execute(entityManager -> entityManager.merge(music));
    }

    @Override
    public void delete(Music music) {
        execute(entityManager -> entityManager.remove(music));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
