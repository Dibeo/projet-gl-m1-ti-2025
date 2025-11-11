package git.projetgl.database.service;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;
import git.projetgl.database.model.User;
import git.projetgl.database.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public User createUser(String firstName, String lastName, String email, String password, String location) {
        Argon2Function function = Argon2Function.getInstance(22, 65536, 1, 16, Argon2.ID);
        Hash hash = Password.hash(password).addRandomSalt().with(function);

        User user = new User(firstName, lastName, email, hash.getResult(), location);
        return userRepository.save(user);
    }

    public boolean verifyPassword(User user, String candidate) {
        return Password.check(candidate, user.getPassword()).withArgon2();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        return userRepository.update(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}