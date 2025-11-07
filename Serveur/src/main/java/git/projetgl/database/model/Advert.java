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
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdvertType advertType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdvertStatus advertStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User publisher;

    public Advert() {
        this.advertStatus = WAITING;
    }

    public Advert(String title, String desc, AdvertType advertType, User publisher) {
        this();
        this.title = title;
        this.desc = desc;
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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
