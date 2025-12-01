package git.projetgl.api.handlers;

import git.projetgl.database.model.Application;
import git.projetgl.service.ApplicationService;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class ApplicationHandler {

    private final ApplicationService applicationService;

    public ApplicationHandler(SessionFactory sessionFactory) {
        this.applicationService = new ApplicationService(sessionFactory);
    }

    public void getAllApplications(Context ctx) {
        List<Application> applications = applicationService.getAllApplications();
        ctx.json(applications);
    }

    public void getApplicationById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Application application = applicationService.getApplicationById(id);
        if (application != null) {
            ctx.json(application);
        } else {
            ctx.status(404).json(Map.of("error", "Application not found"));
        }
    }

    public void createApplication(Context ctx) {
        Application application = ctx.bodyAsClass(Application.class);
        Application created = applicationService.createApplication(application);
        ctx.status(201).json(created);
    }

    public void acceptApplication(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        applicationService.acceptApplication(id);
        ctx.status(204);
    }

    public void rejectApplication(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        applicationService.rejectApplication(id);
        ctx.status(204);
    }

    public void deleteApplication(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        applicationService.deleteApplication(id);
        ctx.status(204);
    }
}
