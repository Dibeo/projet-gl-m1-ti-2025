package git.projetgl.database.service;

import git.projetgl.database.model.AppUser;
import git.projetgl.database.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public AppUser createUser(String firstName, String lastName, String email, String password, String location) {
        String hashedPassword = passwordService.hash(password);
        AppUser user = new AppUser(firstName, lastName, email, hashedPassword, location);
        return userRepository.save(user);
    }

    public boolean verifyPassword(AppUser user, String candidate) {
        return passwordService.verify(candidate, user.getPassword());
    }

    public Optional<AppUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.update(user);
    }

    public void deleteUser(AppUser user) {
        userRepository.delete(user);
    }
}
