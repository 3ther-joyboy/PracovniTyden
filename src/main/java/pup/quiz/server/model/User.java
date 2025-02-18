package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "sessions")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    public String Name;

    public String Icon;

    public int Score;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session", nullable = false)
    public Session session;


}
