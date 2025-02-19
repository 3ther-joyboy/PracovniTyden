package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Session;
import pup.quiz.server.model.User;

import java.util.Set;
import java.util.UUID;

public interface UserRepo  extends CrudRepository<User, UUID> { // TODO
    @Query(value = "SELECT * FROM user WHERE session = :Session and punish > asdfasdf ORDER BY user.punish DESC LIMIT :UserLimit", nativeQuery = true)
    Set<User> punishedUsers(@Param("Session")UUID session, @Param("UserLimit")int userLimit);
}
