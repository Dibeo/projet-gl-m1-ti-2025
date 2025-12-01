package git.projetgl.service;

import git.projetgl.database.model.Advert;
import git.projetgl.database.repository.AdvertRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class AdvertService {

    private final AdvertRepository repository;

    public AdvertService(SessionFactory sessionFactory) {
        this.repository = new AdvertRepository(sessionFactory);
    }

    public List<Advert> getAllAdverts() {
        return repository.findAll();
    }

    public Advert getAdvertById(Long id) {
        return repository.findById(id);
    }

    public Advert createAdvert(Advert advert) {
        return repository.save(advert);
    }

    public Advert updateAdvert(Long id, Advert advert) {
        Advert existing = repository.findById(id);
        if (existing != null) {
            existing.setTitle(advert.getTitle());
            existing.setDesc(advert.getDesc());
            existing.setAdvertType(advert.getAdvertType());
            existing.setAdvertStatus(advert.getAdvertStatus());
            return repository.save(existing);
        }
        return null;
    }

    public void deleteAdvert(Long id) {
        repository.delete(id);
    }

    public List<Advert> searchAdverts(String keyword, String category) {
        return repository.search(keyword, category);
    }
}
