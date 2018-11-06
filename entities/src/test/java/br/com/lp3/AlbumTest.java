package br.com.lp3;

import br.com.lp3.entities.Album;
import br.com.lp3.entities.MusicReleaseAndGameMap;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

public class AlbumTest {

    @Test
    public void albumSaveTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("steamfm");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 1; i <= 1000; i++) {
            em.persist(new Album((long) i, //
                String.format("Title%d", i), //
                String.format("IdLastfm%d", i), //
                String.format("Image%d", i), //
                String.format("Desc%d", i), //
                String.format("Url%d", i), //
                Collections.<MusicReleaseAndGameMap> emptyList()));
        }
        em.getTransaction().commit();

        Query q1 = em.createQuery("SELECT COUNT(a) FROM Album a");
        Assert.assertEquals(1000L, q1.getSingleResult());
    }

}
