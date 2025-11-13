package git.projetgl.database.repository;

import git.projetgl.database.model.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public AppUser save(AppUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public Optional<AppUser> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        AppUser user = em.find(AppUser.class, id);
        em.close();
        return Optional.ofNullable(user);
    }

    public List<AppUser> findAll() {
        EntityManager em = emf.createEntityManager();
        List<AppUser> users = em.createQuery("FROM User", AppUser.class).getResultList();
        em.close();
        return users;
    }

    public AppUser update(AppUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AppUser updated = em.merge(user);
        em.getTransaction().commit();
        em.close();
        return updated;
    }

    public void delete(AppUser user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AppUser managed = em.merge(user);
        em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }
}
