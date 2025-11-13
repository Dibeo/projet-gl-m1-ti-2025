package git.projetgl.database.service;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import git.projetgl.database.model.AppUser;
import git.projetgl.database.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public AppUser createUser(String firstName, String lastName, String email, String password, String location) {
        Argon2Function function = Argon2Function.getInstance(22, 65536, 1, 16, Argon2.ID);
        Hash hash = Password.hash(password).addRandomSalt().with(function);

        AppUser user = new AppUser(firstName, lastName, email, hash.getResult(), location);
        return userRepository.save(user);
    }

    public boolean verifyPassword(AppUser user, String candidate) {
        return Password.check(candidate, user.getPassword()).withArgon2();
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