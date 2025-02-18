package pup.quiz.server.model;

import jakarta.persistence.ManyToMany;

import java.util.Set;

public class PunishmentsSets {
    @ManyToMany(mappedBy = "Sets")
    public Set<Punishments> Punish;
}
