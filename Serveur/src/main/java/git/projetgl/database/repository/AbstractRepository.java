package git.projetgl.database.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractRepository {

    protected SessionFactory sessionFactory;

    public AbstractRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <R> R execute(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            R result = function.apply(session);
            session.getTransaction().commit();
            return result;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    protected void executeVoid(Consumer<Session> consumer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            consumer.accept(session);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
