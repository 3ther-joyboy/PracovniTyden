package pup.quiz.server.model;
import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sessions")
public class Session {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;


    @Column(name = "code")
    public String Code;


    @ManyToMany(mappedBy = "Sessions")
    public Set<Punishments> Punisment;

    @ManyToMany(mappedBy = "Sessions")
    public Set<Question> Questions;

    @OneToMany(mappedBy = "session")
    public Set<User> Users;
}
