package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.GameGenre;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class GameGenreDAO implements DAO<GameGenre> {

    private EntityManager entityManager;

    GameGenreDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<GameGenre> get(long id) {
        return Optional.ofNullable(entityManager.find(GameGenre.class, id));
    }

    @Override
    public List<GameGenre> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM GeneroJogo e");
        return query.getResultList();
    }

    @Override
    public void save(GameGenre gameGenre) {
        execute(entityManager -> entityManager.persist(gameGenre));
    }

    @Override
    public void update(GameGenre gameGenre) {
        execute(entityManager -> entityManager.merge(gameGenre));
    }

    @Override
    public void delete(GameGenre gameGenre) {
        execute(entityManager -> entityManager.remove(gameGenre));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
