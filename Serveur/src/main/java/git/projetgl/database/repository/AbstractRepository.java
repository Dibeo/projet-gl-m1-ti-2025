package git.projetgl.database.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.function.Function;

public abstract class AbstractRepository {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppDatabasePU");

    protected <R> R execute(Function<EntityManager, R> function) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            R result = function.apply(em);
            em.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    protected void executeVoid(java.util.function.Consumer<EntityManager> consumer) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
