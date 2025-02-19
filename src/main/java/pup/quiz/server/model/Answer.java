package pup.quiz.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    public Question question;

    @Column(name = "correct")
    public Boolean Correct;
}
