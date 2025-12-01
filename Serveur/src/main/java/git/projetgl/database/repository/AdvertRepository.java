package git.projetgl.database.repository;

import git.projetgl.database.model.Advert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AdvertRepository extends AbstractRepository {

    public AdvertRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Advert> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Advert", Advert.class).list();
        }
    }

    public Advert findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Advert.class, id);
        }
    }

    public Advert save(Advert advert) {
        executeVoid(session -> session.merge(advert));
        return advert;
    }

    public void delete(Long id) {
        executeVoid(session -> {
            Advert advert = session.find(Advert.class, id);
            if (advert != null) session.remove(advert);
        });
    }

    public List<Advert> search(String keyword, String category) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Advert a WHERE (:keyword = '' OR a.title LIKE :keyword OR a.description LIKE :keyword)" +
                    " AND (:category = '' OR a.advertType = :category)";
            return session.createQuery(hql, Advert.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .setParameter("category", category)
                    .list();
        }
    }
}
