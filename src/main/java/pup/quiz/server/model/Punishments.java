package pup.quiz.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "punishments")
public class Punishments {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @Column(name = "name")
    public String Name;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "p_sets",
            joinColumns = @JoinColumn(name = "id_punishment"),
            inverseJoinColumns = @JoinColumn(name = "id_set")
    )
    public Set<PunishmentsSets> Sets;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "session_punisment",
            joinColumns = @JoinColumn(name = "id_punishment"),
            inverseJoinColumns = @JoinColumn(name = "id_session")
    )
    public Set<Session> Sessions;
}
