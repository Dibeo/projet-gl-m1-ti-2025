package git.projetgl.database.model;

import jakarta.persistence.*;

import static git.projetgl.database.model.AdvertStatus.WAITING;

@Entity
@Table(name = "advert")
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdvertType advertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdvertStatus advertStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser publisher;

    public Advert() {
        this.advertStatus = WAITING;
    }

    public Advert(String title, String description, AdvertType advertType, AppUser publisher) {
        this();
        this.title = title;
        this.description = description;
        this.advertType = advertType;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public AdvertType getAdvertType() {
        return advertType;
    }

    public void setAdvertType(AdvertType advertType) {
        this.advertType = advertType;
    }

    public AdvertStatus getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(AdvertStatus advertStatus) {
        this.advertStatus = advertStatus;
    }

    public AppUser getPublisher() {
        return publisher;
    }

    public void setPublisher(AppUser publisher) {
        this.publisher = publisher;
    }
}
