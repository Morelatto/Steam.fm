package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.GameGenreToMusicRelease;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class GameGenreToMusicReleaseDAO implements DAO<GameGenreToMusicRelease> {

    private EntityManager entityManager;

    GameGenreToMusicReleaseDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<GameGenreToMusicRelease> get(Long id) {
        return Optional.ofNullable(entityManager.find(GameGenreToMusicRelease.class, id));
    }

    @Override
    public List<GameGenreToMusicRelease> getAll() {
        Query query = entityManager.createQuery("SELECT ggtmr FROM GameGenreToMusicRelease ggtmr");
        return query.getResultList();
    }

    @Override
    public GameGenreToMusicRelease save(GameGenreToMusicRelease gameGenreToMusicRelease) {
        execute(entityManager -> entityManager.persist(gameGenreToMusicRelease));
        return gameGenreToMusicRelease;
    }

    @Override
    public void update(GameGenreToMusicRelease gameGenreToMusicRelease) {
        execute(entityManager -> entityManager.merge(gameGenreToMusicRelease));
    }

    @Override
    public void delete(GameGenreToMusicRelease gameGenreToMusicRelease) {
        execute(entityManager -> entityManager.remove(gameGenreToMusicRelease));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
