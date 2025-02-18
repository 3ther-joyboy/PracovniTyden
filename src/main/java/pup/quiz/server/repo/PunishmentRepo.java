package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Punishments;

public interface PunishmentRepo extends CrudRepository<Punishments, Long> {
}
