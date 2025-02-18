package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;


    @ManyToOne
    @JoinColumn(name="session_user", nullable=false)
    public Session game;


}
