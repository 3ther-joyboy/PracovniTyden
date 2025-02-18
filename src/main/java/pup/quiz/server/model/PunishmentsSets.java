package pup.quiz.server.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "punishment_set")
public class PunishmentsSets {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long Id;

    @ManyToMany(mappedBy = "Sets")
    public Set<Punishments> Punish;
}
