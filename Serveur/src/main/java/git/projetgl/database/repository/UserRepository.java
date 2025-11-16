package git.projetgl.database.repository;

import git.projetgl.database.model.AppUser;

import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository {

    public AppUser save(AppUser user) {
        return execute(em -> {
            em.persist(user);
            return user;
        });
    }

    public Optional<AppUser> findById(Long id) {
        return execute(em -> Optional.ofNullable(em.find(AppUser.class, id)));
    }

    public List<AppUser> findAll() {
        return execute(em -> em.createQuery("FROM AppUser", AppUser.class).getResultList());
    }

    public AppUser update(AppUser user) {
        return execute(em -> em.merge(user));
    }

    public void delete(AppUser user) {
        executeVoid(em -> {
            AppUser managed = em.merge(user);
            em.remove(managed);
        });
    }
}
