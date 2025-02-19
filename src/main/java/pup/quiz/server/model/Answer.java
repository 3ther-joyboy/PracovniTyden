package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {
    public Answer(String answer, boolean isCorrect) {
        Answer = answer;
        Correct = isCorrect;
    }
    public Answer(String answer, boolean isCorrect, long id) {
        Answer = answer;
        Correct = isCorrect;
        Id = id;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    public Question question;

    @Column(name = "answer")
    public String Answer;

    @Column(name = "correct")
    public Boolean Correct = false;
}
