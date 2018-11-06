package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.SystemUser;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static br.com.lp3.PersistenceUtils.PERSISTENCE_UNIT_NAME;

public class SystemUserDAO implements DAO<SystemUser> {

    private EntityManager entityManager;

    SystemUserDAO() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<SystemUser> get(long id) {
        return Optional.ofNullable(entityManager.find(SystemUser.class, id));
    }

    @Override
    public List<SystemUser> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Usuario e");
        return query.getResultList();
    }

    @Override
    public void save(SystemUser systemUser) {
        execute(entityManager -> entityManager.persist(systemUser));
    }

    @Override
    public void update(SystemUser systemUser) {
        execute(entityManager -> entityManager.merge(systemUser));
    }

    @Override
    public void delete(SystemUser systemUser) {
        execute(entityManager -> entityManager.remove(systemUser));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
