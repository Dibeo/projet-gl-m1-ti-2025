package git.projetgl.database.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvertTest {
    private AppUser user;
    private Advert advert;

    @BeforeEach
    void setUp() {
        this.user = new AppUser("Alice", "Ecila", "alice@mail.com", "pwd123", "Paris");
        this.advert = new Advert("advert title", "advert description", AdvertType.COMPETENCE, this.user);
    }

    @Test
    void testAdvertConstructor() {
        assertEquals("advert title", advert.getTitle());
        assertEquals("advert description", advert.getDesc());
        assertEquals(AdvertType.COMPETENCE, advert.getAdvertType());
        assertEquals(AdvertStatus.WAITING, advert.getAdvertStatus());
    }

    @AfterEach
    void CleanSetUp() {
        advert = null;
        user = null;
    }
}