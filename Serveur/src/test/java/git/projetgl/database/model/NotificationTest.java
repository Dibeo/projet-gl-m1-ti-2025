package git.projetgl.database.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationTest {
    private AppUser receiver;
    private Notification notif;

    @BeforeEach
    void setUp() {
        this.receiver = new AppUser("Alice", "Ecila", "alice@mail.fr", "pwd123", "Paris");
        this.notif = new Notification(this.receiver, "notification content", NotificationType.OTHER);
    }

    @Test
    void testMessageConstructor() {
        assertEquals("notification content", notif.getContent());
        assertEquals(this.receiver, notif.getReceiver());
        assertEquals(NotificationType.OTHER, this.notif.getNotificationType());
    }

    @AfterEach
    void CleanSetUp() {
        notif = null;
        receiver = null;
    }
}