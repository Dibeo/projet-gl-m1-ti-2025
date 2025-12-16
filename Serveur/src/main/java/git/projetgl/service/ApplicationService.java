package git.projetgl.service;

import git.projetgl.database.model.Application;
import git.projetgl.database.repository.ApplicationRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class ApplicationService {

    private final ApplicationRepository repository;

    public ApplicationService(SessionFactory sessionFactory) {
        this.repository = new ApplicationRepository(sessionFactory);
    }

    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    public Application getApplicationById(Long id) {
        return repository.findById(id);
    }

    public Application createApplication(Application application) {
        return repository.save(application);
    }

    public void acceptApplication(Long id) {
        Application application = repository.findById(id);
        if (application != null) {
            //application.setStatus(true); // ou un enum APPROVED
            repository.save(application);
        }
    }

    public void rejectApplication(Long id) {
        Application application = repository.findById(id);
        if (application != null) {
            //application.setStatus(false); // ou REJECTED
            repository.save(application);
        }
    }

    public void deleteApplication(Long id) {
        repository.delete(id);
    }

    public void createApplicationFromIds(Long userId, Long advertId) {
        repository.createFromIds(userId, advertId);
    }
}
