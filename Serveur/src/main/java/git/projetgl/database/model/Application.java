package git.projetgl.database.model;

import jakarta.persistence.*;

import static git.projetgl.database.model.Status.WAITING;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status applicationStatus;

    public Application() {
        this.applicationStatus = WAITING;
    }

    public Application(Advert advert, AppUser applicant) {
        super();
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

    public Status getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Status applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
