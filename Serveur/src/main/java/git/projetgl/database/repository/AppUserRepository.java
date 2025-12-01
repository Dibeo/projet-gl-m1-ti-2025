package git.projetgl.database.repository;

import git.projetgl.database.model.AppUser;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;

import java.util.List;

public class AppUserRepository extends AbstractRepository {

    public AppUserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public AppUser findById(Long id) {
        return execute(em -> em.find(AppUser.class, id));
    }

    public List<AppUser> findAll() {
        return execute(em -> em.createQuery("FROM AppUser", AppUser.class).getResultList());
    }

    public AppUser save(AppUser user) {
        return execute(em -> {
            if (user.getId() == null) {
                em.persist(user);
                return user;
            } else {
                return em.merge(user);
            }
        });
    }

    public void delete(Long id) {
        executeVoid(em -> {
            AppUser user = em.find(AppUser.class, id);
            if (user != null) {
                em.remove(user);
            }
        });
    }
}
