package git.projetgl.api.handlers;

import git.projetgl.database.model.AppUser;
import git.projetgl.service.AppUserService;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class AppUserHandler {

    private final AppUserService appUserService;

    public AppUserHandler(SessionFactory sessionFactory) {
        this.appUserService = new AppUserService(sessionFactory);
    }

    public void getAllUsers(Context ctx) {
        List<AppUser> users = appUserService.getAllUsers();
        ctx.json(users);
    }

    public void getUserById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        AppUser user = appUserService.getUserById(id);
        if (user != null) {
            ctx.json(user);
        } else {
            ctx.status(404).json(Map.of("error", "User not found"));
        }
    }

    public void createUser(Context ctx) {
        try {
            AppUser user = ctx.bodyAsClass(AppUser.class);
            AppUser created = appUserService.createUser(user);
            ctx.status(201).json(created);
        } catch (Exception e){
            ctx.status(400);
        }

    }

    public void updateUser(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        AppUser user = ctx.bodyAsClass(AppUser.class);
        AppUser updated = appUserService.updateUser(id, user);
        if (updated != null) {
            ctx.json(updated);
        } else {
            ctx.status(404).json(Map.of("error", "User not found"));
        }
    }

    public void deleteUser(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        appUserService.deleteUser(id);
        ctx.status(204);
    }
}
