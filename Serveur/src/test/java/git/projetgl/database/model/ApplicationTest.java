package git.projetgl.database.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest {
    private AppUser user;
    private Advert advert;
    private Application application;

    @BeforeEach
    void setUp() {
        this.user = new AppUser("Alice", "Ecila", "alice@mail.com", "pwd123", "Paris");
        this.advert = new Advert("advert title", "advert description", AdvertType.COMPETENCE, this.user);
        this.application = new Application(this.advert, this.user);
    }

    @Test
    void testAdvertConstructor() {
        assertEquals(user, this.application.getApplicant());
        assertEquals(advert, this.application.getAdvert());
    }

    @AfterEach
    void CleanSetUp() {
        advert = null;
        user = null;
    }
}