package git.projetgl.service;

import git.projetgl.database.model.AppUser;
import git.projetgl.database.repository.AppUserRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class AppUserService {

    private final AppUserRepository repository;

    public AppUserService(SessionFactory sessionFactory) {
        this.repository = new AppUserRepository(sessionFactory);
    }

    public List<AppUser> getAllUsers() {
        return repository.findAll();
    }

    public AppUser getUserById(Long id) {
        return repository.findById(id);
    }

    public AppUser createUser(AppUser user) {
        return repository.save(user);
    }

    public AppUser updateUser(Long id, AppUser user) {
        AppUser existing = repository.findById(id);
        if (existing != null) {
            existing.setFirstName(user.getFirstName());
            existing.setLastName(user.getLastName());
            existing.setEmail(user.getEmail());
            existing.setBio(user.getBio());
            existing.setLocation(user.getLocation());
            existing.setUserType(user.getUserType());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteUser(Long id) {
        repository.delete(id);
    }
}
