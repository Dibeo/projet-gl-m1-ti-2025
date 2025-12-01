package git.projetgl.database.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {
    private AppUser user;

    @BeforeEach
    void setUp() {
        this.user = new AppUser("Alice", "Ecila", "alice@mail.com", "pwd123", "Paris");
    }

    @Test
    void testUserConstructor() {
        assertEquals("Alice", user.getFirstName());
        assertEquals("Ecila", user.getLastName());
        assertEquals("alice@mail.com", user.getEmail());
    }

    @AfterEach
    void CleanSetUp() {
        user = null;
    }
}