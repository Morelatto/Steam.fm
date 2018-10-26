package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.entities.Musica;
import br.com.lp3.dao.DAO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


public class MusicaDAO implements DAO<Musica> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Musica> get(long id) {
        return Optional.ofNullable(entityManager.find(Musica.class, id));
    }

    @Override
    public List<Musica> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Musica e");
        return query.getResultList();
    }
    @Override
    public void save(Musica musica) {
        execute(entityManager -> entityManager.persist(musica));
    }
    @Override
    public void update(Musica musica) {
        execute(entityManager -> entityManager.merge(musica));
    }

    @Override
    public void delete(Musica musica) {
        execute(entityManager -> entityManager.remove(musica));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
