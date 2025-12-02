package git.projetgl.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser requester;

    @ManyToOne
    @JoinColumn(name = "advert_id", nullable = false)
    private Advert advert;

    public Application() {}

    public Application(Advert advert, AppUser applicant) {
        this.requester = applicant;
        this.advert = advert;
    }

    public AppUser getApplicant() {
        return requester;
    }

    public void setApplicant(AppUser applicant) {
        this.requester = applicant;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Long getId() {
        return id;
    }
}
