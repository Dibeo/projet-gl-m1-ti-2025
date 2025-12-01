package git.projetgl.database.service;

import com.password4j.Argon2Function;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Argon2;

public class PasswordService {

    private final Argon2Function function = Argon2Function.getInstance(22, 65536, 1, 16, Argon2.ID);

    public String hash(String plainPassword) {
        Hash hash = Password.hash(plainPassword).addRandomSalt().with(function);
        return hash.getResult();
    }

    public boolean verify(String plainPassword, String hashedPassword) {
        return Password.check(plainPassword, hashedPassword).withArgon2();
    }
}
