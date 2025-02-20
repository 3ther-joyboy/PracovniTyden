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

    @Column(name = "question_text")
    public String Question;

    @ManyToMany
    @JoinTable(
            name = "session_question",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id"))
    public Set<Session> Sessions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="set_id", nullable=false)
    public QuestionSets Set;

    @OneToMany(mappedBy = "question")
    public Set<Answer> Answers;
}
