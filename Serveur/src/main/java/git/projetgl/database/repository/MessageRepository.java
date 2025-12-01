package git.projetgl.database.repository;

import git.projetgl.database.model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MessageRepository extends AbstractRepository {

    public MessageRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Message> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Message", Message.class).list();
        }
    }

    public Message findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Message.class, id);
        }
    }

    public Message save(Message message) {
        executeVoid(session -> session.merge(message));
        return message;
    }

    public void delete(Long id) {
        executeVoid(session -> {
            Message message = session.find(Message.class, id);
            if (message != null) session.remove(message);
        });
    }

    public List<Message> findConversation(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Message m WHERE m.sender.id = :uid OR m.receiver.id = :uid ORDER BY m.id ASC";
            return session.createQuery(hql, Message.class)
                    .setParameter("uid", userId)
                    .list();
        }
    }
}
