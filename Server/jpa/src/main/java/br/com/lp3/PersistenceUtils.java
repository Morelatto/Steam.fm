package br.com.lp3;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Consumer;

public class PersistenceUtils {

    public static void executeInsideTransaction(Consumer<EntityManager> action, EntityTransaction tx, EntityManager entityManager) {
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
