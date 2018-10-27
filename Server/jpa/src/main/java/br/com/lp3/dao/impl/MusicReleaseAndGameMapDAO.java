package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class MusicReleaseAndGameMapDAO implements DAO<MusicReleaseAndGameMap> {

    private EntityManager entityManager;

    MusicReleaseAndGameMapDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<MusicReleaseAndGameMap> get(long id) {
        return Optional.ofNullable(entityManager.find(MusicReleaseAndGameMap.class, id));
    }

    @Override
    public List<MusicReleaseAndGameMap> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Relacao e");
        return query.getResultList();
    }

    @Override
    public void save(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        execute(entityManager -> entityManager.persist(musicReleaseAndGameMap));
    }

    @Override
    public void update(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        execute(entityManager -> entityManager.merge(musicReleaseAndGameMap));
    }

    @Override
    public void delete(MusicReleaseAndGameMap musicReleaseAndGameMap) {
        execute(entityManager -> entityManager.remove(musicReleaseAndGameMap));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
