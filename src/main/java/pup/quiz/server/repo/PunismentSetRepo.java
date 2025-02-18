package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.PunishmentsSets;

public interface PunismentSetRepo extends CrudRepository<PunishmentsSets, Long> {
}
