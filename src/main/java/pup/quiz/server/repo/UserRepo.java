package pup.quiz.server.repo;

import org.springframework.data.repository.CrudRepository;
import pup.quiz.server.model.User;

import java.util.UUID;

public interface UserRepo  extends CrudRepository<User, UUID> {
}
