package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.Session;

public interface SessionRepo extends CrudRepository<Session, Long> {
}
