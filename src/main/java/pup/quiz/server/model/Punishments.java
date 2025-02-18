package pup.quiz.server.model;

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

    @ManyToMany()
    @JoinTable(
            name = "p_sets",
            joinColumns = @JoinColumn(name = "punishment"),
            inverseJoinColumns = @JoinColumn(name = "set")
    )
    public Set<PunishmentsSets> Sets;
}
