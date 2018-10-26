package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.GeneroJogo;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class GeneroJogoDAO implements DAO<GeneroJogo> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<GeneroJogo> get(long id) {
        return Optional.ofNullable(entityManager.find(GeneroJogo.class, id));
    }

    @Override
    public List<GeneroJogo> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM GeneroJogo e");
        return query.getResultList();
    }

    @Override
    public void save(GeneroJogo generoJogo) {
        execute(entityManager -> entityManager.persist(generoJogo));
    }

    @Override
    public void update(GeneroJogo generoJogo) {
        execute(entityManager -> entityManager.merge(generoJogo));
    }

    @Override
    public void delete(GeneroJogo generoJogo) {
        execute(entityManager -> entityManager.remove(generoJogo));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
