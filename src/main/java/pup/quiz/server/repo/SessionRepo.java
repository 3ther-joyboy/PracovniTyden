package pup.quiz.server.repo;

import org.hibernate.internal.SessionOwnerBehavior;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Session;

import java.util.Set;

public interface SessionRepo extends CrudRepository<Session, Long> {
    @Query(value = "SELECT * FROM sessions WHERE code = :Code")
    Session findByCode(@Param("Code")String code);
}
