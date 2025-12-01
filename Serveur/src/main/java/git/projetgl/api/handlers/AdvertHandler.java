package git.projetgl.api.handlers;

import git.projetgl.database.model.Advert;
import git.projetgl.service.AdvertService;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;

public class AdvertHandler {

    private final AdvertService advertService;

    public AdvertHandler(SessionFactory sessionFactory) {
        this.advertService = new AdvertService(sessionFactory);
    }

    public void getAllAdverts(Context ctx) {
        List<Advert> adverts = advertService.getAllAdverts();
        ctx.json(adverts);
    }

    public void getAdvertById(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Advert advert = advertService.getAdvertById(id);
        if (advert != null) {
            ctx.json(advert);
        } else {
            ctx.status(404).json(Map.of("error", "Advert not found"));
        }
    }

    public void createAdvert(Context ctx) {
        Advert advert = ctx.bodyAsClass(Advert.class);
        Advert created = advertService.createAdvert(advert);
        ctx.status(201).json(created);
    }

    public void updateAdvert(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        Advert advert = ctx.bodyAsClass(Advert.class);
        Advert updated = advertService.updateAdvert(id, advert);
        if (updated != null) {
            ctx.json(updated);
        } else {
            ctx.status(404).json(Map.of("error", "Advert not found"));
        }
    }

    public void deleteAdvert(Context ctx) {
        Long id = Long.parseLong(ctx.pathParam("id"));
        advertService.deleteAdvert(id);
        ctx.status(204);
    }

    public void searchAdverts(Context ctx) {
        String keyword = ctx.queryParam("keyword");
        String category = ctx.queryParam("category");
        List<Advert> adverts = advertService.searchAdverts(keyword, category);
        ctx.json(adverts);
    }
}
