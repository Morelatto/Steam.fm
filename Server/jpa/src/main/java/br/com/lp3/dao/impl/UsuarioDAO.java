package br.com.lp3.dao.impl;

import br.com.lp3.PersistenceUtils;
import br.com.lp3.dao.DAO;
import br.com.lp3.entities.Usuario;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class UsuarioDAO implements DAO<Usuario> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Usuario> get(long id) {
        return Optional.ofNullable(entityManager.find(Usuario.class, id));
    }

    @Override
    public List<Usuario> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Usuario e");
        return query.getResultList();
    }

    @Override
    public void save(Usuario usuario) {
        execute(entityManager -> entityManager.persist(usuario));
    }

    @Override
    public void update(Usuario usuario) {
        execute(entityManager -> entityManager.merge(usuario));
    }

    @Override
    public void delete(Usuario usuario) {
        execute(entityManager -> entityManager.remove(usuario));
    }

    private void execute(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        PersistenceUtils.executeInsideTransaction(action, tx, entityManager);
    }

}
