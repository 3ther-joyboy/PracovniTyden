package pup.quiz.server.model;
import java.security.SecureRandom;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collections;
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

    @OneToMany(mappedBy="game")
    public Set<User> Sets;
}
