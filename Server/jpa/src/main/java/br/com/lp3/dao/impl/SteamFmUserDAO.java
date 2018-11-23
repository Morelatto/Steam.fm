package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.SteamFmUser;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class SteamFmUserDAO implements DAO<SteamFmUser> {

    private EntityManager entityManager;

    SteamFmUserDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<SteamFmUser> get(Long id) {
        return Optional.ofNullable(entityManager.find(SteamFmUser.class, id));
    }

    @Override
    public List<SteamFmUser> getAll() {
        Query query = entityManager.createQuery("SELECT sfu FROM SteamFmUser sfu");
        return query.getResultList();
    }

    @Override
    public void save(SteamFmUser steamFmUser) {
        execute(entityManager -> entityManager.persist(steamFmUser));
    }

    @Override
    public void update(SteamFmUser steamFmUser) {
        execute(entityManager -> entityManager.merge(steamFmUser));
    }

    @Override
    public void delete(SteamFmUser steamFmUser) {
        execute(entityManager -> entityManager.remove(steamFmUser));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
