package pup.quiz.server.model;

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


    @JsonIgnore
    @OneToMany(mappedBy = "players")
    public Set<User> Sets;
}
