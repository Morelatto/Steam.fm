package br.com.lp3;

import br.com.lp3.entities.Album;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;

public class AlbumTest {

    @Test
    public void albumSaveTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("steam_fm_test");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 1; i <= 1000; i++) {
            em.persist(Album
                    .builder()
                    .description(String.format("Desc%d", i))
                    .image(String.format("Image%d", i))
                    .lastFmId(String.format("IdLastfm%d", i))
                    .name(String.format("Title%d", i))
                    .url(String.format("Url%d", i))
                    .build());
        }
        em.getTransaction().commit();

        Query q1 = em.createQuery("SELECT COUNT(a) FROM Album a");
        Assert.assertEquals(1000L, q1.getSingleResult());
    }

}
