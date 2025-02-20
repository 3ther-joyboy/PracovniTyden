package pup.quiz.server.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    public UUID Id;

    @Column(name = "code")
    public String Code;

    // TODO (one to many connection)
    @Column(name = "current_question")
    public Question CurrentQuestion;

    @ManyToMany(mappedBy = "Sessions")
    public Set<Punishments> Punisment;

    @ManyToMany(mappedBy = "Sessions")
    public Set<Question> Questions;

    @OneToMany(mappedBy = "session")
    public Set<User> Users;
}
