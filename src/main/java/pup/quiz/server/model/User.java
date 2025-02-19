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


    @Column(name = "pfp")
    public String ProfilePicture;

    @Column(name = "name")
    public String Name;


    @Column(name = "score")
    public int Score = 0;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session", nullable = false)
    public Session session;


}
