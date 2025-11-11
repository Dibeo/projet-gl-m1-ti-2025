package git.projetgl.database.repository;

import git.projetgl.database.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    public User save(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    public Optional<User> findById(Long id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return Optional.ofNullable(user);
    }

    public List<User> findAll() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("FROM User", User.class).getResultList();
        em.close();
        return users;
    }

    public User update(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User updated = em.merge(user);
        em.getTransaction().commit();
        em.close();
        return updated;
    }

    public void delete(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User managed = em.merge(user);
        em.remove(managed);
        em.getTransaction().commit();
        em.close();
    }
}
