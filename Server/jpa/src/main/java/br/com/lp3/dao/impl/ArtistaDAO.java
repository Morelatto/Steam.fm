package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.entities.Artista;
import br.com.lp3.dao.DAO;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ArtistaDAO implements DAO<Artista> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Artista> get(long id) {
        return Optional.ofNullable(entityManager.find(Artista.class, id));
    }

    @Override
    public List<Artista> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Artista e");
        return query.getResultList();
    }

    @Override
    public void save(Artista artista) {
        execute(entityManager -> entityManager.persist(artista));
    }

    @Override
    public void update(Artista artista) {
        execute(entityManager -> entityManager.merge(artista));
    }

    @Override
    public void delete(Artista artista) {
        execute(entityManager -> entityManager.remove(artista));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
