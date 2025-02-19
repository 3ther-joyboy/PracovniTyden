package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public UUID Id;


    @Column(name = "pfp")
    public String ProfilePicture;

    @Column(name = "name")
    public String Name;


    @Column(name = "score")
    public int Score = 0;

    @ManyToMany()
    @JoinTable(
            name = "answer_user",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_answer")
    )
    public Set<Answer> Answered;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "session", nullable = false)
    public Session session;

    @Column(name = "punish")
    public Instant punishTimestamp = Instant.MIN;
}
