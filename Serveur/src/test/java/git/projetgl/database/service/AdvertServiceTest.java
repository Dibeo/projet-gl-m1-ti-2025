package git.projetgl.database.service;

import git.projetgl.database.HibernateTestUtil;
import git.projetgl.database.model.*;
import git.projetgl.service.AdvertService;
import git.projetgl.service.AppUserService;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdvertServiceTest {

    private static AdvertService advertService;
    private static AppUserService userService;

    private static AppUser alice;
    private static Advert advert;

    @BeforeAll
    static void setup() {
        SessionFactory sessionFactory = HibernateTestUtil.getSessionFactory();
        advertService = new AdvertService(sessionFactory);
        userService = new AppUserService(sessionFactory);

        alice = new AppUser(
                "Alice",
                "Wonderland",
                "alice.advert@test.com",
                "password",
                "Paris",
                "Bio Alice"
        );

        alice = userService.createUser(alice);
        assertNotNull(alice.getId());
    }

    @Test
    @Order(1)
    void testCreateAdvert() {
        advert = new Advert(
                "Cours Java",
                "Cours pour débutants",
                AdvertType.COMPETENCE,
                alice
        );

        advertService.createAdvert(advert);

        List<Advert> adverts = advertService.getAllAdverts();
        assertFalse(adverts.isEmpty());

        advert = adverts.get(0);
        assertNotNull(advert.getId());
    }

    @Test
    @Order(2)
    void testGetAdvertById() {
        Advert found = advertService.getAdvertById(advert.getId());
        assertNotNull(found);
        assertEquals("Cours Java", found.getTitle());
    }

    @Test
    @Order(3)
    void testUpdateAdvert() {
        advert.setTitle("Cours Java Avancé");
        advert.setAdvertStatus(Status.ACCEPTED);

        advertService.updateAdvert(advert.getId(), advert);

        Advert updated = advertService.getAdvertById(advert.getId());
        assertEquals("Cours Java Avancé", updated.getTitle());
        assertEquals(Status.ACCEPTED, updated.getAdvertStatus());
    }

    @Test
    @Order(4)
    void testDeleteAdvert() {
        advertService.deleteAdvert(advert.getId());
        Advert deleted = advertService.getAdvertById(advert.getId());
        assertNull(deleted);
    }
}
