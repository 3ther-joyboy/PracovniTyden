package pup.quiz.server.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pup.quiz.server.model.Session;

import java.util.UUID;

public interface SessionRepo extends CrudRepository<Session, UUID> {
    @Query(value = "SELECT * FROM sessions WHERE code = :Code", nativeQuery = true)
    Session findByCode(@Param("Code")String code);
}
