package git.projetgl.api.handlers;

import git.projetgl.database.model.AppUser;
import git.projetgl.database.service.UserService;
import io.javalin.http.Context;

import java.util.List;
import java.util.Optional;

public class UserHandler {

    private static UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    // GET /users
    public void getAllUsers(Context ctx) {
        List<AppUser> users = userService.getAllUsers();
        ctx.json(users);
    }

    // GET /users/:id
    public void getUserById(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Optional<AppUser> user = userService.getUserById(id);
        if (user.isPresent()) {
            ctx.json(user.get());
        } else {
            ctx.status(404).json("User not found");
        }
    }

    // POST /users
    public void createUser(Context ctx) {
        AppUser requestUser = ctx.bodyAsClass(AppUser.class);
        AppUser created = userService.createUser(
                requestUser.getFirstName(),
                requestUser.getLastName(),
                requestUser.getEmail(),
                requestUser.getPassword(),
                requestUser.getLocation()
        );
        ctx.status(201).json(created);
    }

    // PUT /users/:id
    public void updateUser(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        AppUser requestUser = ctx.bodyAsClass(AppUser.class);
        requestUser.setId(id);
        AppUser updated = userService.updateUser(requestUser);
        ctx.json(updated);
    }

    // DELETE /users/:id
    public void deleteUser(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        Optional<AppUser> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(user.get());
            ctx.status(204);
        } else {
            ctx.status(404).json("User not found");
        }
    }
}
