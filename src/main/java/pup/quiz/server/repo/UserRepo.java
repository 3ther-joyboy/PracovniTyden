package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.User;

public interface UserRepo  extends CrudRepository<User, Long> {
}
