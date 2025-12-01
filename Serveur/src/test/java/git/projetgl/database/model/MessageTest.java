package git.projetgl.database.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageTest {
    private AppUser sender;
    private AppUser receiver;
    private Message message;

    @BeforeEach
    void setUp() {
        this.sender = new AppUser("Alice", "Ecila", "alice@mail.com", "pwd123", "Paris");
        this.receiver = new AppUser("Bob", "Bob", "bob@mail.fr", "pwd456", "Paris");
        this.message = new Message(this.sender, this.receiver, "message content");
    }

    @Test
    void testMessageConstructor() {
        assertEquals("message content", message.getContent());
        assertEquals(this.sender, message.getSender());
        assertEquals(this.receiver, message.getReceiver());
    }

    @AfterEach
    void CleanSetUp() {
        message = null;
        receiver = null;
        sender = null;
    }
}