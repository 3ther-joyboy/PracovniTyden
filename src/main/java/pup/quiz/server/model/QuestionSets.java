package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "question_set")
public class QuestionSets {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;


    @ManyToMany(mappedBy = "Sets")
    public Set<Question> Question;
}