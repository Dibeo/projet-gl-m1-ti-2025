package git.projetgl.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean read = false;

    @Column(nullable = false)
    private NotificationType notificationType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser receiver;


    public Notification() {
    }

    public Notification(AppUser receiver, String content, NotificationType notificationType) {
        this.read = false;
        this.receiver = receiver;
        this.notificationType = notificationType;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public AppUser getReceiver() {
        return receiver;
    }

    public void setReceiver(AppUser receiver) {
        this.receiver = receiver;
    }
}
