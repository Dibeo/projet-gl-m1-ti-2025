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

    // DTO pour la création d'une application
    public static class ApplicationDTO {
        public Long userId;
        public Long advertId;
    }

    public void createApplication(Context ctx) {
        // Lire le DTO depuis le corps de la requête
        ApplicationDTO dto = ctx.bodyAsClass(ApplicationDTO.class);

        if (dto.userId == null || dto.advertId == null) {
            ctx.status(400).json(Map.of("error", "userId and advertId are required"));
            return;
        }

        applicationService.createApplicationFromIds(dto.userId, dto.advertId);

        ctx.status(201);
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
