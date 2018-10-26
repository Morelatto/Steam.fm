package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.Relacao;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class RelacaoDAO implements DAO<Relacao> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Relacao> get(long id) {
        return Optional.ofNullable(entityManager.find(Relacao.class, id));
    }

    @Override
    public List<Relacao> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Relacao e");
        return query.getResultList();
    }

    @Override
    public void save(Relacao relacao) {
        execute(entityManager -> entityManager.persist(relacao));
    }

    @Override
    public void update(Relacao relacao) {
        execute(entityManager -> entityManager.merge(relacao));
    }

    @Override
    public void delete(Relacao relacao) {
        execute(entityManager -> entityManager.remove(relacao));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
