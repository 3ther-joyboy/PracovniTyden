package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Punishments;
import pup.quiz.server.model.User;

import java.util.Set;
import java.util.UUID;

public interface PunishmentRepo extends CrudRepository<Punishments, Long> {
    @Query(value = "SELECT * FROM punishments WHERE session = :Session and punish > MIN(timestamp) ORDER BY user.punish DESC LIMIT :UserLimit", nativeQuery = true)
    Set<Punishments> RandomPunishments(@Param("Session") UUID session, @Param("UserLimit")int userLimit);
}
