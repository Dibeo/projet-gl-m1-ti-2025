package git.projetgl.database.service;

import git.projetgl.database.HibernateTestUtil;
import git.projetgl.database.model.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import git.projetgl.service.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppUserServiceTest {

    private static AppUserService userService;

    private static AppUser alice;
    private static AppUser bob;

    @BeforeAll
    static void setup() {
        SessionFactory sessionFactory = HibernateTestUtil.getSessionFactory();
        userService = new AppUserService(sessionFactory);

        alice = new AppUser("Alice", "Wonderland", "alice@example.com", "password", "Paris", "Bio d'Alice");
        bob = new AppUser("Bob", "Builder", "bob@example.com", "password", "Lyon", "Bio de Bob");

    }

    @Test
    @Order(1)
    void testCreateUser() {
        AppUser savedAlice = userService.createUser(alice);
        AppUser savedBob = userService.createUser(bob);
        assertNotNull(savedAlice.getId());
        assertNotNull(savedBob.getId());
    }

    @Test
    @Order(2)
    void testGetAllUsers() {
        List<AppUser> users = userService.getAllUsers();
        assertTrue(users.size() >= 2);
    }

    @Test
    @Order(3)
    void testGetUserById() {
        AppUser user = userService.getUserById(alice.getId());
        assertEquals("Alice", user.getFirstName());
    }

    @Test
    @Order(4)
    void testUpdateUser() {
        alice.setBio("Bonjour, je suis Alice !");
        AppUser updated = userService.updateUser(alice.getId(), alice);
        assertEquals("Bonjour, je suis Alice !", updated.getBio());
    }

    @Test
    @Order(5)
    void testDeleteUser() {
        userService.deleteUser(alice.getId());

        AppUser deleted = userService.getUserById(alice.getId());
        assertNull(deleted, "L'utilisateur doit être supprimé");
    }

}
