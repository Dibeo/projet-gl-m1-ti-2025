package git.projetgl.service;

import git.projetgl.database.model.Message;
import git.projetgl.database.repository.MessageRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class MessageService {

    private final MessageRepository repository;

    public MessageService(SessionFactory sessionFactory) {
        this.repository = new MessageRepository(sessionFactory);
    }

    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    public Message getMessageById(Long id) {
        return repository.findById(id);
    }

    public Message sendMessage(Message message) {
        return repository.save(message);
    }

    public List<Message> getConversationWithUser(Long userId) {
        return repository.findConversation(userId);
    }
}
