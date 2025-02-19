package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @Column(name = "question")
    public String Question;
    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "q_sets",
            joinColumns = @JoinColumn(name = "id_question"),
            inverseJoinColumns = @JoinColumn(name = "id_set")
    )
    public Set<QuestionSets> Sets;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "session_question",
            joinColumns = @JoinColumn(name = "id_question"),
            inverseJoinColumns = @JoinColumn(name = "id_session")
    )
    public Set<Session> Sessions;

    @OneToMany(mappedBy = "question")
    public Set<Answer> Answers;
}
