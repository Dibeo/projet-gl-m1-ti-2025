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
    private User applicant;

    public Application() {}

    public Application(String title, String desc, AdvertType advertType, User applicant) {
        this.applicant = applicant;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User appliant) {
        this.applicant = appliant;
    }
}
